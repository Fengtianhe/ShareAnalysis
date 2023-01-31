#!/usr/bin/bash

#定义颜色的变量
RED_COLOR='\E[1;31m'  #红
GREEN_COLOR='\E[1;32m' #绿
YELOW_COLOR='\E[1;33m' #黄
BLUE_COLOR='\E[1;34m'  #蓝
PINK='\E[1;35m'     #粉红
RES='\E[0m'

#定义当前目录
CURRENT_DIR=$(cd $(dirname $0) && pwd);
#制品jar包名
JAR_NAME="share-analysis-server"

echo "当前目录：${CURRENT_DIR}"

echo -e "${YELOW_COLOR}脚本开始执行...${RES}"

echo -e "${BLUE_COLOR}删除历史文件${RES}"

#删除除deploy.sh 和auth.sh 外的所有文件
find  . -type  f -not \( -name  'deploy.sh'  -or -name  'auth.sh' -or -name 'git.sh' \) -delete

echo -e "${GREEN_COLOR}success${RES}"

echo -e "${BLUE_COLOR}开始拉取最新代码...${RES}"

#由于spawn 和 send是expect内置命令，所以需要使用隐式调用的方式
#expect ./auth.sh
./git.sh -i /home/work/.ssh/abms_server_fth clone git@github.com:Fengtianhe/ShareAnalysis.git source

echo -e "${BLUE_COLOR}安装依赖${RES}"

cd source/

echo -e "${BLUE_COLOR}编译代码...${RES}"

mvn package

echo -e "${BLUE_COLOR}移动制品文件...${RES}"

cd ${CURRENT_DIR}

mv source/target/${JAR_NAME}.jar ./

echo -e "${BLUE_COLORE}删除源文件...${RES}"

rm -rf source

echo -e "${RED_COLOR}杀死进程${RES}"

kill -9 `cat pidfile.txt`

echo -e "${GREEN_COLOR}启动程序${RES}"

nohup java -jar ${JAR_NAME}.jar -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:./gc.log > nohup.out 2>&1 & echo $! > pidfile.txt

echo -e "${GREEN_COLOR}部署成功${RES}"
