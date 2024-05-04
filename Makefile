#!/bin/bash
TODAY := $(shell date +'%Y%m%d')
OS := $(shell uname)

help: ## Show this help message
	@echo "usage: make [target]"
	@echo
	@echo "targets:"
	@egrep "^(.+)\:\ ##\ (.+)" ${MAKEFILE_LIST} | column -t -c 2 -s ":#"

gitpush: ## git push
	clear;
	git add .; git commit -m "$(m)"; git push;

destroy:
	docker-compose -f docker-compose.yml down

rebuild-be: ## rebuild web image (nginx)
	docker-compose up -d --no-deps --force-recreate --build balance_be
	docker ps

logs-be: ## shows logs be
	docker logs cont-balance-java

rebuild-all: ## rebuild all services containers in docker-compose
	docker-compose -f docker-compose.yml down
	docker-compose -f docker-compose.yml up -d --build --remove-orphans
	docker ps

ssh-be: ## ssh be
	docker exec -it --user root cont-balance-java bash

ssh-db: ## ssh be
	docker exec -it --user root cont-balance-mysql bash