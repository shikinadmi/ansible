#!/bin/zsh
ansible "$1" -m ping && ansible-playbook playbook.yml -i inventories/"$1" -e HOSTS="$1"

# Описание запуска
# ./run.sh [inventorry name]
# где inventorry name - имя нашего инвентарного файла, который должен находиться в папке inventories