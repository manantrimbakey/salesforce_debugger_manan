package com.manan.org;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ClassGenerator {

    public static void main(String[] args) {
        String f = "package com.manan.org.events;\n\nimport com.manan.org.EventProcessorClass;\n\nimport java.util.Map;\nimport java.util.regex.Pattern;\n\npublic class @className extends EventProcessorClass {\n\n    public void execute() {\n\n    }\n\n}\n";
        try {
            ArrayList<String> className = new ArrayList<>();
            className.add("FLOW_INTERVIEW_FINISHEDProcessor");
            for (String clss : className) {
                try (FileWriter fw = new FileWriter(new File("C:\\Users\\manan\\Desktop\\Desktop Files\\Java Projects\\salesforce_debugger_manan\\src\\main\\java\\com\\manan\\org\\events\\"+clss+".java"))) {
                    fw.write(f.replaceAll("@className",clss));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
