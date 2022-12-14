#!/bin/bash
set -ex

###########################################################
# UTILS
###########################################################

export DEBIAN_FRONTEND=noninteractive
apt-get update
apt-get install --no-install-recommends -y tzdata ca-certificates net-tools libxml2-utils git curl libudev1 libxml2-utils iptables iproute2 jq unzip
ln -fs /usr/share/zoneinfo/UTC /etc/localtime
dpkg-reconfigure --frontend noninteractive tzdata
rm -rf /var/lib/apt/lists/*

curl https://raw.githubusercontent.com/spring-io/concourse-java-scripts/v0.0.4/concourse-java.sh > /opt/concourse-java.sh

curl --output /opt/concourse-release-scripts.jar https://repo.spring.io/release/io/spring/concourse/releasescripts/concourse-release-scripts/0.3.1/concourse-release-scripts-0.3.1.jar

###########################################################
# JAVA
###########################################################

mkdir -p /opt/openjdk
cd /opt/openjdk
curl -L $1 | tar zx --strip-components=1
test -f /opt/openjdk/bin/java
test -f /opt/openjdk/bin/javac