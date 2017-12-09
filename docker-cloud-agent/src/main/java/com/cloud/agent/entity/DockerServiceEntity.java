package com.cloud.agent.entity;

/**
 * Created by zhaoyun on 2017/9/27.
 */
public class DockerServiceEntity {
    private String operUser;
    private String State;
    private String commands;
    private String config;
    private String constraint;
    private String containerLabel;
    private String credentialSpec;
    private String detach;
    private String dns;
    private String dnsOption;
    private String dnsSearch;
    private String endpointMode;
    private String entrypoint;
    private String env;
    private String envFile;
    private String group;
    private String healthCmd;
    private String healthInterval;
    private String healthRetries;
    private String healthStartPeriod;
    private String healthTimeout;
    private String host;
    private String hostname;
    private String label;
    private String limitCpu;
    private String limitMemory;
    private String logDriver;
    private String logOpt;
    private String mode;
    private String mount;
    private String name;
    private String network;
    private String noHealthcheck;
    private String image;

    private String noResolveImage;
    private String placementPref;
    private String publish;
    private String quiet;
    private String readOnly;
    private String replicas;
    private String reserveCpu;
    private String reserveMemory;
    private String restartCondition;
    private String restartDelay;
    private String restartMaxAttempts;
    private String restartWindow;
    private String rollbackDelay;
    private String rollbackFailureAction;
    private String rollbackMaxFailureRatio;
    private String rollbackMonitor;
    private String rollbackOrder;
    private String rollbackParallelism;
    private String secret;
    private String stopGracePeriod;
    private String stopSignal;
    private String tty;
    private String updateDelay;
    private String updateFailureAction;
    private String updateMaxFailureRatio;
    private String updateMonitor;
    private String updateOrder;
    private String updateParallelism;
    private String user;
    private String workdir;
    private String port;
    private String scaleCpu;
    private String scaleMem;
    private String scaleMin;
    private String scaleMax;
    private String domain;
    private String loadblanceTp;


    public String getLoadblanceTp() {
        return loadblanceTp;
    }

