package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

import java.util.regex.Matcher;

public class DML_BEGINProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);

        try {
            if (m.matches()) {
                int ln = 0;
                try {
                    ln = Integer.parseInt(m.group(1));
                } catch (NumberFormatException e) {
                    // just ignore
                }
//                node.setData(m.group(2) + " operation on " + m.group(4) + " records of object " + m.group(3));
                DebugAnalyser.startArrayObject(generator, m.group(2) + " operation on " + m.group(4) + " records of object " + m.group(3), ln);
                needToProcess = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DML_BEGINProcessor() {
        isUpdateStackAtOpening = true;
    }

}
