package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;

public class STATIC_VARIABLE_LISTProcessor extends EventProcessorClass {

    public void execute() {
        try {
            while (scanner.hasNextLine()) {
                String lines = scanner.nextLine();
                Matcher v = DebugAnalyser.masterPattern.matcher(lines);
                boolean b = v.matches();
                if (b) {
                    app.process(lines, scanner);
                    return;
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
