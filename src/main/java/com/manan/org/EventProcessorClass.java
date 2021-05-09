package com.manan.org;

import com.fasterxml.jackson.core.JsonGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Stack;

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
