#!/usr/bin/env bash

# ssh 시작
/usr/sbin/sshd

# 패키지 설치 ㅁㄴㅇㄹ
npm i

# next 빌드 ㄱㄱ
npm run build

# 실행
exec npm run start