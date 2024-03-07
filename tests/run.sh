#!/bin/zsh
# shellcheck disable=SC2034
#inventory_file="inventories/$1"
if ! ansible "$1" -m ping &> /dev/null; then
  echo "Failed to ping $1"
  exit 1
fi
# Проверка возможности запуска ansible-playbook через ansible-lint
#ansible-lint playbook.yml -e HOSTS="$1" -v # run with 10 forks
ansible-playbook playbook.yml -i hosts -e HOSTS="$1" -f 10 #-v # run with 10 forks

#ansible-playbook playbook.yml -i "$inventory_file" -e HOSTS="$1" --limit "$1" -f 10 # run with 10 forks




# Описание запуска
# ./run.sh [inventorry name]
# где inventorry name - имя нашего инвентарного файла, который должен находиться в папке inventories
