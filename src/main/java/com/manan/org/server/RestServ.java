package com.manan.org.server;

import com.manan.org.analyse.DebugAnalyser;
import org.springframework.web.bind.annotation.*;
import java.io.StringWriter;
import java.util.HashMap;

@RestController
public class RestServ {

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Object analyse(@RequestBody Object jsonData) {
        try {
            DebugAnalyser da = new DebugAnalyser();
            HashMap<String, Object> h = (HashMap<String, Object>) jsonData;
            StringWriter st = da.analyseLogPost((String) h.get("data"));
            return st.toString();
        } catch (Exception e) {
            return e.getMessage() + " " + e.getStackTrace();
        }

    }
}
