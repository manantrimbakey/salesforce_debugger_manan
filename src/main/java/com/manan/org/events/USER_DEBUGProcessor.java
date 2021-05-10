package com.manan.org.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class USER_DEBUGProcessor extends EventProcessorClass {

    public void execute() {
        try {
            Gson json = new GsonBuilder().disableHtmlEscaping().create();
            StringBuilder debugLine;
            Pattern p = eventVsPattern.get("USER_DEBUG");
            Matcher m = p.matcher(eventData);
            if (m.matches()) {
                debugLine = new StringBuilder(m.group(2));
                while (scanner.hasNextLine()) {
                    String lines = scanner.nextLine();
                    Matcher v = DebugAnalyser.masterPattern.matcher(lines);
                    boolean b = v.matches();
                    if (b) {
                        DebugAnalyser.addEntryObject(generator, event, json.toJson(debugLine.toString()));
                        app.process(lines, scanner);
                        return;
                    } else {
                        debugLine.append("\n" + lines);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
