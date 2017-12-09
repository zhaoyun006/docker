package com.cloud.agent.util.docker;

import com.cloud.agent.util.FileIoUtil;

import java.io.File;

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
 * @since 1.0
 */

public class PipeworkScripts {

    private static final String scripts = "#!/bin/sh\n" +
            "set -e\n" +
            "\n" +
            "case \"$1\" in\n" +
            "  --wait)\n" +
            "    WAIT=1\n" +
            "    ;;\n" +
            "  --direct-phys)\n" +
            "    DIRECT_PHYS=1\n" +
            "    shift\n" +
            "    ;;\n" +
            "esac\n" +
            "\n" +
            "IFNAME=$1\n" +
            "\n" +
            "# default value set further down if not set here\n" +
            "CONTAINER_IFNAME=\n" +
            "if [ \"$2\" = \"-i\" ]; then\n" +
            "  CONTAINER_IFNAME=$3\n" +
            "  shift 2\n" +
            "fi\n" +
            "\n" +
            "if [ \"$2\" = \"-l\" ]; then\n" +
            "  LOCAL_IFNAME=$3\n" +
            "  shift 2\n" +
            "fi\n" +
            "\n" +
            "#inet or inet6\n" +
            "FAMILY_FLAG=\"-4\"\n" +
            "if [ \"$2\" = \"-a\" ]; then\n" +
            "  FAMILY_FLAG=\"-$3\"\n" +
            "  shift 2\n" +
            "fi\n" +
            "\n" +
            "GUESTNAME=$2\n" +
            "IPADDR=$3\n" +
            "MACADDR=$4\n" +
            "\n" +
            "case \"$MACADDR\" in\n" +
            "  *@*)\n" +
            "    VLAN=\"${MACADDR#*@}\"\n" +
            "    VLAN=\"${VLAN%%@*}\"\n" +
            "    MACADDR=\"${MACADDR%%@*}\"\n" +
            "    ;;\n" +
            "  *)\n" +
            "    VLAN=\n" +
            "    ;;\n" +
            "esac\n" +
            "\n" +
            "# did they ask to generate a custom MACADDR?\n" +
            "# generate the unique string\n" +
            "case \"$MACADDR\" in\n" +
            "  U:*)\n" +
            "    macunique=\"${MACADDR#*:}\"\n" +
            "    # now generate a 48-bit hash string from $macunique\n" +
            "    MACADDR=$(echo $macunique|md5sum|sed 's/^\\(..\\)\\(..\\)\\(..\\)\\(..\\)\\(..\\).*$/02:\\1:\\2:\\3:\\4:\\5/')\n" +
            "   ;;\n" +
            "esac\n" +
            "\n" +
            "\n" +
            "[ \"$IPADDR\" ] || [ \"$WAIT\" ] || {\n" +
            "  echo \"Syntax:\"\n" +
            "  echo \"pipework <hostinterface> [-i containerinterface] [-l localinterfacename] [-a addressfamily] <guest> <ipaddr>/<subnet>[@default_gateway] [macaddr][@vlan]\"\n" +
            "  echo \"pipework <hostinterface> [-i containerinterface] [-l localinterfacename] <guest> dhcp [macaddr][@vlan]\"\n" +
            "  echo \"pipework route <guest> <route_command>\"\n" +
            "  echo \"pipework rule <guest> <rule_command>\"\n" +
            "  echo \"pipework tc <guest> <tc_command>\"\n" +
            "  echo \"pipework --wait [-i containerinterface]\"\n" +
            "  exit 1\n" +
            "}\n" +
            "\n" +
            "# Succeed if the given utility is installed. Fail otherwise.\n" +
            "# For explanations about `which` vs `type` vs `command`, see:\n" +
            "# http://stackoverflow.com/questions/592620/check-if-a-program-exists-from-a-bash-script/677212#677212\n" +
            "# (Thanks to @chenhanxiao for pointing this out!)\n" +
            "installed () {\n" +
            "  command -v \"$1\" >/dev/null 2>&1\n" +
            "}\n" +
            "\n" +
            "# Google Styleguide says error messages should go to standard error.\n" +
            "warn () {\n" +
            "  echo \"$@\" >&2\n" +
            "}\n" +
            "die () {\n" +
            "  status=\"$1\"\n" +
            "  shift\n" +
            "  warn \"$@\"\n" +
            "  exit \"$status\"\n" +
            "}\n" +
            "\n" +
            "# First step: determine type of first argument (bridge, physical interface...),\n" +
            "# Unless \"--wait\" is set (then skip the whole section)\n" +
            "if [ -z \"$WAIT\" ]; then\n" +
            "  if [ -d \"/sys/class/net/$IFNAME\" ]\n" +
            "  then\n" +
            "    if [ -d \"/sys/class/net/$IFNAME/bridge\" ]; then\n" +
            "      IFTYPE=bridge\n" +
            "      BRTYPE=linux\n" +
            "    elif installed ovs-vsctl && ovs-vsctl list-br|grep -q \"^${IFNAME}$\"; then\n" +
            "      IFTYPE=bridge\n" +
            "      BRTYPE=openvswitch\n" +
            "    elif [ \"$(cat \"/sys/class/net/$IFNAME/type\")\" -eq 32 ]; then # InfiniBand IPoIB interface type 32\n" +
            "      IFTYPE=ipoib\n" +
            "      # The IPoIB kernel module is fussy, set device name to ib0 if not overridden\n" +
            "      CONTAINER_IFNAME=${CONTAINER_IFNAME:-ib0}\n" +
            "      PKEY=$VLAN\n" +
            "    else IFTYPE=phys\n" +
            "    fi\n" +
            "  else\n" +
            "    case \"$IFNAME\" in\n" +
            "      br*)\n" +
            "        IFTYPE=bridge\n" +
            "        BRTYPE=linux\n" +
            "        ;;\n" +
            "      ovs*)\n" +
            "        if ! installed ovs-vsctl; then\n" +
            "          die 1 \"Need OVS installed on the system to create an ovs bridge\"\n" +
            "        fi\n" +
            "        IFTYPE=bridge\n" +
            "        BRTYPE=openvswitch\n" +
            "        ;;\n" +
            "      route*)\n" +
            "        IFTYPE=route\n" +
            "        ;;\n" +
            "      rule*)\n" +
            "        IFTYPE=rule\n" +
            "        ;;\n" +
            "      tc*)\n" +
            "        IFTYPE=tc\n" +
            "        ;;\n" +
            "      dummy*)\n" +
            "        IFTYPE=dummy\n" +
            "        ;;\n" +
            "      *) die 1 \"I do not know how to setup interface $IFNAME.\" ;;\n" +
            "    esac\n" +
            "  fi\n" +
            "fi\n" +
            "\n" +
            "# Set the default container interface name to eth1 if not already set\n" +
            "CONTAINER_IFNAME=${CONTAINER_IFNAME:-eth1}\n" +
            "\n" +
            "[ \"$WAIT\" ] && {\n" +
            "  while true; do\n" +
            "    # This first method works even without `ip` or `ifconfig` installed,\n" +
            "    # but doesn't work on older kernels (e.g. CentOS 6.X). See #128.\n" +
            "    grep -q '^1$' \"/sys/class/net/$CONTAINER_IFNAME/carrier\" && break\n" +
            "    # This method hopefully works on those older kernels.\n" +
            "    ip link ls dev \"$CONTAINER_IFNAME\" && break\n" +
            "    sleep 1\n" +
            "  done > /dev/null 2>&1\n" +
            "  exit 0\n" +
            "}\n" +
            "\n" +
            "[ \"$IFTYPE\" = bridge ] && [ \"$BRTYPE\" = linux ] && [ \"$VLAN\" ] && {\n" +
            "  die 1 \"VLAN configuration currently unsupported for Linux bridge.\"\n" +
            "}\n" +
            "\n" +
            "[ \"$IFTYPE\" = ipoib ] && [ \"$MACADDR\" ] && {\n" +
            "  die 1 \"MACADDR configuration unsupported for IPoIB interfaces.\"\n" +
            "}\n" +
            "\n" +
            "# Second step: find the guest (for now, we only support LXC containers)\n" +
            "while read _ mnt fstype options _; do\n" +
            "  [ \"$fstype\" != \"cgroup\" ] && continue\n" +
            "  echo \"$options\" | grep -qw devices || continue\n" +
            "  CGROUPMNT=$mnt\n" +
            "done < /proc/mounts\n" +
            "\n" +
            "[ \"$CGROUPMNT\" ] || {\n" +
            "    die 1 \"Could not locate cgroup mount point.\"\n" +
            "}\n" +
            "\n" +
            "# Try to find a cgroup matching exactly the provided name.\n" +
            "N=$(find \"$CGROUPMNT\" -name \"$GUESTNAME\" | wc -l)\n" +
            "case \"$N\" in\n" +
            "  0)\n" +
            "    # If we didn't find anything, try to lookup the container with Docker.\n" +
            "    if installed docker; then\n" +
            "      RETRIES=3\n" +
            "      while [ \"$RETRIES\" -gt 0 ]; do\n" +
            "        DOCKERPID=$(docker inspect --format='{{ .State.Pid }}' \"$GUESTNAME\")\n" +
            "        [ \"$DOCKERPID\" != 0 ] && break\n" +
            "        sleep 1\n" +
            "        RETRIES=$((RETRIES - 1))\n" +
            "      done\n" +
            "\n" +
            "      [ \"$DOCKERPID\" = 0 ] && {\n" +
            "        die 1 \"Docker inspect returned invalid PID 0\"\n" +
            "      }\n" +
            "\n" +
            "      [ \"$DOCKERPID\" = \"<no value>\" ] && {\n" +
            "        die 1 \"Container $GUESTNAME not found, and unknown to Docker.\"\n" +
            "      }\n" +
            "    else\n" +
            "      die 1 \"Container $GUESTNAME not found, and Docker not installed.\"\n" +
            "    fi\n" +
            "    ;;\n" +
            "  1) true ;;\n" +
            "  *) die 1 \"Found more than one container matching $GUESTNAME.\" ;;\n" +
            "esac\n" +
            "\n" +
            "# only check IPADDR if we are not in a route mode\n" +
            "[ \"$IFTYPE\" != route ] && [ \"$IFTYPE\" != rule ] && [ \"$IFTYPE\" != tc ] && {\n" +
            "  case \"$IPADDR\" in\n" +
            "          # Let's check first if the user asked for DHCP allocation.\n" +
            "          dhcp|dhcp:*)\n" +
            "            # Use Docker-specific strategy to run the DHCP client\n" +
            "            # from the busybox image, in the network namespace of\n" +
            "            # the container.\n" +
            "            if ! [ \"$DOCKERPID\" ]; then\n" +
            "              warn \"You asked for a Docker-specific DHCP method.\"\n" +
            "              warn \"However, $GUESTNAME doesn't seem to be a Docker container.\"\n" +
            "              warn \"Try to replace 'dhcp' with another option?\"\n" +
            "              die 1 \"Aborting.\"\n" +
            "            fi\n" +
            "            DHCP_CLIENT=${IPADDR%%:*}\n" +
            "            ;;\n" +
            "          udhcpc|udhcpc:*|udhcpc-f|udhcpc-f:*|dhcpcd|dhcpcd:*|dhclient|dhclient:*|dhclient-f|dhclient-f:*)\n" +
            "            DHCP_CLIENT=${IPADDR%%:*}\n" +
            "            # did they ask for the client to remain?\n" +
            "            DHCP_FOREGROUND=\n" +
            "            [ \"${DHCP_CLIENT: -2}\" = '-f' ] && {\n" +
            "              DHCP_FOREGROUND=true\n" +
            "            }\n" +
            "            DHCP_CLIENT=${DHCP_CLIENT%-f}\n" +
            "            if ! installed \"$DHCP_CLIENT\"; then\n" +
            "              die 1 \"You asked for DHCP client $DHCP_CLIENT, but I can't find it.\"\n" +
            "            fi\n" +
            "            ;;\n" +
            "          # Alright, no DHCP? Then let's see if we have a subnet *and* gateway.\n" +
            "          */*@*)\n" +
            "            GATEWAY=\"${IPADDR#*@}\" GATEWAY=\"${GATEWAY%%@*}\"\n" +
            "            IPADDR=\"${IPADDR%%@*}\"\n" +
            "            ;;\n" +
            "          # No gateway? We need at least a subnet, anyway!\n" +
            "          */*) : ;;\n" +
            "          # ... No? Then stop right here.\n" +
            "          *)\n" +
            "            warn \"The IP address should include a netmask.\"\n" +
            "            die 1 \"Maybe you meant $IPADDR/24 ?\"\n" +
            "            ;;\n" +
            "  esac\n" +
            "}\n" +
            "\n" +
            "# If a DHCP method was specified, extract the DHCP options.\n" +
            "if [ \"$DHCP_CLIENT\" ]; then\n" +
            "  case \"$IPADDR\" in\n" +
            "    *:*) DHCP_OPTIONS=\"${IPADDR#*:}\" ;;\n" +
            "  esac\n" +
            "fi\n" +
            "\n" +
            "if [ \"$DOCKERPID\" ]; then\n" +
            "  NSPID=$DOCKERPID\n" +
            "else\n" +
            "  NSPID=$(head -n 1 \"$(find \"$CGROUPMNT\" -name \"$GUESTNAME\" | head -n 1)/tasks\")\n" +
            "  [ \"$NSPID\" ] || {\n" +
            "    # it is an alternative way to get the pid\n" +
            "    NSPID=$(lxc-info -n  \"$GUESTNAME\" | grep PID | grep -Eo '[0-9]+')\n" +
            "    [ \"$NSPID\" ] || {\n" +
            "      die 1 \"Could not find a process inside container $GUESTNAME.\"\n" +
            "    }\n" +
            "  }\n" +
            "fi\n" +
            "\n" +
            "# Check if an incompatible VLAN device already exists\n" +
            "[ \"$IFTYPE\" = phys ] && [ \"$VLAN\" ] && [ -d \"/sys/class/net/$IFNAME.VLAN\" ] && {\n" +
            "  ip -d link show \"$IFNAME.$VLAN\" | grep -q \"vlan.*id $VLAN\" || {\n" +
            "    die 1 \"$IFNAME.VLAN already exists but is not a VLAN device for tag $VLAN\"\n" +
            "  }\n" +
            "}\n" +
            "\n" +
            "[ ! -d /var/run/netns ] && mkdir -p /var/run/netns\n" +
            "rm -f \"/var/run/netns/$NSPID\"\n" +
            "ln -s \"/proc/$NSPID/ns/net\" \"/var/run/netns/$NSPID\"\n" +
            "\n" +
            "# Check if we need to create a bridge.\n" +
            "[ \"$IFTYPE\" = bridge ] && [ ! -d \"/sys/class/net/$IFNAME\" ] && {\n" +
            "  [ \"$BRTYPE\" = linux ] && {\n" +
            "    (ip link add dev \"$IFNAME\" type bridge > /dev/null 2>&1) || (brctl addbr \"$IFNAME\")\n" +
            "    ip link set \"$IFNAME\" up\n" +
            "  }\n" +
            "  [ \"$BRTYPE\" = openvswitch ] && {\n" +
            "    ovs-vsctl add-br \"$IFNAME\"\n" +
            "  }\n" +
            "}\n" +
            "\n" +
            "[ \"$IFTYPE\" != \"route\" ] && [ \"$IFTYPE\" != \"dummy\" ] && [ \"$IFTYPE\" != \"rule\" ] && [ \"$IFTYPE\" != \"tc\" ] && MTU=$(ip link show \"$IFNAME\" | awk '{print $5}')\n" +
            "\n" +
            "# If it's a bridge, we need to create a veth pair\n" +
            "[ \"$IFTYPE\" = bridge ] && {\n" +
            "  if [ -z \"$LOCAL_IFNAME\" ]; then\n" +
            "    LOCAL_IFNAME=\"v${CONTAINER_IFNAME}pl${NSPID}\"\n" +
            "  fi\n" +
            "  GUEST_IFNAME=\"v${CONTAINER_IFNAME}pg${NSPID}\"\n" +
            "  # Does the link already exist?\n" +
            "  if ip link show \"$LOCAL_IFNAME\" >/dev/null 2>&1; then\n" +
            "    # link exists, is it in use?\n" +
            "    if ip link show \"$LOCAL_IFNAME\" up | grep -q \"UP\"; then\n" +
            "      echo \"Link $LOCAL_IFNAME exists and is up\"\n" +
            "      exit 1\n" +
            "    fi\n" +
            "    # delete the link so we can re-add it afterwards\n" +
            "    ip link del \"$LOCAL_IFNAME\"\n" +
            "  fi\n" +
            "  ip link add name \"$LOCAL_IFNAME\" mtu \"$MTU\" type veth peer name \"$GUEST_IFNAME\" mtu \"$MTU\"\n" +
            "  case \"$BRTYPE\" in\n" +
            "    linux)\n" +
            "      (ip link set \"$LOCAL_IFNAME\" master \"$IFNAME\" > /dev/null 2>&1) || (brctl addif \"$IFNAME\" \"$LOCAL_IFNAME\")\n" +
            "      ;;\n" +
            "    openvswitch)\n" +
            "      if ! ovs-vsctl list-ports \"$IFNAME\" | grep -q \"^${LOCAL_IFNAME}$\"; then\n" +
            "        ovs-vsctl add-port \"$IFNAME\" \"$LOCAL_IFNAME\" ${VLAN:+tag=\"$VLAN\"}\n" +
            "      fi\n" +
            "      ;;\n" +
            "  esac\n" +
            "  ip link set \"$LOCAL_IFNAME\" up\n" +
            "}\n" +
            "\n" +
            "# If it's a physical interface, create a macvlan subinterface\n" +
            "[ \"$IFTYPE\" = phys ] && {\n" +
            "  [ \"$VLAN\" ] && {\n" +
            "    [ ! -d \"/sys/class/net/${IFNAME}.${VLAN}\" ] && {\n" +
            "      ip link add link \"$IFNAME\" name \"$IFNAME.$VLAN\" mtu \"$MTU\" type vlan id \"$VLAN\"\n" +
            "    }\n" +
            "    ip link set \"$IFNAME\" up\n" +
            "    IFNAME=$IFNAME.$VLAN\n" +
            "  }\n" +
            "\n" +
            "  if [ ! -z \"$DIRECT_PHYS\" ]; then\n" +
            "    GUEST_IFNAME=$IFNAME\n" +
            "  else\n" +
            "    GUEST_IFNAME=ph$NSPID$CONTAINER_IFNAME\n" +
            "    ip link add link \"$IFNAME\" dev \"$GUEST_IFNAME\" mtu \"$MTU\" type macvlan mode bridge\n" +
            "  fi\n" +
            "\n" +
            "  ip link set \"$IFNAME\" up\n" +
            "}\n" +
            "\n" +
            "# If it's an IPoIB interface, create a virtual IPoIB interface (the IPoIB\n" +
            "# equivalent of a macvlan device)\n" +
            "#\n" +
            "# Note: no macvlan subinterface nor Ethernet bridge can be created on top of an\n" +
            "# IPoIB interface. InfiniBand is not Ethernet. IPoIB is an IP layer on top of\n" +
            "# InfiniBand, without an intermediate Ethernet layer.\n" +
            "[ \"$IFTYPE\" = ipoib ] && {\n" +
            "  GUEST_IFNAME=\"${IFNAME}.${NSPID}\"\n" +
            "\n" +
            "  # If a partition key is provided, use it\n" +
            "  [ \"$PKEY\" ] && {\n" +
            "    GUEST_IFNAME=\"${IFNAME}.${PKEY}.${NSPID}\"\n" +
            "    PKEY=\"pkey 0x$PKEY\"\n" +
            "  }\n" +
            "\n" +
            "  ip link add link \"$IFNAME\" name \"$GUEST_IFNAME\" type ipoib $PKEY\n" +
            "  ip link set \"$IFNAME\" up\n" +
            "}\n" +
            "\n" +
            "# If its a dummy interface, create a dummy interface.\n" +
            "[ \"$IFTYPE\" = dummy ] && {\n" +
            "  GUEST_IFNAME=du$NSPID$CONTAINER_IFNAME\n" +
            "  ip link add dev \"$GUEST_IFNAME\" type dummy\n" +
            "}\n" +
            "\n" +
            "# If the `route` command was specified ...\n" +
            "if [ \"$IFTYPE\" = route ]; then\n" +
            "  # ... discard the first two arguments and pass the rest to the route command.\n" +
            "  shift 2\n" +
            "  ip netns exec \"$NSPID\" ip route \"$@\"\n" +
            "elif [ \"$IFTYPE\" = rule ] ; then\n" +
            "  shift 2\n" +
            "  ip netns exec \"$NSPID\" ip rule \"$@\"\n" +
            "elif [ \"$IFTYPE\" = tc ] ; then\n" +
            "  shift 2\n" +
            "  ip netns exec \"$NSPID\" tc \"$@\"\n" +
            "else\n" +
            "  # Otherwise, run normally.\n" +
            "  ip link set \"$GUEST_IFNAME\" netns \"$NSPID\"\n" +
            "  ip netns exec \"$NSPID\" ip link set \"$GUEST_IFNAME\" name \"$CONTAINER_IFNAME\"\n" +
            "  [ \"$MACADDR\" ] && ip netns exec \"$NSPID\" ip link set dev \"$CONTAINER_IFNAME\" address \"$MACADDR\"\n" +
            "\n" +
            "        # When using any of the DHCP methods, we start a DHCP client in the\n" +
            "        # network namespace of the container. With the 'dhcp' method, the\n" +
            "        # client used is taken from the Docker busybox image (therefore\n" +
            "        # requiring no specific client installed on the host). Other methods\n" +
            "        # use a locally installed client.\n" +
            "        case \"$DHCP_CLIENT\" in\n" +
            "          dhcp)\n" +
            "            docker run -d --net container:$GUESTNAME --cap-add NET_ADMIN \\\n" +
            "                   busybox udhcpc -i \"$CONTAINER_IFNAME\" -x \"hostname:$GUESTNAME\" \\\n" +
            "                   $DHCP_OPTIONS \\\n" +
            "                   >/dev/null\n" +
            "            ;;\n" +
            "          udhcpc)\n" +
            "            DHCP_Q=\"-q\"\n" +
            "            [ \"$DHCP_FOREGROUND\" ] && {\n" +
            "              DHCP_OPTIONS=\"$DHCP_OPTIONS -f\"\n" +
            "            }\n" +
            "            ip netns exec \"$NSPID\" \"$DHCP_CLIENT\" -qi \"$CONTAINER_IFNAME\" \\\n" +
            "                                                  -x \"hostname:$GUESTNAME\" \\\n" +
            "                                                  -p \"/var/run/udhcpc.$GUESTNAME.pid\" \\\n" +
            "                                                  $DHCP_OPTIONS\n" +
            "            [ ! \"$DHCP_FOREGROUND\" ] && {\n" +
            "              rm \"/var/run/udhcpc.$GUESTNAME.pid\"\n" +
            "            }\n" +
            "            ;;\n" +
            "          dhclient)\n" +
            "            ip netns exec \"$NSPID\" \"$DHCP_CLIENT\" \"$CONTAINER_IFNAME\" \\\n" +
            "                                                  -pf \"/var/run/dhclient.$GUESTNAME.pid\" \\\n" +
            "                                                  -lf \"/etc/dhclient/dhclient.$GUESTNAME.leases\" \\\n" +
            "                                                  $DHCP_OPTIONS\n" +
            "            # kill dhclient after get ip address to prevent device be used after container close\n" +
            "            [ ! \"$DHCP_FOREGROUND\" ] && {\n" +
            "              kill \"$(cat \"/var/run/dhclient.$GUESTNAME.pid\")\"\n" +
            "              rm \"/var/run/dhclient.$GUESTNAME.pid\"\n" +
            "            }\n" +
            "            ;;\n" +
            "          dhcpcd)\n" +
            "            ip netns exec \"$NSPID\" \"$DHCP_CLIENT\" -q \"$CONTAINER_IFNAME\" -h \"$GUESTNAME\"\n" +
            "            ;;\n" +
            "          \"\")\n" +
            "            if installed ipcalc; then\n" +
            "              eval $(ipcalc -b $IPADDR)\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" addr add \"$IPADDR\" brd \"$BROADCAST\" dev \"$CONTAINER_IFNAME\"\n" +
            "            else\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" addr add \"$IPADDR\" dev \"$CONTAINER_IFNAME\"\n" +
            "            fi\n" +
            "\n" +
            "            [ \"$GATEWAY\" ] && {\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" route delete default >/dev/null 2>&1 && true\n" +
            "            }\n" +
            "            ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" link set \"$CONTAINER_IFNAME\" up\n" +
            "            [ \"$GATEWAY\" ] && {\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" route get \"$GATEWAY\" >/dev/null 2>&1 || \\\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" route add \"$GATEWAY/32\" dev \"$CONTAINER_IFNAME\"\n" +
            "              ip netns exec \"$NSPID\" ip \"$FAMILY_FLAG\" route replace default via \"$GATEWAY\" dev \"$CONTAINER_IFNAME\"\n" +
            "            }\n" +
            "            ;;\n" +
            "        esac\n" +
            "\n" +
            "  # Give our ARP neighbors a nudge about the new interface\n" +
            "  if installed arping; then\n" +
            "    IPADDR=$(echo \"$IPADDR\" | cut -d/ -f1)\n" +
            "    ip netns exec \"$NSPID\" arping -c 1 -A -I \"$CONTAINER_IFNAME\" \"$IPADDR\" > /dev/null 2>&1 || true\n" +
            "  else\n" +
            "    echo \"Warning: arping not found; interface may not be immediately reachable\"\n" +
            "  fi\n" +
            "fi\n" +
            "# Remove NSPID to avoid `ip netns` catch it.\n" +
            "rm -f \"/var/run/netns/$NSPID\"\n" +
            "\n" +
            "# vim: set tabstop=2 shiftwidth=2 softtabstop=2 expandtab :";

    /**
     * 将pipework写入到系统命令
     */
    public static void writeScripts(){
        String file = "/bin/pipework";
        if(!new File(file).exists()) {
            FileIoUtil.writeFile(file, scripts, false);
            FileIoUtil.setFileExec(file);
        }
    }
}
