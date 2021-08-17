#!/bin/zsh

build:
	./mvnw clean package -Dmaven.test.skip=true

run:
	./mvnw spring-boot:run

test:
	./mvnw clean test-compile

dc-up:
	docker-compose up -d -r .

dc-down:
	docker-compose down