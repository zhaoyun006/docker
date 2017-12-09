package com.cloud.agent.util.vlan;

import com.cloud.agent.util.FileIoUtil;
import com.cloud.agent.util.RunCommandUtil;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 虚拟机vlan创建
 * @author zhaozq
 * @version 1.0
 * @since 1.0
 */
public class CreateVlan {

    /**
     * 创建vlan需要的脚本
     * @return
     */
    static String getVlanScripts(){
        return "#!/bin/bash\n" +
                "#zy\n" +
                "#\n" +
                "\n" +
                "# 第一个参数为vlan的ID\n" +
                "vlan_id=$1\n" +
                "\n" +
                "# 第二个为本机可用的网卡\n" +
                "eth=$(brctl show |awk '{print $NF}'|egrep \"\\.\"|awk -F\".\" '{print $1}'|uniq -c |sort -rn |head -n 1|awk '{print $2}')\n" +
                "\n" +
                "# 第三个参数为IP\n" +
                "# 如果有IP就添加IP地址\n" +
                "# 10.16.35.100/24\n" +
                "ip=$3\n" +
                "\n" +
                "# 第4个位网关\n" +
                "# 10.16.35.1\n" +
                "gateway=$4\n" +
                "\n" +
                "function run() {\n" +
                " grep \"$1\" /etc/rc.local -q || ( echo \"$1\" >> /etc/rc.local && $1 )\n" +
                " ip link show br${vlan_id} |grep -q br${vlan_id}|| $1\n" +
                " ip link show br${vlan_id} |grep UP -q || $1\n" +
                "}\n" +
                "run \"ip link set $eth up\"\n" +
                "run \"brctl addbr br$vlan_id\"\n" +
                "run \"ip link set br$vlan_id up\"\n" +
                "run \"ip link add link $eth name ${eth}.${vlan_id} type vlan id ${vlan_id}\" \n" +
                "run \"ip link set ${eth}.${vlan_id} up\"\n" +
                "run \"brctl addif br${vlan_id} ${eth}.${vlan_id}\"\n" +
                "[ ! -z $ip ] && run \"ip add add $ip dev br${vlan_id}\"\n" +
                "[ ! -z $ip ] && run \"ip route del `ip route show |grep default|grep -v br${vlan_id}`\"\n" +
                "[ ! -z $ip ] && run \"ip route del `ip route show |grep 0.0.0.0|grep -v br${vlan_id}`\"\n" +
                "[ ! -z $ip ] && run \"ip route add default via $gateway dev br${vlan_id}\"\n" +
                "brctl show \n" +
                "ip add |grep br${vlan_id}\n" +
                "\n" +
                " ip link show br${vlan_id} |grep -q br${vlan_id}|| $1\n" +
                " ip link show br${vlan_id} |grep UP -q || $1\n" +
                "}\n" +
                "run \"ip link set $eth up\"\n" +
                "run \"brctl addbr br$vlan_id\"\n" +
                "run \"ip link set br$vlan_id up\"\n" +
                "run \"ip link add link $eth name ${eth}.${vlan_id} type vlan id ${vlan_id}\" \n" +
                "run \"ip link set ${eth}.${vlan_id} up\"\n" +
                "run \"brctl addif br${vlan_id} ${eth}.${vlan_id}\"\n" +
                "[ ! -z $ip ] && run \"ip add add $ip dev br${vlan_id}\"\n" +
                "[ ! -z $ip ] && run \"ip route del `ip route show |grep default|grep -v br${vlan_id}`\"\n" +
                "[ ! -z $ip ] && run \"ip route del `ip route show |grep 0.0.0.0|grep -v br${vlan_id}`\"\n" +
                "[ ! -z $ip ] && run \"ip route add default via $gateway dev br${vlan_id}\"";
    }

    /**
     * 将脚本写入到文件里
     * @param ip
     */
    static void writeScripts(String ip){
        String file = "/dev/shm/vlan.sh.".concat(ip);
        FileIoUtil.writeFile(file, getVlanScripts(), false);
        FileIoUtil.setFileExec(file);
    }

    /**
     * 创建vlan
     * @param ip
     * @param vlan
     * @return
     */
    public static String createVlan(String ip, String vlan){
        if (vlan == null || vlan.length() < 1){
            return "no vlan";
        }
        writeScripts(ip);
        StringBuilder cmds = new StringBuilder();
        cmds.append("/dev/shm/vlan.sh.")
                .append(ip)
                .append(" ")
                .append(vlan);
        String cmd = cmds.toString();
        String result = RunCommandUtil.runScript(cmd);
        return result.concat("ok");
    }
}
