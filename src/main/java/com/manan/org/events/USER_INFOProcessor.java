package com.manan.org.events;

import com.manan.org.EventProcessorClass;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class USER_INFOProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        if (m.matches()) {
            try {
                generator.writeStartObject();
                generator.writeFieldName(event);
                generator.writeString(m.group(1));
                generator.writeEndObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
