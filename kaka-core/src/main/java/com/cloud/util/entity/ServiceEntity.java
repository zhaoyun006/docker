package com.cloud.util.entity;

import java.util.Map;

/**
 * Created by zhaoyun on 2017/9/27.
 */
public class ServiceEntity {

    private String Name;
    private Map RollbackConfig;
    private Map UpdateConfig;
    private Map Mode;
    private Map EndpointSpec;
    private Map Labels;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map getRollbackConfig() {
        return RollbackConfig;
    }

    public void setRollbackConfig(Map rollbackConfig) {
        RollbackConfig = rollbackConfig;
    }

    public Map getUpdateConfig() {
        return UpdateConfig;
    }

    public void setUpdateConfig(Map updateConfig) {
        UpdateConfig = updateConfig;
    }

    public Map getMode() {
        return Mode;
    }

    public void setMode(Map mode) {
        Mode = mode;
    }

    public Map getEndpointSpec() {
        return EndpointSpec;
    }

    public void setEndpointSpec(Map endpointSpec) {
        EndpointSpec = endpointSpec;
    }

    public Map getLabels() {
        return Labels;
    }

    public void setLabels(Map labels) {
        Labels = labels;
    }

}
