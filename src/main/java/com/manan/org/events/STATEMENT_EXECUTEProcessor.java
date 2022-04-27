package com.manan.org.events;

import com.manan.org.analyse.EventProcessorClass;

import com.manan.org.analyse.DebugAnalyser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class STATEMENT_EXECUTEProcessor extends EventProcessorClass {

    public void execute() {
        String line = "";
        StringBuilder lineNumbers = new StringBuilder();
        Pattern p = eventVsPattern.get("STATEMENT_EXECUTE");
        Matcher m = p.matcher(eventData);
        if (m.matches()) {
            lineNumbers.append(m.group(1));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                Matcher v = DebugAnalyser.masterPattern.matcher(line);
                boolean b = v.matches();
                if (b && v.group(2).equalsIgnoreCase("STATEMENT_EXECUTE")) {
                    Matcher f = p.matcher(v.group(3));
                    if (f.matches()) {
                        lineNumbers.append("," + f.group(1));
                    }
                } else {
                    try {
                        DebugAnalyser.addEntryObject(generator, event, lineNumbers.toString(), 0);
                        app.process(line, scanner, generator);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
