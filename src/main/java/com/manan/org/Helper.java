package com.manan.org;

import java.util.*;
import java.util.regex.Pattern;

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

    public void processEvent() {
        EventProcessorClass process;
        try {
            process = (EventProcessorClass) Class.forName("com.manan.org.events." + event + "Processor")
                    .getDeclaredConstructor().newInstance();
            // eventVsTypeMap.put(event, process);
            process.soqlStatementVsCount = soqlStatementVsCount;
            process.callStack = callStack;
            process.eventStack = eventStack;
            process.exceptions = new ArrayList<>();
            process.scanner = scanner;
            process.app = app;
            process.execute();
            needToProcess = process.needToProcess;
            if (needToProcess) {
//                 updateStack(node, process);
            }
            exceptions.addAll(process.exceptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
