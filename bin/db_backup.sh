#!/usr/bin/env bash
DATABASE_NAME=share-$(date +%Y%m%d%H%M)
DATABASE_BACKUP_DIR="/home/work/mysql_backup_dir"
DIR="$(cd "$(dirname "$0")";pwd)"

echo "文件名称${DATABASE_NAME}"
echo "备份目录${DATABASE_BACKUP_DIR}"
echo "当前脚本目录${DIR}"

echo "访问备份目录"
if [ ! -d ${DATABASE_BACKUP_DIR} ]; then
  mkdir ${DATABASE_BACKUP_DIR}
else
  echo "文件夹已经存在"
  cd /home/work/mysql_backup_dir
fi

echo "导出SQL"
mysqldump -uroot -p'1&NbhG*Lw7%6Ftla' share >${DATABASE_NAME}.sql

#加密压缩
#p=`</dev/urandom tr -dc '!@#$%^&*?_A-Z-a-z-0-9' | head -c32; echo ""`
#nice zip -1 --password "$p" ${DATABASE_NAME}.sql.tar.gz ${DATABASE_NAME}.sql

echo "压缩SQL"
nice zip -1 ${DATABASE_NAME}.sql.tar.gz ${DATABASE_NAME}.sql

echo "删除原始SQL"
rm -rf ${DATABASE_NAME}.sql

echo "上传压缩文件"
${DIR}/ossutil64 cp ${DATABASE_NAME}.sql.tar.gz oss://share-db-backup/ --config-file=${DIR}/ossuitlconfig

echo "删除压缩文件，完成"
rm -rf ${DATABASE_NAME}.sql.tar.gz
