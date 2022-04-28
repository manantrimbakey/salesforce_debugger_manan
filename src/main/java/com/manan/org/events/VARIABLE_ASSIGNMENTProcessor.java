package com.manan.org.events;

import com.manan.org.analyse.EventProcessorClass;
import com.manan.org.analyse.DebugAnalyser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VARIABLE_ASSIGNMENTProcessor extends EventProcessorClass {

    @Override
    public void execute() {
        Pattern p = eventVsPattern.get("VARIABLE_ASSIGNMENT");
        Matcher m = p.matcher(eventData);
        if (m.matches()) {
            Integer ln = Integer.parseInt(m.group(1));
            String eventText = m.group(2) + "<-" + m.group(3);
            try {
                DebugAnalyser.addEntryObject(generator, event, eventText, ln);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
