package com.cloud.agent.service;

import com.cloud.agent.entity.ImagesBuildInfoEntity;
import com.cloud.agent.util.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;
import sun.net.util.IPAddressUtil;

import java.io.File;
import java.util.ArrayList;

import static com.cloud.agent.util.RunCommandUtil.getSecurityCmd;
import static com.cloud.agent.util.RunCommandUtil.runScript;

/**
 * Created by zhaoyun on 2017/10/21.
 *
 * @author zhaoyun
 */
public class CompileService {

    private ImagesBuildInfoEntity imagesBuildInfoEntity;
    private static SVNClientManager ourClientManager;
    private LogUtil logUtil;
    private final int dirLength = 10;
    private final String SEPARATOR = System.getProperty("file.separator");
    private final String GIT_FETCH = SEPARATOR.concat(".git".concat(SEPARATOR) + "FETCH_HEAD");
    private final String HEAD = SEPARATOR.concat(".git".concat(SEPARATOR) + "HEAD");
    private final String HTTP = "http";

    private final Logger logger = LoggerFactory.getLogger(CompileService.class);
    private String dataDir = "/home/data/cloud/source/";
    private boolean faild = false;
    private String oldCommitId;
    private String newCommitId;
    private boolean autoCompile = false;

    public boolean isAutoCompile() {
        return autoCompile;
    }

    public void setAutoCompile(boolean autoCompile) {
        this.autoCompile = autoCompile;
    }

    public boolean isFaild() {
        return faild;
    }

    public void setFaild(boolean faild) {
        this.faild = faild;
    }

    public void setImagesBuildInfoEntity(ImagesBuildInfoEntity imagesBuildInfoEntity) {
        this.imagesBuildInfoEntity = imagesBuildInfoEntity;
    }

    public void setLogUtil(LogUtil logUtil) {
        this.logUtil = logUtil;
    }

    /**
     * 根据构建日志，自动生成tag
     * tag按照 年月日-00001，构建次数
     * 例子 2017-10-22 00001
     *
     * @param imagesBuildInfoEntity
     * @return
     */
   public String makeTag(ImagesBuildInfoEntity imagesBuildInfoEntity) {
        String tag;
        if (null != imagesBuildInfoEntity) {
            tag = imagesBuildInfoEntity.getVersion();
        } else {
            tag = "19701010-00001";
        }
        String[] tags = tag.split("-");
        String dates = DateUtil.getDate(DateUtil.DATE_FORMAT).replace("-", "");
        if (!tags[0].equals(dates)) {
            return dates + "-" + "00001";
        }
        int number = Integer.valueOf(tags[1]);
        number = number + 1;
        String s = "";
        if (number < 10000) {
            s = "0";
        }
        if (number < 1000) {
            s = "00";
        }
        if (number < 100) {
            s = "000";
        }
        if (number < 10) {
            s = "0000";
        }
        tag = dates + "-" + s + number;
        return tag;
    }


    /**
     * @return
     */
    public CredentialsProvider getCp() {
        if (!CheckUtil.checkString(imagesBuildInfoEntity.getGitBranch())) {
            imagesBuildInfoEntity.setGitBranch("master");
        }
        String user = imagesBuildInfoEntity.getGitUser();
        String pass = imagesBuildInfoEntity.getGitPassword();
        CredentialsProvider cp = null;
        if (CheckUtil.checkString(user) && CheckUtil.checkString(pass)) {
            logUtil.compileLog("通过用户名密码验证 ".concat(user));
            cp = new UsernamePasswordCredentialsProvider(user, pass);
        }
        if (CheckUtil.checkString(imagesBuildInfoEntity.getGitCiToken())) {
            logUtil.compileLog("通过 gitlab-ci-token 验证 ".concat(imagesBuildInfoEntity.getGitCiToken()));
            cp = new UsernamePasswordCredentialsProvider("gitlab-ci-token", imagesBuildInfoEntity.getGitCiToken());
        }
        return cp;
    }

