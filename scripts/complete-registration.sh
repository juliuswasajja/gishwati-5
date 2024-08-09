#!/bin/bash

USER_STORE_PATH="C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt"

uuid=$1
first_name=$2
last_name=$3
country=$4
age=$5
hiv_status=$6
diagnosis_date=$7
art_status=$8
art_start_date=$9

sed -i '' "/,${uuid}$/s/$/,$first_name,$last_name,$country,$age,$hiv_status,$diagnosis_date,$art_status,$art_start_date/" "$USER_STORE_PATH"
