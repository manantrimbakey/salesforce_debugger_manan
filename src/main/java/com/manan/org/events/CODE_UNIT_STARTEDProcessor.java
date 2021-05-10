package com.manan.org.events;

import com.manan.org.DebugAnalyser;
import com.manan.org.EventProcessorClass;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CODE_UNIT_STARTEDProcessor extends EventProcessorClass {

    public void execute() {
        Matcher m = eventVsPattern.get(event).matcher(eventData);
        if (m.matches()) {
            if (m.groupCount() > 2) {
                try {
                    resultantEventData = m.group(3);
                    /**
                     * Validation:Address:a2N0I00000JrqFR
                     */
                    DebugAnalyser.startArrayObject(generator, event, resultantEventData);
                    needToProcess = true;
                    String VALIDATION_RULE_PATTERN = "Validation:.+:.{15}";
                    Pattern p = Pattern.compile(VALIDATION_RULE_PATTERN);
                    Matcher validationRuleMatcher = p.matcher(resultantEventData);

                    if (validationRuleMatcher.matches()) {
                        processValidations(resultantEventData);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void processValidations(String text) throws IOException {
        String validationText = "";
        generator.writeStartObject();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher v = DebugAnalyser.masterPattern.matcher(line);
            boolean b = v.matches();
            if (b) {
                if (v.group(2).equalsIgnoreCase("CODE_UNIT_FINISHED")) {
                    needToProcess = false;
                    isUpdateStackAtOpening = false;
                    generator.writeStringField("VALIDATION", validationText);
                    generator.writeEndObject();
                    DebugAnalyser.endArrayObject(generator);
                    return;
                } else if (v.group(2).equalsIgnoreCase("VALIDATION_PASS")) {
                    validationText += "\n" + "Validation Passed";
                } else if (v.group(2).equalsIgnoreCase("VALIDATION_RULE")) {
                    Pattern valRulePattern = Pattern.compile("\\|.{15}\\|(.+)");
                    Matcher tempMatch = valRulePattern.matcher(v.group(3));
                    if (tempMatch.matches()) {
                        validationText += "\n" + "Validation Rule Name: " + tempMatch.group(1);
//                        generator.writeStringField("VALIDATION",validationText);
                    }
                } else {
                    if (v.groupCount() == 3) {
                        validationText += "\n" + v.group(3);
//                        generator.writeStringField("VALIDATION",validationText);
                    }
                }
            } else {
                validationText += "\n" + line;
//                generator.writeStringField("VALIDATION",validationText);
            }
        }
    }

    public CODE_UNIT_STARTEDProcessor() {
        isUpdateStackAtOpening = true;
    }

}
