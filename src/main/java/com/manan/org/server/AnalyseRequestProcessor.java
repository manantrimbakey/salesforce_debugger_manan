package com.manan.org.server;

import com.manan.org.DebugAnalyser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
public class AnalyseRequestProcessor {

    @GetMapping("/analyse")
    public String analyse(@RequestParam(value = "path", defaultValue = "C:\\Users\\manan\\Desktop\\Desktop Files\\log1.log") String path) {
        DebugAnalyser da= new DebugAnalyser();
        StringWriter st = da.analyseLog(path);
        return st.toString();
    }
}