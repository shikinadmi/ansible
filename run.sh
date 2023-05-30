#!/bin/zsh
inventory_file="inventories/$1"
if ! ansible "$1" -m ping &> /dev/null; then
  echo "Failed to ping $1"
  exit 1
fi
ansible-playbook playbook.yml -i "$inventory_file" -e HOSTS="$1" --limit "$1" -f 10 # run with 10 forks




# Описание запуска
# ./run.sh [inventorry name]
# где inventorry name - имя нашего инвентарного файла, который должен находиться в папке inventories
