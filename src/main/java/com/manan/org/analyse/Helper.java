package com.manan.org.analyse;

import java.util.*;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonGenerator;

public class Helper {

    public Map<String, Pattern> eventVsPattern;
    public Stack<String> callStack;
    public Stack<String> eventStack;
    public Scanner scanner;
    public String event;
    public String eventData;
    public String fullString;
    public LinkedHashMap<String, Integer> soqlStatementVsCount;
    public DebugAnalyser app;
    public ArrayList<String> exceptions = new ArrayList<>();
    public Boolean needToProcess;
    public JsonGenerator generator;

    public void processEvent() {
        EventProcessorClass process;
        try {
            process = (EventProcessorClass) Class.forName("com.manan.org.events." + event + "Processor")
                    .getDeclaredConstructor().newInstance();
            process.soqlStatementVsCount = soqlStatementVsCount;
            process.callStack = callStack;
            process.eventStack = eventStack;
            process.exceptions = exceptions;
            process.scanner = scanner;
            process.app = app;
            process.generator = generator;
            process.event = event;
            process.eventData = eventData;
            process.eventVsPattern = eventVsPattern;
            process.execute();
            needToProcess = process.needToProcess;
            if (needToProcess) {
                updateStack(process.event, process.resultantEventData, process);
            }
            exceptions.addAll(process.exceptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStack(String event, String eventData, EventProcessorClass processor) {
        if (processor.isUpdateStackAtOpening) {
            callStack.push(eventData.isBlank() ? event : eventData);
            eventStack.push(event);
        } else if (processor.isUpdateStackAtClosing) {
            if (eventStack.size() > 0 && callStack.size() > 0) {
                eventStack.pop();
                callStack.pop();
            }
        }
    }
}