    public void setLoadblanceTp(String loadblanceTp) {
        this.loadblanceTp = loadblanceTp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private String CreatedAt;

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getScaleCpu() {
        return scaleCpu;
    }

    public void setScaleCpu(String scaleCpu) {
        this.scaleCpu = scaleCpu;
    }

    public String getScaleMem() {
        return scaleMem;
    }

    public void setScaleMem(String scaleMem) {
        this.scaleMem = scaleMem;
    }

    public String getScaleMin() {
        return scaleMin;
    }

    public void setScaleMin(String scaleMin) {
        this.scaleMin = scaleMin;
    }

    public String getScaleMax() {
        return scaleMax;
    }

    public void setScaleMax(String scaleMax) {
        this.scaleMax = scaleMax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPort() {
        return port;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNoResolveImage() {
        return noResolveImage;
    }

    public void setNoResolveImage(String noResolveImage) {
        this.noResolveImage = noResolveImage;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getContainerLabel() {
        return containerLabel;
    }

    public void setContainerLabel(String containerLabel) {
        this.containerLabel = containerLabel;
    }

    public String getCredentialSpec() {
        return credentialSpec;
    }

    public void setCredentialSpec(String credentialSpec) {
        this.credentialSpec = credentialSpec;
    }

    public String getDetach() {
        return detach;
    }

    public void setDetach(String detach) {
        this.detach = detach;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getDnsOption() {
        return dnsOption;
    }

    public void setDnsOption(String dnsOption) {
        this.dnsOption = dnsOption;
    }

    public String getDnsSearch() {
        return dnsSearch;
    }

    public void setDnsSearch(String dnsSearch) {
        this.dnsSearch = dnsSearch;
    }

    public String getEndpointMode() {
        return endpointMode;
    }

    public void setEndpointMode(String endpointMode) {
        this.endpointMode = endpointMode;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getEnvFile() {
        return envFile;
    }

    public void setEnvFile(String envFile) {
        this.envFile = envFile;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHealthCmd() {
        return healthCmd;
    }

    public void setHealthCmd(String healthCmd) {
        this.healthCmd = healthCmd;
    }

    public String getHealthInterval() {
        return healthInterval;
    }

    public void setHealthInterval(String healthInterval) {
        this.healthInterval = healthInterval;
    }

    public String getHealthRetries() {
        return healthRetries;
    }

    public void setHealthRetries(String healthRetries) {
        this.healthRetries = healthRetries;
    }

    public String getHealthStartPeriod() {
        return healthStartPeriod;
    }

    public void setHealthStartPeriod(String healthStartPeriod) {
        this.healthStartPeriod = healthStartPeriod;
    }

    public String getHealthTimeout() {
        return healthTimeout;
    }

    public void setHealthTimeout(String healthTimeout) {
        this.healthTimeout = healthTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLimitCpu() {
        return limitCpu;
    }

    public void setLimitCpu(String limitCpu) {
        this.limitCpu = limitCpu;
    }

    public String getLimitMemory() {
        return limitMemory;
    }

    public void setLimitMemory(String limitMemory) {
        this.limitMemory = limitMemory;
    }

    public String getLogDriver() {
        return logDriver;
    }

    public void setLogDriver(String logDriver) {
        this.logDriver = logDriver;
    }

    public String getLogOpt() {
        return logOpt;
    }

    public void setLogOpt(String logOpt) {
        this.logOpt = logOpt;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNoHealthcheck() {
        return noHealthcheck;
    }

    public void setNoHealthcheck(String noHealthcheck) {
        this.noHealthcheck = noHealthcheck;
    }


    public String getPlacementPref() {
        return placementPref;
    }

    public void setPlacementPref(String placementPref) {
        this.placementPref = placementPref;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getQuiet() {
        return quiet;
    }

    public void setQuiet(String quiet) {
        this.quiet = quiet;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getReplicas() {
        return replicas;
    }

    public void setReplicas(String replicas) {
        this.replicas = replicas;
    }

    public String getReserveCpu() {
        return reserveCpu;
    }

    public void setReserveCpu(String reserveCpu) {
        this.reserveCpu = reserveCpu;
    }

    public String getReserveMemory() {
        return reserveMemory;
    }

    public void setReserveMemory(String reserveMemory) {
        this.reserveMemory = reserveMemory;
    }

    public String getRestartCondition() {
        return restartCondition;
    }

    public void setRestartCondition(String restartCondition) {
        this.restartCondition = restartCondition;
    }

    public String getRestartDelay() {
        return restartDelay;
    }

    public void setRestartDelay(String restartDelay) {
        this.restartDelay = restartDelay;
    }

    public String getRestartMaxAttempts() {
        return restartMaxAttempts;
    }

    public void setRestartMaxAttempts(String restartMaxAttempts) {
        this.restartMaxAttempts = restartMaxAttempts;
    }

    public String getRestartWindow() {
        return restartWindow;
    }

    public void setRestartWindow(String restartWindow) {
        this.restartWindow = restartWindow;
    }

    public String getRollbackDelay() {
        return rollbackDelay;
    }

    public void setRollbackDelay(String rollbackDelay) {
        this.rollbackDelay = rollbackDelay;
    }

    public String getRollbackFailureAction() {
        return rollbackFailureAction;
    }

    public void setRollbackFailureAction(String rollbackFailureAction) {
        this.rollbackFailureAction = rollbackFailureAction;
    }

    public String getRollbackMaxFailureRatio() {
        return rollbackMaxFailureRatio;
    }

    public void setRollbackMaxFailureRatio(String rollbackMaxFailureRatio) {
        this.rollbackMaxFailureRatio = rollbackMaxFailureRatio;
    }

    public String getRollbackMonitor() {
        return rollbackMonitor;
    }

    public void setRollbackMonitor(String rollbackMonitor) {
        this.rollbackMonitor = rollbackMonitor;
    }

    public String getRollbackOrder() {
        return rollbackOrder;
    }

    public void setRollbackOrder(String rollbackOrder) {
        this.rollbackOrder = rollbackOrder;
    }

    public String getRollbackParallelism() {
        return rollbackParallelism;
    }

    public void setRollbackParallelism(String rollbackParallelism) {
        this.rollbackParallelism = rollbackParallelism;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getStopGracePeriod() {
        return stopGracePeriod;
    }

    public void setStopGracePeriod(String stopGracePeriod) {
        this.stopGracePeriod = stopGracePeriod;
    }

    public String getStopSignal() {
        return stopSignal;
    }

    public void setStopSignal(String stopSignal) {
        this.stopSignal = stopSignal;
    }

    public String getTty() {
        return tty;
    }

    public void setTty(String tty) {
        this.tty = tty;
    }

    public String getUpdateDelay() {
        return updateDelay;
    }

    public void setUpdateDelay(String updateDelay) {
        this.updateDelay = updateDelay;
    }

    public String getUpdateFailureAction() {
        return updateFailureAction;
    }

    public void setUpdateFailureAction(String updateFailureAction) {
        this.updateFailureAction = updateFailureAction;
    }

    public String getUpdateMaxFailureRatio() {
        return updateMaxFailureRatio;
    }

    public void setUpdateMaxFailureRatio(String updateMaxFailureRatio) {
        this.updateMaxFailureRatio = updateMaxFailureRatio;
    }

    public String getUpdateMonitor() {
        return updateMonitor;
    }

    public void setUpdateMonitor(String updateMonitor) {
        this.updateMonitor = updateMonitor;
    }

    public String getUpdateOrder() {
        return updateOrder;
    }

    public void setUpdateOrder(String updateOrder) {
        this.updateOrder = updateOrder;
    }

    public String getUpdateParallelism() {
        return updateParallelism;
    }

    public void setUpdateParallelism(String updateParallelism) {
        this.updateParallelism = updateParallelism;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWorkdir() {
        return workdir;
    }

    public void setWorkdir(String workdir) {
        this.workdir = workdir;
    }
}
