# !/bin/bash

if [ ! -f ../data-store/user-store.txt ]; then
    echo "email,password,uuid,first_name,last_name,hiv_status,diagnosis_date,art_status,art_start_date," > ../data-store/user-store.txt
    echo "admin@lpmt.com,admin" >> ../data-store/user-store.txt
fi

generate_uuid() {
    last_uuid=$(tail -n 1 ../data-store/user-store.txt | awk -F, '{print $3}')
    if [[ $last_uuid =~ ^UUID([0-9]+)$ ]]; then
        last_number=${BASH_REMATCH[1]}
        next_number=$((last_number + 1))
        printf "UUID%03d" $next_number
    else
        echo "UUID001"
    fi
}

uuid=$(generate_uuid)

printf "$1,,$uuid\n" >> ../data-store/user-store.txt