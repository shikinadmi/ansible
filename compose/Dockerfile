# Создание Image с необходимым окружением ansible

ARG BASE_IMAGE=arm64v8/fedora

FROM ${BASE_IMAGE}
WORKDIR /root
LABEL maintainer='shikin.a.dmi@icloud.com'
LABEL description='ansible_arm64'
ENTRYPOINT ["bash"]
RUN dnf install ansible unzip bzip2 vim postgresql-server python-psycopg2 -y && dnf clean all

