package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.util.Map;
import java.util.regex.Pattern;

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
