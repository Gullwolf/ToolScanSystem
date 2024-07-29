package com.example.toolscansystem;

public class TS {
    private String tool;
    private String store;

    public TS(String tool, String store) {
        this.tool = tool;
        this.store = store;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}