    /**
     * @param dir
     * @return
     */
    public String getCommitId(File dir) {
        try {
            Iterable<RevCommit> d = Git.open(dir).log().setMaxCount(1).call();
            ObjectId object = null;
            for (RevCommit commit : d) {
                object = commit.getId();
            }
            String id = "";
            if (null != object) {
                id = object.toString();
            }
            logUtil.compileLog("获取到最新 commit_id 为: ".concat(id));
            logUtil.comlileLogClose();
            return id;
        } catch (Exception e) {
            logUtil.compileLog("获取 commit_id 失败".concat(e.getMessage()));
            return "";
        }
    }

    /**
     * @param file
     * @return
     */
    public String getHead(String file) {
        String head = FileIoUtil.readFile(file.concat(HEAD)).trim();
        if (!CheckUtil.checkString(head)){
            return imagesBuildInfoEntity.getGitBranch();
        }
        String[] heads = head.split("/");
        String h = "";
        for (int i = 2; i < heads.length; i++) {
            h += heads[i] + "/";
        }
        return h.substring(0, h.length() - 1);
    }

    /**
     * @param file
     * @return
     */
    public String getRepository(String file) {
        try {
            String[] data = FileIoUtil.readFile(file.concat(GIT_FETCH)).split("\n")[0].replace("  ", "").replace("  ", " ").split(" ");
            return data[data.length - 1];
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param gitFile
     * @return
     */
    public boolean diffRepository(String gitFile) {
        String o =  getHead(gitFile) + getRepository(gitFile);
        String n =  getHead(gitFile) + getRepository(gitFile.concat(".local"));
        if (o.equals(n)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean getIsNewRepository(){
        String repository = getRepository(getFilePath());
        if (repository.equals(imagesBuildInfoEntity.getGitAddress())){
            return true;
        }
        return false;
    }

    /**
     * @param cp
     * @param dir
     * @return
     */
    public boolean updateGitCode(CredentialsProvider cp, File dir) {
        // 对比git地址是否一致,不一致删除老的
        if (!getIsNewRepository()){
            deleteGitCode(getFilePath());
        }
        try {
            Git.open(dir).pull().setCredentialsProvider(cp).setRemoteBranchName(imagesBuildInfoEntity.getGitBranch()).call();
        } catch (Exception e) {
            logUtil.compileLog("更新git代码失败: " + e.getMessage());
            faild = true;
        }
        newCommitId = getCommitId(new File(getFilePath()));
        if (CheckUtil.checkString(oldCommitId) && CheckUtil.checkString(newCommitId)){
            if (newCommitId.equals(oldCommitId)){
                logUtil.compileLog("结束本次构建, 原因:  <span style='color:red'>代码没有改动</span>");
                faild = true;
            }
        }
        return false;
    }

    /**
     *
     * @param cp
     * @param file
     * @param gitUri
     * @param dir
     */
    public void initGitCode(CredentialsProvider cp, String file, String gitUri, File dir) {
        Git git = null;
        File testFilePath = new File(file.concat("/.git"));
        oldCommitId = getCommitId(new File(getFilePath()));
        if (!testFilePath.exists()) {
            logUtil.compileLog("代码路径不存在");
            logUtil.compileLog("初始化代码路径");
            logUtil.compileLog("获取到git代码分支是".concat(imagesBuildInfoEntity.getGitBranch()));
            try {
                if (null == cp) {
                    git = Git.cloneRepository().setURI(gitUri)
                            .setBranch(imagesBuildInfoEntity.getGitBranch()).setDirectory(dir).setNoCheckout(false).call();
                } else {
                    git = Git.cloneRepository().setURI(gitUri).setCredentialsProvider(cp).setNoCheckout(false)
                            .setBranch(imagesBuildInfoEntity.getGitBranch()).setDirectory(dir).call();
                }
            } catch (Exception e) {
                logUtil.compileLog("执行 git 操作失败 ".concat(e.getMessage()));
                logUtil.compileLog("结束本次构建");
                faild = true;
                logUtil.comlileLogClose();
            }
            logUtil.comlileLogClose();
            if (null != git) {
                git.close();
            }
            logUtil.compileLog("执行git 代码初始化 操作完成");
        }
    }

    /**
     * 获取代码路径
     *
     * @return
     */
    public String getFilePath() {
        String file = dataDir.concat(imagesBuildInfoEntity.getUser()).concat("/").concat(RunCommandUtil.getSecurityCmd(imagesBuildInfoEntity.getServiceName()));
        return file;
    }

    /**
     * @param file
     */
    public void deleteGitCode(String file) {
        File deleteFile = new File(file);
        if (deleteFile.exists()) {
            logUtil.compileLog("git 目录更新，删除老的目录");
            if (file.contains(dataDir) && dataDir.length() > dirLength) {
                RunCommandUtil.runScript("rm -rf " + RunCommandUtil.getSecurityCmd(file).replace(" ", ""));
                RunCommandUtil.runScript("rm -rf " + RunCommandUtil.getSecurityCmd(file.concat(".local")).replace(" ", ""));
            }
            logUtil.compileLog("删除老的目录完成");
            logUtil.compileLog("开始重新 clone 代码");
            initGitCode(getCp(), file, imagesBuildInfoEntity.getGitAddress(), new File(file));
            file = file.concat(".local");
            initGitCode(getCp(), file, imagesBuildInfoEntity.getGitAddress(), new File(file));
        }
    }

    public void gitCheckout() {
        String gitUri = imagesBuildInfoEntity.getGitAddress();
        logUtil.compileLog("开始获取git代码 ".concat(gitUri));
        String file = getFilePath();
        if (!new File(file).exists()) {
            logUtil.compileLog("创建代码目录".concat(file));
            FileIoUtil.makeDir(file);
        }
        logUtil.comlileLogClose();
        if (gitUri.contains(HTTP)) {
            CredentialsProvider cp = getCp();
            logUtil.comlileLogClose();
            try {
                // 如果是更新的就删除原有的代码,替换git路径
                if (CheckUtil.checkString(imagesBuildInfoEntity.getNewGit())) {
                    deleteGitCode(file);
                }
                logUtil.comlileLogClose();
                File dir = new File(file);
                initGitCode(cp, file, gitUri, dir);
                updateGitCode(cp, dir);
            } catch (Exception e) {
                logUtil.compileLog("执行git操作失败".concat( e.getMessage()));
                logUtil.compileLog("结束本次构建");
                faild = true;
            }
        }
    }

    /**
     *
     * @param file
     */
    void deleteSvnDir(String file) {
        if (file.contains(dataDir)) {
            logUtil.compileLog("svn目录更新，删除老的目录");
            RunCommandUtil.runScript("rm -rf " + RunCommandUtil.getSecurityCmd(file).replace(" ", ""));
            logUtil.compileLog("删除老的目录完成");
        }
    }

    public void svnCheckout() {
        try {
            logUtil.compileLog("开始下载svn代码".concat(imagesBuildInfoEntity.getSvnAddress()));
            logUtil.compileLog("指定版本号为".concat(imagesBuildInfoEntity.getSvnVersion()));
            logUtil.comlileLogClose();
            String file = getFilePath();
            //某目录在svn的位置，获取目录对应的URL。即版本库对应的URL地址
            SVNURL url = SVNURL.parseURIEncoded(imagesBuildInfoEntity.getSvnAddress());
            //初始化
            DAVRepositoryFactory.setup();
            //驱动选项
            ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
            //提供认证
            SVNRevision svnRevision = SVNRevision.HEAD;

            File configFile = new File("/tmp/." + imagesBuildInfoEntity.getUser());
            File dir = new File(file);
            final int fileSize = 500;
            final ArrayList count = new ArrayList();
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(configFile, imagesBuildInfoEntity.getSvnUser(), imagesBuildInfoEntity.getSvnPassword().toCharArray());
            ourClientManager = SVNClientManager.newInstance(options, authManager);
            SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
            ISVNEventHandler isvnEventHandler = new ISVNEventHandler() {
                @Override
                public void handleEvent(SVNEvent svnEvent, double v) throws SVNException {
                    count.add(1);
                    if (count.size() > fileSize) {
                        logUtil.compileLog("文件更新只显示前500个..");
                        return;
                    }
                    logUtil.compileLog(DateUtil.getDate(DateUtil.TIME_FORMAT).concat(" ").concat(svnEvent.getAction().toString()).concat(" ").concat(svnEvent.getFile().toString()).concat(" ").concat(svnEvent.getRevision() + ""));
                }

                @Override
                public void checkCancelled() throws SVNCancelException {

                }
            };
            updateClient.setEventHandler(isvnEventHandler);
            updateClient.setIgnoreExternals(false);
            if (CheckUtil.checkString(imagesBuildInfoEntity.getNewGit())) {
                deleteSvnDir(file);
            }
            String already = "already a working";
            try {
                logUtil.compileLog("开始 checkout 代码");
                updateClient.doCheckout(url, dir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, true);
                logUtil.compileLog("结束 checkout 代码");
            } catch (SVNException e) {
                if (e.getMessage().contains(already)) {
                    deleteSvnDir(file);
                    logUtil.compileLog("开始重新 checkout 代码");
                    updateClient.doCheckout(url, dir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, true);
                }
            }
            if (CheckUtil.checkString(imagesBuildInfoEntity.getSvnVersion())) {
                svnRevision = SVNRevision.create(Long.valueOf(imagesBuildInfoEntity.getSvnVersion()));
                logUtil.compileLog("设置到更新版本号为:" + imagesBuildInfoEntity.getSvnVersion());
            }
            updateClient.doUpdate(dir, svnRevision, SVNDepth.INFINITY, false, false);
            logUtil.compileLog("本次更新共有 " + (count.size() - 4) + " 个文件遍历..");
            count.clear();
            logUtil.compileLog("更新代码完成");
        } catch (Exception e) {
            logUtil.compileLog("svn执行失败".concat(e.getMessage()));
            logger.error("svn拉取失败", e);
            logUtil.compileLog("结束本次构建");
            logUtil.comlileLogClose();
        }
    }

    /**
     * @param command
     * @param buildLog
     */
    public void buildImages(String command, String buildLog) {
        if (faild){
            return;
        }
        String success1 = "Successfully tagged";
        String success2 = "Successfully built";
        runScript(command.toString());
        String imagesCmd = "docker images 2>&1|grep {0}|grep {1}|head -n 1".replace("{0}", imagesBuildInfoEntity.getRegistry()).replace("{1}", imagesBuildInfoEntity.getVersion());
        logUtil.compileLog(runScript(imagesCmd, 1));
        String log = FileIoUtil.readFile(buildLog);
        log = log.concat("<br>");
        logUtil.compileLog(log);
        if (log.contains(success1) && log.contains(success2)) {
            logUtil.compileLog("<font color='green'>本次构建成功..</font>");
        } else {
            logUtil.compileLog("<font color='red'>本次构建失败..</font>");
            faild = true;
        }
        logUtil.comlileLogClose();
        if (faild){
            return;
        }
        logUtil.compileLog("执行Dockerfile完成");
        logUtil.compileLog("打包镜像完成");
        logUtil.compileLog("开始上传镜像");
        logUtil.comlileLogClose();
        if (new File(buildLog).exists()) {
            new File(buildLog).delete();
        }
    }

    /**
     * @param imageName
     */
    public void push(String imageName) {
        if (faild){
            return;
        }
        imageName = getSecurityCmd(imageName);
        String lock = "/dev/shm/".concat(imagesBuildInfoEntity.getUser()) + imagesBuildInfoEntity.getEnvId() + ".lock";
        String log = runScript("docker push ".concat(imageName).concat("; rm -f ".concat(lock)), 1);
        logUtil.compileLog(log);
        logUtil.comlileLogClose();
        logUtil.compileLog("镜像push完成");
        logUtil.compileLog("结束本次构建");
        logUtil.comlileLogClose();
    }
}
