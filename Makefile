all: image uberjar

image:
	docker build --build-arg=uid=`id -u` --build-arg=gid=`id -g` -t justin2004/datething .

uberjar:
	docker run -v datething_m2:/home/containeruser/.m2 --rm -it -v `pwd`:/mnt justin2004/datething
