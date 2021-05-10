package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

public class EXECUTION_STARTEDProcessor extends EventProcessorClass {

    public void execute() {
        try {
            DebugAnalyser.startArrayObject(generator, event, eventData, 0);
            needToProcess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EXECUTION_STARTEDProcessor() {
        isUpdateStackAtOpening = true;
    }

}
