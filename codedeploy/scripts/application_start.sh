#!/usr/bin/env bash

# 실행중인 컨테이너 확인
running=$(docker ps -a --format "{{.ID}}" --filter "name=todo-cd" | wc -l)
# 존재하면 중단
if [ $running -eq 1 ]; then
  docker stop todo-cd
fi

# S3에서 .env 파일 다운로드
aws s3 cp s3://toucheese-team2-s3/env env

# 최신 이미지를 pull
docker pull toucheeseteam2/toucheese:latest

# 컨테이너 실행 (다운로드한 env 파일을 --env-file로 사용)
docker run --env-file env -d --name toucheese-cd -p 8080:8080 toucheeseteam2/toucheese:latest