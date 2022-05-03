package com.manan.org.events;

import com.manan.org.analyse.DebugAnalyser;
import com.manan.org.analyse.EventProcessorClass;

public class SOQL_EXECUTE_ENDProcessor extends EventProcessorClass {

    public void execute() {
        try {
            DebugAnalyser.endArrayObject(generator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
