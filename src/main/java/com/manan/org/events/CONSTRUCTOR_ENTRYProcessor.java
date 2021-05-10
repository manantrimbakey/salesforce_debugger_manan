package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.regex.Matcher;

public class CONSTRUCTOR_ENTRYProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        if (m.matches()) {
            try {
//                node.setLineNumber(Integer.parseInt(m.group(1)));
//                node.setData(m.group(3));
                int ln = 0;
                try {
                    ln = Integer.parseInt(m.group(1));
                } catch (NumberFormatException e) {

                }
                DebugAnalyser.startArrayObject(generator,event,m.group(3), ln);
                needToProcess = true;
            } catch (NumberFormatException e) {
                // just ignore exception
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
