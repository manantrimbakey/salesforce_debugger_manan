package com.manan.org.analyse;

// import java.io.File;
// import java.io.FileWriter;
// import java.util.ArrayList;

public class ClassGenerator {

    /**
     * Main Method
     * 
     * @param args
     */
    /*
     * public static void main(String[] args) {
     * String f =
     * "package com.manan.org.events;\n\nimport com.manan.org.analyse.EventProcessorClass;\n\nimport java.util.Map;\nimport java.util.regex.Pattern;\n\npublic class @className extends EventProcessorClass {\n\n \n\n}\n"
     * ;
     * try {
     * ArrayList<String> className = new ArrayList<>();
     * className.add("BULK_HEAP_ALLOCATEProcessor");
     * className.add("CALLOUT_REQUESTProcessor");
     * className.add("CALLOUT_RESPONSEProcessor");
     * className.add("CODE_UNIT_FINISHEDProcessor");
     * className.add("CODE_UNIT_STARTEDProcessor");
     * className.add("CONSTRUCTOR_ENTRYProcessor");
     * className.add("CONSTRUCTOR_EXITProcessor");
     * className.add("CUMULATIVE_LIMIT_USAGEProcessor");
     * className.add("CUMULATIVE_LIMIT_USAGE_ENDProcessor");
     * className.add("CUMULATIVE_PROFILINGProcessor");
     * className.add("CUMULATIVE_PROFILING_BEGINProcessor");
     * className.add("CUMULATIVE_PROFILING_ENDProcessor");
     * className.add("DML_BEGINProcessor");
     * className.add("DML_ENDProcessor");
     * className.add("EMAIL_QUEUEProcessor");
     * className.add("ENTERING_MANAGED_PKGProcessor");
     * className.add("EVENT_SERVICE_PUB_BEGINProcessor");
     * className.add("EVENT_SERVICE_PUB_DETAILProcessor");
     * className.add("EVENT_SERVICE_PUB_ENDProcessor");
     * className.add("EVENT_SERVICE_SUB_BEGINProcessor");
     * className.add("EVENT_SERVICE_SUB_DETAILProcessor");
     * className.add("EVENT_SERVICE_SUB_ENDProcessor");
     * className.add("EXCEPTION_THROWNProcessor");
     * className.add("EXECUTION_FINISHEDProcessor");
     * className.add("EXECUTION_STARTEDProcessor");
     * className.add("FATAL_ERRORProcessor");
     * className.add("FLOW_ACTIONCALL_DETAILProcessor");
     * className.add("FLOW_ASSIGNMENT_DETAILProcessor");
     * className.add("FLOW_BULK_ELEMENT_BEGINProcessor");
     * className.add("FLOW_BULK_ELEMENT_DETAILProcessor");
     * className.add("FLOW_BULK_ELEMENT_ENDProcessor");
     * className.add("FLOW_BULK_ELEMENT_LIMIT_USAGEProcessor");
     * className.add("FLOW_BULK_ELEMENT_NOT_SUPPORTEDProcessor");
     * className.add("FLOW_CREATE_INTERVIEW_BEGINProcessor");
     * className.add("FLOW_CREATE_INTERVIEW_ENDProcessor");
     * className.add("FLOW_CREATE_INTERVIEW_ERRORProcessor");
     * className.add("FLOW_ELEMENT_BEGINProcessor");
     * className.add("FLOW_ELEMENT_DEFERREDProcessor");
     * className.add("FLOW_ELEMENT_ENDProcessor");
     * className.add("FLOW_ELEMENT_ERRORProcessor");
     * className.add("FLOW_ELEMENT_FAULTProcessor");
     * className.add("FLOW_ELEMENT_LIMIT_USAGEProcessor");
     * className.add("FLOW_INTERVIEW_FINISHEDProcessor");
     * className.add("FLOW_INTERVIEW_FINISHED_LIMIT_USAGEProcessor");
     * className.add("FLOW_INTERVIEW_PAUSEDProcessor");
     * className.add("FLOW_INTERVIEW_RESUMEDProcessor");
     * className.add("FLOW_LOOP_DETAILProcessor");
     * className.add("FLOW_RULE_DETAILProcessor");
     * className.add("FLOW_START_INTERVIEWS_BEGINProcessor");
     * className.add("FLOW_START_INTERVIEWS_ENDProcessor");
     * className.add("FLOW_START_INTERVIEWS_ERRORProcessor");
     * className.add("FLOW_START_INTERVIEW_BEGINProcessor");
     * className.add("FLOW_START_INTERVIEW_ENDProcessor");
     * className.add("FLOW_START_INTERVIEW_LIMIT_USAGEProcessor");
     * className.add("FLOW_START_SCHEDULED_RECORDSProcessor");
     * className.add("FLOW_SUBFLOW_DETAILProcessor");
     * className.add("FLOW_VALUE_ASSIGNMENTProcessor");
     * className.add("FLOW_WAIT_EVENT_RESUMING_DETAILProcessor");
     * className.add("FLOW_WAIT_EVENT_WAITING_DETAILProcessor");
     * className.add("FLOW_WAIT_RESUMING_DETAILProcessor");
     * className.add("FLOW_WAIT_WAITING_DETAILProcessor");
     * className.add("HEAP_ALLOCATEProcessor");
     * className.add("HEAP_DEALLOCATEProcessor");
     * className.add("IDEAS_QUERY_EXECUTEProcessor");
     * className.add("LIMIT_USAGEProcessor");
     * className.add("LIMIT_USAGE_FOR_NSProcessor");
     * className.add("METHOD_ENTRYProcessor");
     * className.add("METHOD_EXITProcessor");
     * className.add("NAMED_CREDENTIAL_REQUESTProcessor");
     * className.add("NAMED_CREDENTIAL_RESPONSEProcessor");
     * className.add("NAMED_CREDENTIAL_RESPONSE_DETAILProcessor");
     * className.add("NBA_NODE_BEGINProcessor");
     * className.add("NBA_NODE_DETAILProcessor");
     * className.add("NBA_NODE_ENDProcessor");
     * className.add("NBA_NODE_ERRORProcessor");
     * className.add("NBA_OFFER_INVALIDProcessor");
     * className.add("NBA_STRATEGY_BEGINProcessor");
     * className.add("NBA_STRATEGY_ENDProcessor");
     * className.add("NBA_STRATEGY_ERRORProcessor");
     * className.add("POP_TRACE_FLAGSProcessor");
     * className.add("PUSH_NOTIFICATION_INVALID_APPProcessor");
     * className.add("PUSH_NOTIFICATION_INVALID_CERTIFICATEProcessor");
     * className.add("PUSH_NOTIFICATION_INVALID_NOTIFICATIONProcessor");
     * className.add("PUSH_NOTIFICATION_NOT_ENABLEDProcessor");
     * className.add("PUSH_NOTIFICATION_NO_DEVICESProcessor");
     * className.add("PUSH_NOTIFICATION_SENTProcessor");
     * className.add("PUSH_TRACE_FLAGSProcessor");
     * className.add("QUERY_MORE_BEGINProcessor");
     * className.add("QUERY_MORE_ENDProcessor");
     * className.add("QUERY_MORE_ITERATIONSProcessor");
     * className.add("SAVEPOINT_ROLLBACKProcessor");
     * className.add("SAVEPOINT_SETProcessor");
     * className.add("SLA_ENDProcessor");
     * className.add("SLA_EVAL_MILESTONEProcessor");
     * className.add("SLA_NULL_START_DATEProcessor");
     * className.add("SLA_PROCESS_CASEProcessor");
     * className.add("SOQL_EXECUTE_BEGINProcessor");
     * className.add("SOQL_EXECUTE_ENDProcessor");
     * className.add("SOQL_EXECUTE_EXPLAINProcessor");
     * className.add("SOSL_EXECUTE_BEGINProcessor");
     * className.add("SOSL_EXECUTE_ENDProcessor");
     * className.add("STACK_FRAME_VARIABLE_LISTProcessor");
     * className.add("STATEMENT_EXECUTEProcessor");
     * className.add("STATIC_VARIABLE_LISTProcessor");
     * className.add("SYSTEM_CONSTRUCTOR_ENTRYProcessor");
     * className.add("SYSTEM_CONSTRUCTOR_EXITProcessor");
     * className.add("SYSTEM_METHOD_ENTRYProcessor");
     * className.add("SYSTEM_METHOD_EXITProcessor");
     * className.add("SYSTEM_MODE_ENTERProcessor");
     * className.add("SYSTEM_MODE_EXITProcessor");
     * className.add("TESTING_LIMITSProcessor");
     * className.add("TOTAL_EMAIL_RECIPIENTS_QUEUEDProcessor");
     * className.add("USER_DEBUGProcessor");
     * className.add("USER_INFOProcessor");
     * className.add("VALIDATION_ERRORProcessor");
     * className.add("VALIDATION_FAILProcessor");
     * className.add("VALIDATION_FORMULAProcessor");
     * className.add("VALIDATION_PASSProcessor");
     * className.add("VALIDATION_RULEProcessor");
     * className.add("VARIABLE_ASSIGNMENTProcessor");
     * className.add("VARIABLE_SCOPE_BEGINProcessor");
     * className.add("VARIABLE_SCOPE_ENDProcessor");
     * className.add("VF_APEX_CALL_ENDProcessor");
     * className.add("VF_APEX_CALL_STARTProcessor");
     * className.add("VF_DESERIALIZE_VIEWSTATE_BEGINProcessor");
     * className.add("VF_DESERIALIZE_VIEWSTATE_ENDProcessor");
     * className.add("VF_EVALUATE_FORMULA_BEGINProcessor");
     * className.add("VF_EVALUATE_FORMULA_ENDProcessor");
     * className.add("VF_PAGE_MESSAGEProcessor");
     * className.add("VF_SERIALIZE_VIEWSTATE_BEGINProcessor");
     * className.add("VF_SERIALIZE_VIEWSTATE_ENDProcessor");
     * className.add("WF_ACTIONProcessor");
     * className.add("WF_ACTIONS_ENDProcessor");
     * className.add("WF_ACTION_TASKProcessor");
     * className.add("WF_APPROVALProcessor");
     * className.add("WF_APPROVAL_REMOVEProcessor");
     * className.add("WF_APPROVAL_SUBMITProcessor");
     * className.add("WF_APPROVAL_SUBMITTERProcessor");
     * className.add("WF_ASSIGNProcessor");
     * className.add("WF_CRITERIA_BEGINProcessor");
     * className.add("WF_CRITERIA_ENDProcessor");
     * className.add("WF_EMAIL_ALERTProcessor");
     * className.add("WF_EMAIL_SENTProcessor");
     * className.add("WF_ENQUEUE_ACTIONSProcessor");
     * className.add("WF_ESCALATION_ACTIONProcessor");
     * className.add("WF_ESCALATION_RULEProcessor");
     * className.add("WF_EVAL_ENTRY_CRITERIAProcessor");
     * className.add("WF_FIELD_UPDATEProcessor");
     * className.add("WF_FLOW_ACTION_BEGINProcessor");
     * className.add("WF_FLOW_ACTION_DETAILProcessor");
     * className.add("WF_FLOW_ACTION_ENDProcessor");
     * className.add("WF_FLOW_ACTION_ERRORProcessor");
     * className.add("WF_FLOW_ACTION_ERROR_DETAILProcessor");
     * className.add("WF_FORMULAProcessor");
     * className.add("WF_HARD_REJECTProcessor");
     * className.add("WF_NEXT_APPROVERProcessor");
     * className.add("WF_NO_PROCESS_FOUNDProcessor");
     * className.add("WF_OUTBOUND_MSGProcessor");
     * className.add("WF_PROCESS_FOUNDProcessor");
     * className.add("WF_PROCESS_NODEProcessor");
     * className.add("WF_REASSIGN_RECORDProcessor");
     * className.add("WF_RESPONSE_NOTIFYProcessor");
     * className.add("WF_RULE_ENTRY_ORDERProcessor");
     * className.add("WF_RULE_EVAL_BEGINProcessor");
     * className.add("WF_RULE_EVAL_ENDProcessor");
     * className.add("WF_RULE_EVAL_VALUEProcessor");
     * className.add("WF_RULE_FILTERProcessor");
     * className.add("WF_RULE_INVOCATIONProcessor");
     * className.add("WF_RULE_NOT_EVALUATEDProcessor");
     * className.add("WF_SOFT_REJECTProcessor");
     * className.add("WF_SPOOL_ACTION_BEGINProcessor");
     * className.add("WF_TIME_TRIGGERProcessor");
     * className.add("WF_TIME_TRIGGERS_BEGINProcessor");
     * className.add("XDS_DETAILProcessor");
     * className.add("XDS_RESPONSEProcessor");
     * className.add("XDS_RESPONSE_DETAILProcessor");
     * className.add("XDS_RESPONSE_ERRORProcessor");
     * for (String clss : className) {
     * try (FileWriter fw = new FileWriter(new File(
     * "src\\main\\java\\com\\manan\\org\\events\\"
     * + clss + ".java"))) {
     * fw.write(f.replaceAll("@className", clss));
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     */
}
