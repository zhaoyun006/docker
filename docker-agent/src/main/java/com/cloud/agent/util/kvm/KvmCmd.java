package com.cloud.agent.util.kvm;

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

public class KvmCmd {

    // 获取运行的kvm数量
    public static String getRunningVm = getConf("getRunningVm", 2 ,"virsh list --all |grep running |wc -l");

    // 关闭虚拟机
    public static String shutdownVm = getConf("shutdownVm", 2 ,"virsh destroy {0};");

    // 获取内存使用率
    public static String memoryUsedPercent = getConf("memoryUsedPercent", 2 ,"free -m|awk '$0 ~ /Mem/ {OFMT=\"%.2f\" ; print ($3/$2)*100}'");

    // 获取CPU使用率
    public static String cpuUsedPercent = getConf("cpuUsedPercent", 2 ,"sar 1 2 |tail -n1|awk '{print 100-$NF}'");

    // 获取cpu使用率
    public static String cpuUsed = cpuUsedPercent;

    // 获取磁盘使用详情
    public static String diskUsed = getConf("diskUsed", 2 ,"df -lh /var/lib/libvirt/images/ |awk '$0 ~ / \\//{print $(NF-1)\",\"$(NF-4)\",\"$(NF-2)}'");

    // 获取内存使用详情
    public static String memoryUsed = getConf("memoryUsed", 2 ,"free -m |grep Mem |awk '{OFMT=\"%.2f\"; print $3/$2*100\",\"$2\"M,\"$4\"M\"}';");

    // 删除虚拟机
    public static String removeVm = getConf("", 2 ,"cp /etc/libvirt/qemu/{0}.xml /etc/libvirt/qemu/{0}.xml.delete;" +
            " virsh destroy {0} 2>&1|grep destroy >/dev/null;" +
            " virsh undefine {0};" +
            " mv /var/lib/libvirt/images/{0}.qcow2  /var/lib/libvirt/images/{0}.qcow2.delete; ");

    // 启动虚拟机
    public static String startVm = getConf("startVm", 2 ,"virsh start {0} 2>&1|grep start >/dev/null ; " +
            "     virsh autostart {0};");

    // 暂停虚拟机
    public static String supendVm = getConf("supendVm", 2 ,"virsh suspend {0} 2>&1|grep suspend >/dev/null");

    // 恢复虚拟机
    public static String resumeVm = getConf("resumeVm", 2 ,"virsh resume {0} 2>&1 |grep resume >/dev/null");

    // 修改内存大小
    public static String setMemory = getConf("setMemory", 2 ,"/etc/libvirt/qemu/{0}.xml;" +
            "    virsh setmem {0} {1} --live --config 2>&1;" +
            "    virsh setmaxmem {0} {1} --config;" +
            "    sed -i \"s#<memory.*</memory>#<memory unit='KiB'>{1}</memory>#\" /etc/libvirt/qemu/{0}.xml; " +
            "    sed -i \"s#<currentMemory.*</currentMemory>#<currentMemory unit='KiB'>{1}</currentMemory>#\" /etc/libvirt/qemu/{0}.xml; " +
            "    virsh define /etc/libvirt/qemu/{0}.xml");

    // 修改cpu大小
    public static String setCpu = getConf("setCpu", 2 ,"virsh  setvcpus  {0} {1} --live --config;" +
            "    sed -i 's#<vcpu .*</vcpu>#  <vcpu placement='static' current='{1}'>32</vcpu>#' /etc/libvirt/qemu/{0}.xml;" +
            "    virsh define /etc/libvirt/qemu/{0}.xml;");

    // 获取镜像是否已经在本地
    public static String imagesExist = getConf("imagesExist", 2, "a=$(curl {0} -I 2>/dev/null |awk '$0 ~ /Content-Length/ {print $2}'|sed 's/\\r//g');" +
            "b=$(ls -l  /var/lib/libvirt/images/{1} 2>/dev/null|awk '{print $5}');" +
            "[ $a == $b ] && echo {1}");

    // 下载镜像到本地
    public static String downloadImage = getConf("downloadImage", 2, "setsid curl {0} -o  /var/lib/libvirt/images/{1} ");

    // 获取密码
    public static String getPassword = getConf("getPassword", 2, "echo -n `echo '{0}' | openssl passwd -1 -salt $(< /dev/urandom tr -dc '[:alnum:]' | head -c 32) -stdin `");
}