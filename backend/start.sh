#!/bin/bash

# SSH 서버 시작
/usr/sbin/sshd

# Java 애플리케이션 실행
exec java -jar /domi/domiTodo-0.0.1-SNAPSHOT.jar