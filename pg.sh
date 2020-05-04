#! /bin/bash

containerName=pg-quarkus-hello

containerId=$(docker inspect -f '{{.Id}}' $containerName)

if [ -n "$containerId" ]; then
	docker start $containerName
else
	docker run -d --name $containerName \
		-v pg-quarkus-hello:/var/lib/postgresql/data \
		-e POSTGRES_PASSWORD=p4ssw0rd \
		-e POSTGRES_USER=hello \
		-p 5432:5432 \
		postgres:12.2-alpine
fi


