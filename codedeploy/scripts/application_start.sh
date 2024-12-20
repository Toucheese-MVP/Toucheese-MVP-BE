#!/usr/bin/env bash

# 실행중인 컨테이너 확인
running=$(sudo docker ps -a --format "{{.ID}}" --filter "name=toucheese-cd" | wc -l)
# 존재하면 중단
if [ $running -eq 1 ]; then
  sudo docker stop toucheese-cd
  sudo docker rm toucheese-cd
fi

# 최신 이미지를 pull
sudo docker pull toucheeseteam2/toucheese:latest

# 컨테이너 실행 (다운로드한 .env 파일을 --env-file로 사용)
sudo docker run --env-file /home/ubuntu/.env -d --name toucheese-cd -p 8080:8080 toucheeseteam2/toucheese:latest