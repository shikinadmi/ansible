#!/bin/zsh
ansible "$1" -m ping && ansible-playbook playbook.yml -i inventories/"$1" -e HOSTS="$1"

#./run.sh ya_cloud