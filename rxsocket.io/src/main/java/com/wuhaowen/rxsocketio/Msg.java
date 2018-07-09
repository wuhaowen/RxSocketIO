package com.wuhaowen.rxsocketio;

import java.util.Arrays;

public class Msg {
    private String eventName;
    private Object[] args;

    public Msg(String eventName, Object[] args) {
        this.eventName = eventName;
        this.args = args;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "eventName='" + eventName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
