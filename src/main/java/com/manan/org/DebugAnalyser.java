package com.manan.org;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DebugAnalyser {

    public static ArrayList<String> OPENING_TOKENS;
    public static ArrayList<String> CLOSING_TOKENS;
    public static ArrayList<String> IGNORE_LIST;
    public static Map<String, Pattern> eventVsPattern;
    public static Pattern masterPattern;

    /**
     * [\d]{1,3}:[\d]{1,3}:[\d]{1,3}.[\d]{1,3}\s*\((\d+)\)\|([\da-zA-z]+)(.*)
     */
    public static String MASTER_REGEX = "[\\d]{1,3}:[\\d]{1,3}:[\\d]{1,3}.[\\d]{1,3}\\s*\\((\\d+)\\)\\|([\\da-zA-z]+)(.*)";
    public static RegEx REGEX_DATA = new RegEx();
    public Stack<String> ignoreStack;
    public Stack<String> callStack;
    public Stack<String> eventStack;
    public Scanner logFileScanner;
    public String fullStackLog;
    public LinkedHashMap<String, Integer> soqlStatementVsCount;
    public ArrayList<String> exceptionsList;
    public JsonGenerator generator;
    public ArrayList<String> exceptions;

    static {
        OPENING_TOKENS = new Opening().openers;
        CLOSING_TOKENS = new Closure().closures;
        IGNORE_LIST = new Ignore().ignores;
        initPatterns();
    }

    public DebugAnalyser() {
        ignoreStack = new Stack<>();
        callStack = new Stack<>();
        eventStack = new Stack<>();
        soqlStatementVsCount = new LinkedHashMap<>();
        fullStackLog = "";
        exceptionsList = new ArrayList<>();
        logFileScanner = null;
        exceptions = new ArrayList<>();
    }

    private static void initPatterns() {
        masterPattern = Pattern.compile(MASTER_REGEX);
        eventVsPattern = new HashMap<>();
        Map<String, String> eventVsRegEx = REGEX_DATA.eventVsRegEx;
        for (Map.Entry<String, String> each : eventVsRegEx.entrySet()) {
            Pattern p = Pattern.compile(each.getValue());
            eventVsPattern.put(each.getKey(), p);
        }
    }

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(new File("C:\\Users\\manan\\Desktop\\Desktop Files\\apex-07L9D000007G1DEUA0__json.json"))) {
            DebugAnalyser da = new DebugAnalyser();
            StringWriter st = da.analyseLog("C:\\Users\\manan\\Downloads\\apex-07L9D000007G1DEUA0.log");
            fw.write(st.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringWriter analyseLog(String source) {
        File sourceFile = new File(source);
        try (Scanner scanner = new Scanner(sourceFile)) {
            JsonFactory factory = new JsonFactory();
            StringWriter jsonObjectWriter = new StringWriter();
            generator = factory.createGenerator(jsonObjectWriter);
            generator.setPrettyPrinter(new DefaultPrettyPrinter());
            generator.writeStartObject();
            generator.writeFieldName("log");
            generator.writeStartArray();
            this.process(scanner);
            generator.writeEndArray();
            generator.writeEndObject();
            generator.close();
            return jsonObjectWriter;
        } catch (Exception e) {
            StringWriter jsonObjectWriter = new StringWriter();
            e.printStackTrace();
            return jsonObjectWriter;
        }
    }

    public void process(Scanner scanner) throws Exception {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher m = masterPattern.matcher(line);
            boolean b = m.matches();
            if (b) {
                String uniqueId = m.group(1);
                String event = m.group(2);
                String eventText = m.group(3);
                if (ignoreStack.size() > 0 && !(!event.isBlank() && ignoreStack.size() > 0
                        && REGEX_DATA.closingVsOpening.get(event) != null
                        && REGEX_DATA.closingVsOpening.get(event).equalsIgnoreCase(ignoreStack.peek()))) {
                    // ANCHOR : handle ignored lines
                    handleIgnoreLine(event, eventText, uniqueId, line, generator, scanner);
                } else if (checkForIgnore(line)) {
                    if (matchToken(event, eventText, OPENING_TOKENS)) {
                        // ANCHOR : add to ignore stack if the keyword is in ignore list and is opening
                        // tag
                        if (IGNORE_LIST.contains(event)) {
                            ignoreStack.push(event);
                        }
                    } else if (!event.isBlank() && ignoreStack.size() > 0
                            && REGEX_DATA.closingVsOpening.get(event) != null
                            && REGEX_DATA.closingVsOpening.get(event).equalsIgnoreCase(ignoreStack.peek())) {
                        // ANCHOR : pop from ignore stack if the event is in ignore and is closing of
                        // top element
                        // resetFlags(event);
                        ignoreStack.pop();
                    } else {
                        // ANCHOR : handle ignored lines
                    }
                } else if (matchToken(event, eventText, OPENING_TOKENS)) {
                    // ANCHOR : if event is any event opening tag
                    if (handleSpecificNode(event, eventText, uniqueId, line, generator, scanner)) {
                        this.process(scanner);
                    }
                } else if (matchToken(event, eventText, CLOSING_TOKENS)) {
                    // ANCHOR : if event is any event closing tag
                    handleSpecificNode(event, eventText, uniqueId, line, generator, scanner);
                    return;
                } else {
                    // ANCHOR : if event is not any opening and closing tag
                    handleSpecificNode(event, eventText, uniqueId, line, generator, scanner);
                }
            } else {
                // ANCHOR : process line which does not any event
                DebugAnalyser.addEntryObject(generator, "text", line, 0);
            }
        }
    }

    public boolean matchToken(String event, String eventText, ArrayList<String> tokenList) {
        boolean result = false;
        if (event == null || event.isEmpty()) {
            return false;
        }
        if (tokenList.contains(event)) {
            Pattern p = eventVsPattern.get(event);
            if (p != null) {
                if (eventText == null || eventText.isEmpty()) {
                    eventText = " ";
                }
                Matcher m = p.matcher(eventText);
                if (m.matches()) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean checkForIgnore(String line) {
        boolean result = false;
        if (line != null && !line.isEmpty()) {
            for (String token : IGNORE_LIST) {
                if (line.contains(token)) {
                    result = true;
                    break;
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    public void handleIgnoreLine(String event, String eventData, String uniqueId, String line,
                                 JsonGenerator generator, Scanner scanner) throws IOException {
        if (event.equalsIgnoreCase("USER_DEBUG")) {
            handleSpecificNode(event, eventData, uniqueId, line, generator, scanner);
        }
    }

    public boolean handleSpecificNode(String event, String eventData, String uniqueId, String line,
                                      JsonGenerator generator, Scanner scanner) {
        Helper helper = new Helper();
        helper.app = this;
        helper.callStack = callStack;
        helper.event = event;
        helper.eventData = eventData;
        helper.eventStack = eventStack;
        helper.exceptions = exceptions;
        helper.fullString = line;
        helper.scanner = scanner;
        helper.soqlStatementVsCount = soqlStatementVsCount;
        helper.generator = generator;
        helper.eventVsPattern = eventVsPattern;
        helper.processEvent();
        return helper.needToProcess;
    }

    public static void addEntryObject(JsonGenerator generator, String event, String eventData, int lineNumber) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName(event);
        generator.writeString(eventData);
        generator.writeNumberField("ln",lineNumber);
        generator.writeEndObject();
    }

    public static void endArrayObject(JsonGenerator generator) throws IOException {
        generator.writeEndArray();
        generator.writeEndObject();
    }

    public static void startArrayObject(JsonGenerator generator, String event, String eventData, int lineNumber) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("ln",lineNumber);
        generator.writeFieldName(event + " " + eventData);
        generator.writeStartArray();
    }

    public static void startArrayObject(JsonGenerator generator, String event, int lineNumber) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("ln",lineNumber);
        generator.writeFieldName(event);
        generator.writeStartArray();
    }

    public void process(String line, Scanner scanner) throws Exception {
        Matcher m = masterPattern.matcher(line);
        boolean b = m.matches();
        if (b) {
            String uniqueId = m.group(1);
            String event = m.group(2);
            String eventText = m.group(3);
            if (ignoreStack.size() > 0 && !(!event.isBlank() && ignoreStack.size() > 0
                    && REGEX_DATA.closingVsOpening.get(event) != null
                    && REGEX_DATA.closingVsOpening.get(event).equalsIgnoreCase(ignoreStack.peek()))) {
                // ANCHOR : handle ignored lines
                handleIgnoreLine(event, eventText, uniqueId, line, generator, scanner);
            } else if (checkForIgnore(line)) {
                if (matchToken(event, eventText, OPENING_TOKENS)) {
                    // ANCHOR : add to ignore stack if the keyword is in ignore list and is opening
                    // tag
                    if (IGNORE_LIST.contains(event)) {
                        ignoreStack.push(event);
                    }
                } else if (!event.isBlank() && ignoreStack.size() > 0
                        && REGEX_DATA.closingVsOpening.get(event) != null
                        && REGEX_DATA.closingVsOpening.get(event).equalsIgnoreCase(ignoreStack.peek())) {
                    // ANCHOR : pop from ignore stack if the event is in ignore and is closing of
                    // top element
                    // resetFlags(event);
                    ignoreStack.pop();
                } else {
                    // ANCHOR : handle ignored lines
                }
            } else if (matchToken(event, eventText, OPENING_TOKENS)) {
                // ANCHOR : if event is any event opening tag
                if (handleSpecificNode(event, eventText, uniqueId, line, generator, scanner)) {
                    this.process(scanner);
                }
            } else if (matchToken(event, eventText, CLOSING_TOKENS)) {
                // ANCHOR : if event is any event closing tag
                handleSpecificNode(event, eventText, uniqueId, line, generator, scanner);
                return;
            } else {
                // ANCHOR : if event is not any opening and closing tag
                handleSpecificNode(event, eventText, uniqueId, line, generator, scanner);
            }
        } else {
            // ANCHOR : process line which does not any event
            DebugAnalyser.addEntryObject(generator, "text", line, 0);
        }
    }
}