package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;

public class DML_BEGINProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);

        try {
            if (m.matches()) {
//                node.setData(m.group(2) + " operation on " + m.group(4) + " records of object " + m.group(3));
                DebugAnalyser.startArrayObject(generator, m.group(2) + " operation on " + m.group(4) + " records of object " + m.group(3));
                needToProcess = true;
                try {


//                    node.setLineNumber(Integer.parseInt(m.group(1)));
                } catch (NumberFormatException e) {
                    // just ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DML_BEGINProcessor() {
        isUpdateStackAtOpening = true;
    }

}
