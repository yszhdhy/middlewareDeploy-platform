#基础镜像使用
FROM java:8
#作者
MAINTAINER yszhdhy
#VOLUME 指定临时文件目录为/tmp，在主机本目录之下创建一个临时文件并连接到容器的/tmp
VOLUME /tmp
#将jar包添加到容器中并更名为  
ADD untitled.jar untitled.jar
#运行jar包
RUN bash -c 'touch /untitled.jar'
ENTRYPOINT ["java","-jar","/untitled.jar"]
#暴露端口 和springboot工程中的yml文件中的端口进行一致  注意：记得开放安全组
EXPOSE 8080