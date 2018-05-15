FROM centos:7

RUN curl -L \
  -o java.tar.gz \
  https://download.java.net/java/GA/jdk10/10/binaries/openjdk-10_linux-x64_bin.tar.gz && \
  mkdir -p /usr/lib/jvm && tar xzf java.tar.gz -C /usr/lib/jvm && \
  rm java.tar.gz

ADD src/ /tmp/src
