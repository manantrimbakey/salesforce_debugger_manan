package com.manan.org.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;

public class SOQL_EXECUTE_BEGINProcessor extends EventProcessorClass {
    private Gson json;

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        int lineNumber = 0;
//        node.setEvent(event);
        String soql = "";
        try {
            if (m.matches()) {
                try {
                    lineNumber = Integer.parseInt(m.group(1));
                } catch (NumberFormatException e) {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                soql = m.group(2);
                DebugAnalyser.startArrayObject(generator, event, soql, lineNumber);
                String methodCall = json.toJson(eventStack) + "!@#$" + lineNumber;
                if (soqlStatementVsCount.get(methodCall) != null) {
                    int count = soqlStatementVsCount.get(methodCall);
                    count += 1;
                    soqlStatementVsCount.put(methodCall, count);
                } else {
                    soqlStatementVsCount.put(methodCall, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SOQL_EXECUTE_BEGINProcessor() {
        json = new GsonBuilder().disableHtmlEscaping().create();
    }

}
