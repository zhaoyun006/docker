package com.cloud.sms.docker.ci.entity;

/**
 * Created by zhaoyun on 2017/10/17.
 * @author zhaoyun
 */
public class ImagesBuildInfoEntity {
    private String user;
    private int envId;
    private String serviceName;
    private String codeTp;
    private String templateId;
    private String autoBuild;
    private String log;
    private String buildStatus;
    private String buildApiServer;
    private String entId;
    private String imagesId;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagesId() {
        return imagesId;
    }

    public void setImagesId(String imagesId) {
        this.imagesId = imagesId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }
    public String getBuildApiServer() {
        return buildApiServer;
    }

    public void setBuildApiServer(String buildApiServer) {
        this.buildApiServer = buildApiServer;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getAutoBuild() {
        return autoBuild;
    }

    public void setAutoBuild(String autoBuild) {
        this.autoBuild = autoBuild;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCodeTp() {
        return codeTp;
    }

    public void setCodeTp(String codeTp) {
        this.codeTp = codeTp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
        this.envId = envId;
    }

    private String buildTp;
    private String registry;
    private String version;
    private String dockerFile;
    private String gitAddress;
    private String gitBranch;
    private String gitSubDir;
    private String gitUser;
    private String gitPassword;
    private String svnAddress;
    private String svnVersion;
    private String svnSubDir;
    private String svnUser;
    private String svnPassword;
    private String dockerFileSource;
    private String dockerFilePath;
    private String gitCiToken;
    private String newGit;

    public String getNewGit() {
        return newGit;
    }

    public void setNewGit(String newGit) {
        this.newGit = newGit;
    }

    public String getGitCiToken() {
        return gitCiToken;
    }

    public void setGitCiToken(String gitCiToken) {
        this.gitCiToken = gitCiToken;
    }

    public String getBuildTp() {
        return buildTp;
    }

    public void setBuildTp(String buildTp) {
        this.buildTp = buildTp;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDockerFile() {
        return dockerFile;
    }

    public void setDockerFile(String dockerFile) {
        this.dockerFile = dockerFile;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public String getGitBranch() {
        return gitBranch;
    }

    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
    }

    public String getGitSubDir() {
        return gitSubDir;
    }

    public void setGitSubDir(String gitSubDir) {
        this.gitSubDir = gitSubDir;
    }

    public String getGitUser() {
        return gitUser;
    }

    public void setGitUser(String gitUser) {
        this.gitUser = gitUser;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public void setGitPassword(String gitPassword) {
        this.gitPassword = gitPassword;
    }

    public String getSvnAddress() {
        return svnAddress;
    }

    public void setSvnAddress(String svnAddress) {
        this.svnAddress = svnAddress;
    }

    public String getSvnVersion() {
        return svnVersion;
    }

    public void setSvnVersion(String svnVersion) {
        this.svnVersion = svnVersion;
    }

    public String getSvnSubDir() {
        return svnSubDir;
    }

    public void setSvnSubDir(String svnSubDir) {
        this.svnSubDir = svnSubDir;
    }

    public String getSvnUser() {
        return svnUser;
    }

    public void setSvnUser(String svnUser) {
        this.svnUser = svnUser;
    }

    public String getSvnPassword() {
        return svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    public String getDockerFileSource() {
        return dockerFileSource;
    }

    public void setDockerFileSource(String dockerFileSource) {
        this.dockerFileSource = dockerFileSource;
    }

    public String getDockerFilePath() {
        return dockerFilePath;
    }

    public void setDockerFilePath(String dockerFilePath) {
        this.dockerFilePath = dockerFilePath;
    }
}
