#!/bin/bash
#set -e
#sed 's/9/ZZ/g' release-ift.txt
#count=0
#for i in {1..3}; do
#  for MODULE in $(cat ./release-ift.txt); do
#      MODULE_NAME=$(echo $MODULE | awk -F: '{print $1}')
#      MODULE_VERSION=$(echo $MODULE | awk -F: '{print $2}')
#      sleep 2 ;
##      curl "https://sfera.inno.local/app/repo-distr/api/repository/sudo-docker/v2/${MODULE_NAME}/manifests/${MODULE_VERSION}"
#        --user "${USERNAME}":"${PASSWORD}" \
#         --silent \
#         --show-error \
#         -o ${MODULE_NAME}.json ;
#      JSON_CHECK=$(jq -r .schemaVersion ${MODULE_NAME}.json)
##      export JSON_CHECK="1" ;
#      if [ "$JSON_CHECK" = "1" ]; then
#            echo "Json checked - is OK"
#        else
#            echo "Json checked - is ERROR"
#            count=$((count+1))
#      fi
##      rm ${MODULE_NAME}.json
#    done
#    echo "Это попытка №$i"
#    if [ "$count" = "0" ]; then
#        echo "Json checked - is OK"
#    else
#        echo "Json checked - is ERROR"
#        exit 1
#    fi
#
#done
#
## необходимо упростить выше указанный код
##!/bin/bash
#set -e
#sed 's/9/ZZ/g' release-ift.txt
#count=0
#for i in {1..3}; do
#  for MODULE in $(cat ./release-ift.txt); do
#      MODULE_NAME=$(echo $MODULE | awk -F: '{print $1}')
#      MODULE_VERSION=$(echo $MODULE | awk -F: '{print $2}')
#      sleep 2 ;
##      curl "https://sfera.inno.local/app/repo-distr/api/repository/sudo-docker/v2/${MODULE_NAME}/manifests/${MODULE_VERSION}"    \
##      --user "${USERNAME}":"${PASSWORD}" \
##      --silent \
##      --show-error \
##      -o ${MODULE_NAME}.json ;
#      JSON_CHECK=$(jq -r .schemaVersion ${MODULE_NAME}.json)
#      if [ "$JSON_CHECK" = "1" ]; then
#            echo "Json checked - is OK"
#        else
#            echo "Json checked - is ERROR"
#            count=$((count+1))
#      fi
##      rm ${MODULE_NAME}.json
#    done
#    echo "Это попытка №$i"
#    if [ "$count" = "0" ]; then
#        echo "Json checked - is OK"
#    else
#        echo "Json checked - is ERROR"
#        exit 1
#    fi
#
#done



set -e

sed 's/9/ZZ/g' release-ift.txt > temp.txt  # сохраняем измененную строку в файле


#i=1
MODULES=$(cat ./release-ift.txt)


while IFS=: read -r MODULE; do # проходим по каждому модулю в release-ift.txt с помощью while
    MODULE_NAME=$(echo "$MODULE" | cut -d: -f1)
    MODULE_VERSION=$(echo "$MODULE" | cut -d: -f2)

    # Пробовать скачать файл json 3 раза и при неудаче выходить с ошибкой
    count=0
    for j in {1..3}; do
#      response=$(curl --silent \
#          --user "${USERNAME}":"${PASSWORD}" \
#          "https://sfera.inno.local/app/repo-distr/api/repository/sudo-docker/v2/${MODULE_NAME}/manifests/${MODULE_VERSION}")
      response=$(cat test_container.json)
      if [ $? -eq 0 ]; then
          JSON_CHECK=$(jq -r '.schemaVersion' <<< "$response")
          if [ "$JSON_CHECK" = "1" ]; then
              echo "Json Проверка - OK"
          else
              echo "Json Проверка - ERROR"
              count=$((count+1))
              sleep 2
          fi
      else
          echo "Ошибка при скачивании JSON: $? $response"
          exit 1
      fi
    done

    # Пробовать скачать файл json 3 раза и при неудаче выходить с ошибкой

    rm temp.txt  # удаляем временный файл, не используемый больше
done < temp.txt # проходим по каждому модулю в release-ift.txt с помощью while < temp.txt

#echo "Это попытка №$i"
#
#if [ "$count" = "0" ]; then
#    echo "Json checked - is OK"
#else
#    echo "Json checked - is ERROR"
#    exit 1
#fi