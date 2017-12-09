package com.cloud.agent.util.k8s;

/**
 * Created by zhaoyun on 2017/12/6.
 * @author zhaoyun
 */
public class K8sRcTemplate {

    public static String env = "- name: ${name}\n" +
            "  value: ${value}\n";

    public static String rc = "apiVersion: v1\n" +
            "kind: ReplicationController\n" +
            "metadata:\n" +
            "  name: ${name}-rc\n" +
            "  labels:\n" +
            "    domain: \"${domain}\"\n" +
            "spec:\n" +
            "  replicas: ${replicas}\n" +
            "  template:\n" +
            "    metadata:\n" +
            "      labels:\n" +
            "        app: ${app}\n" +
            "        max-scale: \"${max-scale}\"\n" +
            "        min-scale: \"${max-scale}\"\n" +
            "    spec:\n" +
            "     containers:\n" +
            "     - name: ${name}\n" +
            "       image: ${image}\n" +
            "       ${env}  \n" +
            "       ports:\n" +
            "       - containerPort: ${port}\n" +
            "         protocol: TCP\n" +
            "       resources: \n" +
            "         requests: #容器运行时，最低资源需求，也就是说最少需要多少资源容器才能正常运行\n" +
            "           cpu: ${cpu} #CPU资源（核数），两种方式，浮点数或者是整数+m，0.1=100m，最少值为0.001核（1m）\n" +
            "           memory: ${memory} #内存使用量\n" +
            "         limits: #资源限制\n" +
            "           cpu: ${cpu}\n" +
            "           memory: ${memory}\n";

    public static String service = "apiVersion: v1\n" +
            "kind: Service\n" +
            "metadata:\n" +
            "  name: ${name}-service\n" +
            "  labels:\n" +
            "    app: ${app}\n" +
            "spec:\n" +
            "  ${type}\n" +
            "  ports:\n" +
            "  - port: ${publish}\n" +
            "    targetPort: ${port}\n" +
            "  selector:\n" +
            "    app: ${app}\n";
}
