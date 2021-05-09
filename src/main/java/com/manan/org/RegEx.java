package com.manan.org;

import java.util.HashMap;
import java.util.Map;

public class RegEx {

    public Map<String, String> eventVsRegEx;
    public Map<String, String> closingVsOpening;

    public RegEx() {
        initRegex();
        initTagContainer();
    }

    public void initRegex() {
        this.eventVsRegEx = new HashMap<>();

        /**
         * \|\[(\d+)\]\|(.{15})?\|(.+)
         */
        this.eventVsRegEx.put("METHOD_ENTRY", "\\|\\[(\\d+)\\]\\|(.{15})?\\|(.+)");

        /**
         * \|\[(\d+)\]\|(.{15}\|)?(.+)
         */
        this.eventVsRegEx.put("METHOD_EXIT", "\\|\\[(\\d+)\\]\\|(.{15}\\|)?(.+)");

        /**
         * \|\[(.+)\]\|([a-zA-Z0-9]{15})?\|?(.+)
         */
        this.eventVsRegEx.put("CODE_UNIT_STARTED", "\\|\\[(.+)\\]\\|([a-zA-Z0-9]{15})?\\|?(.+)");
        this.eventVsRegEx.put("CODE_UNIT_FINISHED", "\\|(.*)");
        this.eventVsRegEx.put("EXECUTION_FINISHED", "(.*)");
        this.eventVsRegEx.put("EXECUTION_STARTED", "(.*)");

        /**
         * \|\[(\d+)?(EXTERNAL)?\]\|.{15}\|\<init\>\(.*\)\|(.+)
         */
        this.eventVsRegEx.put("CONSTRUCTOR_ENTRY", "\\|\\[(\\d+)?(EXTERNAL)?\\]\\|.{15}\\|\\<init\\>\\(.*\\)\\|(.+)");
        this.eventVsRegEx.put("CONSTRUCTOR_EXIT", "\\|(.*)");

        /**
         * \|\[(\d+)?\w+?\]\|Op:([a-zA-Z]+)\|Type:([a-zA-Z0-9_]+)\|Rows:(\d+)
         */
        this.eventVsRegEx.put("DML_BEGIN",
                "\\|\\[(\\d+)?\\w+?\\]\\|Op:([a-zA-Z]+)\\|Type:([a-zA-Z0-9_]+)\\|Rows:(\\d+)");
        this.eventVsRegEx.put("DML_END", "\\|(.*)");

        /**
         * \|\[(.+)\]\|\w+:\d+\|(.*)
         */
        this.eventVsRegEx.put("SOQL_EXECUTE_BEGIN", "\\|\\[(.+)\\]\\|\\w+:\\d+\\|(.*)");
        this.eventVsRegEx.put("SOQL_EXECUTE_END", "\\|(.*)");
        this.eventVsRegEx.put("SYSTEM_METHOD_ENTRY", "\\|(.*)");
        this.eventVsRegEx.put("SYSTEM_MODE_ENTER", "\\|(.*)");
        this.eventVsRegEx.put("SYSTEM_METHOD_EXIT", "\\|(.*)");
        this.eventVsRegEx.put("SYSTEM_MODE_EXIT", "\\|(.*)");

        /**
         * \|\[.+\]\|[a-zA-Z\d]{15}\|([a-zA-Z\d_@\.]+)\|.+
         */
        this.eventVsRegEx.put("USER_INFO", "\\|\\[.+\\]\\|[a-zA-Z\\d]{15}\\|([a-zA-Z\\d_@\\.]+)\\|.+");
        this.eventVsRegEx.put("USER_DEBUG", "\\|\\[(\\d+)\\]\\|DEBUG\\|(.*)");

        /**
         * \|\[(\d*)?.*?\]\|([\w\d_\.]+)\|(null|.+)
         */
        this.eventVsRegEx.put("VARIABLE_ASSIGNMENT", "\\|\\[(\\d*)?.*?\\]\\|([\\w\\d_\\.]+)\\|(null|.+)");

        /**
         * \|\[(\d+)\]
         */
        this.eventVsRegEx.put("STATEMENT_EXECUTE", "\\|\\[(\\d+)\\]");

        /**
         * \|\[(.+)\]\|Bytes:.+
         */
        this.eventVsRegEx.put("HEAP_ALLOCATE", "\\|\\[(.+)\\]\\|Bytes:.+");
    }

    public void initTagContainer() {
        this.closingVsOpening = new HashMap<>();
        this.closingVsOpening.put("METHOD_EXIT", "METHOD_ENTRY");
        this.closingVsOpening.put("EXECUTION_FINISHED", "EXECUTION_STARTED");
        this.closingVsOpening.put("CODE_UNIT_FINISHED", "CODE_UNIT_STARTED");
        this.closingVsOpening.put("CONSTRUCTOR_EXIT", "CONSTRUCTOR_ENTRY");
        this.closingVsOpening.put("DML_END", "DML_BEGIN");
        this.closingVsOpening.put("SOQL_EXECUTE_END", "SOQL_EXECUTE_BEGIN");
        this.closingVsOpening.put("SYSTEM_METHOD_EXIT", "SYSTEM_METHOD_ENTRY");
        this.closingVsOpening.put("SYSTEM_MODE_EXIT", "SYSTEM_MODE_ENTER");
    }
}