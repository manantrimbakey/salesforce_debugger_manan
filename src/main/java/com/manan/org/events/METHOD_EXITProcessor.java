package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

public class METHOD_EXITProcessor extends EventProcessorClass {

    public void execute() {
        try {
            DebugAnalyser.endArrayObject(generator);
            isUpdateStackAtClosing = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
