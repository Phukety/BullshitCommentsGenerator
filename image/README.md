# 镜像生成
1. 在根目录执行 mvn clean package
2. 在根目录执行 docker build -t hub.x/bullshit-comments-generator:1 .
3. 启动 docker run -itd --name comments -p 8080:8080 -v /opt/app/config:/opt/app/config/ hub.x/bullshit-comments-generator:1
4. 进入进程 docker exec -it comments bash