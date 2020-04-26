#! /bin/bash

docker run -d --name pg-quarkus-hello \
	-v pg-quarkus-hello:/var/lib/postgresql/data \
	-e POSTGRES_PASSWORD=p4ssw0rd \
	-e POSTGRES_USER=hello \
	-p 5432:5432 \
	postgres:12.2-alpine

