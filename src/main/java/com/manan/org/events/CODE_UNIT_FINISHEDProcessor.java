package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

public class CODE_UNIT_FINISHEDProcessor extends EventProcessorClass {

    public void execute() {
        try {
            DebugAnalyser.endArrayObject(generator);
            isUpdateStackAtClosing = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
