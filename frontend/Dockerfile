FROM node:22.14.0

WORKDIR /domi
EXPOSE 3000

COPY . .

# SSH 설치 ㄱㄱ
RUN apt-get update && apt-get install -y openssh-server

# ssh 설정
RUN mkdir -p /var/run/sshd

# 이겅 권한 잘 설정해야 실행됨
RUN chmod 755 /var/run/sshd

RUN echo 'root:domi1234!' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# 실행 ㄱㄱㄱ
# node 이미지는 node 가 붙여져 있어서 암튼 sh 로 해야함
CMD [ "sh", "start.sh" ]