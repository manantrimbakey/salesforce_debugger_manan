package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

import java.util.regex.Matcher;

public class LIMIT_USAGE_FOR_NSProcessor extends EventProcessorClass {

    public void execute() {
        StringBuilder text = new StringBuilder("");
        try {
            while (scanner.hasNextLine()) {
                String lines = scanner.nextLine();
                Matcher v = DebugAnalyser.masterPattern.matcher(lines);
                boolean b = v.matches();
                if (b) {
                    DebugAnalyser.addEntryObject(generator, event, text.toString(), 0);
                    app.process(lines, scanner, generator);
                    return;
                } else {
                    text.append("\n").append(lines);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
