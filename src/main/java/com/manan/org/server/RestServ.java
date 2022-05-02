package com.manan.org.server;

import com.manan.org.analyse.DebugAnalyser;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.StringWriter;
import java.util.HashMap;

@RestController
public class RestServ {

    @SuppressWarnings("unchecked")
    @PostMapping(path = "/analyse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public Object analyse(@RequestBody Object jsonData) {
        String result = "";
        try {
            DebugAnalyser da = new DebugAnalyser();
            if (jsonData instanceof HashMap) {
                HashMap<String, Object> h = (HashMap<String, Object>) jsonData;
                StringWriter st = da.analyseLogPost((String) h.get("data"));
                result = st.toString();
            } else {
                result = "invalid data";
            }
        } catch (Exception e) {
            result = e.getMessage() + " " + e.getStackTrace();
        }
        return result;
    }
}
