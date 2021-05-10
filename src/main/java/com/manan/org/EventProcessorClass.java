package com.manan.org;

import com.fasterxml.jackson.core.JsonGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

public class EventProcessorClass {

    public Scanner scanner;
    public LinkedHashMap<String, Integer> soqlStatementVsCount;
    public Stack<String> callStack;
    public Stack<String> eventStack;
    public boolean isUpdateStackAtOpening = false;
    public boolean isUpdateStackAtClosing = false;
    public ArrayList<String> exceptions = new ArrayList<>();
    public DebugAnalyser app;
    public Boolean needToProcess = true;
    public JsonGenerator generator;
    public String resultantEventData;
    public String resultantEvent;
    public String event;
    public String eventData;
    public String fullString;
    public Map<String,Pattern> eventVsPattern;

    public String getResultantEventData() {
        return resultantEventData;
    }

    public void setResultantEventData(String resultantEventData) {
        this.resultantEventData = resultantEventData;
    }

    public void setResultantEvent(String resultantEvent) {
        this.resultantEvent = resultantEvent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getFullString() {
        return fullString;
    }

    public void setFullString(String fullString) {
        this.fullString = fullString;
    }

    public String getResultantEvenData() {
        return resultantEventData;
    }

    public void setResultantEvenData(String resultantEventData) {
        this.resultantEventData = resultantEventData;
    }

    public String getResultantEvent() {
        return resultantEvent;
    }

    public void setResultantEven(String resultantEvent) {
        this.resultantEvent = resultantEvent;
    }

    public JsonGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(JsonGenerator generator) {
        this.generator = generator;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public LinkedHashMap<String, Integer> getSoqlStatementVsCount() {
        return soqlStatementVsCount;
    }

    public void setSoqlStatementVsCount(LinkedHashMap<String, Integer> soqlStatementVsCount) {
        this.soqlStatementVsCount = soqlStatementVsCount;
    }

    public Stack<String> getCallStack() {
        return callStack;
    }

    public void setCallStack(Stack<String> callStack) {
        this.callStack = callStack;
    }

    public Stack<String> getEventStack() {
        return eventStack;
    }

    public void setEventStack(Stack<String> eventStack) {
        this.eventStack = eventStack;
    }

    public boolean isUpdateStackAtOpening() {
        return isUpdateStackAtOpening;
    }

    public void setUpdateStackAtOpening(boolean updateStackAtOpening) {
        isUpdateStackAtOpening = updateStackAtOpening;
    }

    public boolean isUpdateStackAtClosing() {
        return isUpdateStackAtClosing;
    }

    public void setUpdateStackAtClosing(boolean updateStackAtClosing) {
        isUpdateStackAtClosing = updateStackAtClosing;
    }

    public ArrayList<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(ArrayList<String> exceptions) {
        this.exceptions = exceptions;
    }

    public DebugAnalyser getApp() {
        return app;
    }

    public void setApp(DebugAnalyser app) {
        this.app = app;
    }

    public Boolean getNeedToProcess() {
        return needToProcess;
    }

    public void setNeedToProcess(Boolean needToProcess) {
        this.needToProcess = needToProcess;
    }

    public void execute() {

    }
}
