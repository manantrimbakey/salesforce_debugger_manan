package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CONSTRUCTOR_ENTRYProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        if (m.matches()) {
            try {
//                node.setLineNumber(Integer.parseInt(m.group(1)));
//                node.setData(m.group(3));
                DebugAnalyser.startArrayObject(generator,event,m.group(3));
                needToProcess = true;
            } catch (NumberFormatException e) {
                // just ignore exception
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
