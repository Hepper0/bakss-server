mkdir -p logs
java -jar -server -Xms2g -Xmx4g -Duser.timezone="Asia/Shanghai" -Dfile.encoding=UTF-8 bakss-admin.jar >> logs/console.log
