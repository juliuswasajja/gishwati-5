# !/bin/bash

if [ ! -f C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt ]; then
    echo "email,password,role,uuid,first_name,last_name,date_of_birth,country,hiv_status,diagnosis_date,art_status,art_start_date,life_expectancy" > C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt
    echo "admin@lpmt.com","$1$24uuid$P9Gf2tmbmZPmg1JO0JoaK1",admin >> C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt
fi

# Function to generate a UUID using the uuidgen command
generate_uuid() {
    uuidgen
}

uuid=$(generate_uuid)

printf "$1,,"patient",$uuid\n" >> C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt

echo "UUID for user" "$1" is "$uuid"