#!/usr/bin/env sh

export LAMT="LAMT"

# проверка переменной LAMT
if [[ "$LAMT" = "LAMT" ]]; then
  echo "LAMT is set"
else
  echo "LAMT is not set"
fi