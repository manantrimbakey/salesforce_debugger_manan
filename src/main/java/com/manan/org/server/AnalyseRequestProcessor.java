package com.manan.org.server;

import com.manan.org.analyse.DebugAnalyser;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;

@RestController
public class AnalyseRequestProcessor {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String analyse(
            @RequestParam(value = "path", defaultValue = "C:\\Users\\manan\\Downloads\\apex-07L9D000007G1DEUA0.log") String path) {
        DebugAnalyser da = new DebugAnalyser();
        StringWriter st = da.analyseLog(path);
        return st.toString();
    }
}