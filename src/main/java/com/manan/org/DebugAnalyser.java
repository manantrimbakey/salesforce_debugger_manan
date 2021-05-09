package com.manan.org;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

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
        try (FileWriter fw = new FileWriter(new File("C:\\Users\\manan\\Desktop\\Desktop Files\\log1__json.json"))) {
            DebugAnalyser da = new DebugAnalyser();
            StringWriter st = da.analyseLog("C:\\Users\\manan\\Desktop\\Desktop Files\\log1.log");
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
                    handleIgnoreLine(event, eventText, uniqueId, line, generator);
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
//                        resetFlags(event);
                        ignoreStack.pop();
                    } else {
                        // ANCHOR : handle ignored lines
                    }
                } else if (matchToken(event, eventText, OPENING_TOKENS)) {
                    // ANCHOR : if event is any event opening tag
                    generator.writeStartObject();
                    generator.writeFieldName(event + " " + eventText);
                    generator.writeStartArray();
                    this.process(scanner);
                } else if (matchToken(event, eventText, CLOSING_TOKENS)) {
                    // ANCHOR : if event is any event closing tag
                    generator.writeEndArray();
                    generator.writeEndObject();
                    return;
                } else {
                    // ANCHOR : if event is not any opening and closing tag
                    generator.writeStartObject();
                    generator.writeFieldName(event);
                    generator.writeString(eventText);
                    generator.writeEndObject();
                }
            } else {
                // ANCHOR : process line which does not any event
                generator.writeStartObject();
                generator.writeFieldName("text");
                generator.writeString(line);
                generator.writeEndObject();
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

    public void handleIgnoreLine(String event, String eventText, String uniqueId, String s, JsonGenerator generator) throws IOException {
        if (event.equalsIgnoreCase("USER_DEBUG")) {
            generator.writeStartObject();
            generator.writeFieldName(event);
            generator.writeString(eventText);
            generator.writeEndObject();
        }
    }
}