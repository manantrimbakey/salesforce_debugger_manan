package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LIMIT_USAGE_FOR_NSProcessor extends EventProcessorClass {

    public void execute() {
        StringBuilder text = new StringBuilder(event);
        try {
            while (scanner.hasNextLine()) {
                String lines = scanner.nextLine();
                Matcher v = DebugAnalyser.masterPattern.matcher(lines);
                boolean b = v.matches();
                if (b) {
                    DebugAnalyser.addEntryObject(generator, event, text.toString(),0);
                    app.process(lines, scanner);
                    return;
                } else {
                    text.append("\n" + lines);
                }
            }
        } catch (Exception e) {

        }
    }

}
