FROM debian:10
RUN apt-get update && apt-get install -y leiningen

ARG uid=1000
ARG gid=1000
ARG user=containeruser
RUN groupadd -g $gid $user || true
RUN useradd $user --uid $uid --gid $gid --home-dir /home/$user && \
    mkdir /home/$user && \
    chown -R $uid:$gid /home/$user
USER containeruser

RUN mkdir /home/containeruser/.m2
# ^ that i have to do that feels like a docker volume bug -- maybe my laptop version is old...

CMD cd /mnt && lein uberjar
