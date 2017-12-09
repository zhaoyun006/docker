package com.cloud.agent.util.k8s;

import static com.cloud.agent.configure.Configure.getConf;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zhaozq
 * @version 1.0
 */
public class K8sCmd {

    public static String kubectlDeleteService = getConf("kubectlDeleteService", 1,"kubectl delete -f service/{0}-service");
    public static String kubectlDeleteRc = getConf("kubectlDeleteRc", 1,"kubectl delete rc/{0}-rc 2>&1");
    public static String kubectlCreate = getConf("kubectlCreate", 1,"kubectl create -f {0} 2>&1");
    public static String kubectlUpdate = getConf("kubectlUpdate", 1,"kubectl rolling-update {0} --image=\"{1}\" --dry-run=true  ");

    public static String kubectlGetService = getConf("kubectlGetService", 1, "kubectl get svc {0}-service -o yaml |grep -i port");
}
