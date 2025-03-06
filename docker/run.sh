docker run -it -d --name web --privileged --network=host -v /opt/deploy/bakss-server:/opt/deploy/bakss-server --workdir=/opt/deploy/bakss-server --restart=unless-stopped java:8 sh start.sh
