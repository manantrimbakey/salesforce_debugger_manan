package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

import java.util.regex.Matcher;

public class CONSTRUCTOR_ENTRYProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        if (m.matches()) {
            try {
                int ln = 0;
                try {
                    ln = Integer.parseInt(m.group(1));
                } catch (NumberFormatException ignored) {

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
