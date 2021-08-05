package com.syhuang.hudson.plugins.listgitbranchesparameter;

import hudson.model.Hudson;
import hudson.slaves.EnvironmentVariablesNodeProperty;
import hudson.slaves.NodeProperty;

import java.util.HashMap;
import java.util.Map;

public class GlobalNodeProperties {

    public static Map<String, String> getProperties() {
        Map<String, String> globalNodeProperties = new HashMap<String, String>();
        // Get latest global properties by looking for all 
        // instances of EnvironmentVariablesNodeProperty in the global node properties
        for (NodeProperty<?> nodeProperty : Hudson.getInstance()
                .getGlobalNodeProperties()) {
            if (nodeProperty instanceof EnvironmentVariablesNodeProperty) {
                globalNodeProperties.putAll(((EnvironmentVariablesNodeProperty) nodeProperty).getEnvVars());
            }
        }
        return globalNodeProperties;
    }

    public static String getValue(String key) {
        if (key == null) {
            return null;
        } else {
            return getProperties().get(key);
        }
    }
}
