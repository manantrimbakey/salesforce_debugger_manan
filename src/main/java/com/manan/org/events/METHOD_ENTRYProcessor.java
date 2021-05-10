package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;

public class METHOD_ENTRYProcessor extends EventProcessorClass {

    public METHOD_ENTRYProcessor() {
        isUpdateStackAtOpening = true;
    }

    public void execute() {
        try {

            Matcher m = eventVsPattern.get(event).matcher(eventData);
            if (m.matches()) {
//                node.setLineNumber(Integer.parseInt(m.group(1)));
//                node.setData(m.group(3));
                DebugAnalyser.startArrayObject(generator, event, m.group(3));
                needToProcess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
