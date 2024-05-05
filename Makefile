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

debug: ## tail logs/debug.log
	rm -f logs/debug-$(TODAY).log
	touch logs/debug-$(TODAY).log
	clear
	tail -f logs/debug-$(TODAY).log

error: ## tail logs/error.log
	rm -f logs/error-$(TODAY).log
	touch logs/error-$(TODAY).log
	clear
	tail -f logs/error-$(TODAY).log

logs-be: ## shows logs be
	docker logs cont-balance-java

rebuild-all: ## rebuild all services containers in docker-compose
	cp target/*.jar docker/balance_be
	docker-compose -f docker-compose.yml down
	docker-compose -f docker-compose.yml up -d --build --remove-orphans
	docker ps

ssh-be: ## ssh be
	docker exec -it --user root cont-balance-java bash

ssh-db: ## ssh db
	docker exec -it --user root cont-balance-mysql bash

health-be: ## health get-products
	curl http://localhost:8085/get-products
