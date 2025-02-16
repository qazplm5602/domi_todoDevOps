FROM node:22.14.0

WORKDIR /domi
EXPOSE 3000

COPY . .

# SSH 설치 ㄱㄱ
RUN apt-get update && apt-get install -y openssh-server

# ssh 설정
RUN mkdir /var/run/sshd
RUN echo 'root:domi1234!' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# 실행 ㄱㄱㄱ
CMD [ "start.sh" ]