package com.manan.org;

import java.util.ArrayList;

public class Ignore {

    public ArrayList<String> ignores;

    public Ignore() {
        this.ignores = new ArrayList<>();
        this.ignores.add("SYSTEM_METHOD_ENTRY");
        this.ignores.add("SYSTEM_METHOD_EXIT");
        this.ignores.add("System.JSON.serialize(Object)");
        this.ignores.add("SYSTEM_CONSTRUCTOR_ENTRY");
        this.ignores.add("SYSTEM_CONSTRUCTOR_EXIT");
        this.ignores.add("HEAP_ALLOCATE");
        this.ignores.add("STATEMENT_EXECUTE");
        this.ignores.add("VARIABLE_ASSIGNMENT");
        this.ignores.add("VARIABLE_SCOPE_BEGIN");
        this.ignores.add("Database.QueryLocator.iterator()");
        this.ignores.add("Database.QueryLocatorIterator.hasNext()");
        this.ignores.add("Database.QueryLocatorIterator.next()");
        // this.ignores.add("SYSTEM_MODE_ENTER");
        // this.ignores.add("SYSTEM_MODE_EXIT");
        this.ignores.add("System.JSON.deserialize(String, System.Type)");
    }
}
