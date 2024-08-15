# !/bin/bash

country_file="C:\Users\STUDENT\Desktop\gishwati-5\data-store\life-expectancy.csv"

get_country() {
    name=$1

    line=$(grep -i -w "${name}" "$country_file")

    if [[ $(echo "$line" | wc -l) -eq 1 ]]; then
        echo "$line"
    else
        echo "invalid country"
        exit 2
    fi
}

get_country $1