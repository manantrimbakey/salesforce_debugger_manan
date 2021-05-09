package com.manan.org;

import java.util.ArrayList;

public class Closure {

    public ArrayList<String> closures;

    public Closure() {
        this.closures = new ArrayList<>();
        this.closures.add("CODE_UNIT_FINISHED");
        this.closures.add("EXECUTION_FINISHED");
        this.closures.add("METHOD_EXIT");
        this.closures.add("SOQL_EXECUTE_END");
        this.closures.add("CONSTRUCTOR_EXIT");
        this.closures.add("DML_END");
        this.closures.add("SYSTEM_METHOD_EXIT");
        this.closures.add("SYSTEM_MODE_EXIT");
    }
}
