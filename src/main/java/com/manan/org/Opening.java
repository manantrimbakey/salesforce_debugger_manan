package com.manan.org;

import java.util.ArrayList;

public class Opening {

    public ArrayList<String> openers;

    public Opening() {
        this.openers = new ArrayList<>();
        this.openers.add("EXECUTION_STARTED");
        this.openers.add("CODE_UNIT_STARTED");
        this.openers.add("METHOD_ENTRY");
        this.openers.add("CONSTRUCTOR_ENTRY");
        this.openers.add("DML_BEGIN");
        this.openers.add("SOQL_EXECUTE_BEGIN");
        this.openers.add("SYSTEM_METHOD_ENTRY");
        this.openers.add("SYSTEM_MODE_ENTER");
    }
}
