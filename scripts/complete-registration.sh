#!/bin/bash

USER_STORE_PATH="../data-store/user-store.txt"

uuid=$1
first_name=$2
last_name=$3
country=$4
age=$5
hiv_status=$6
diagnosis_date=$7
art_status=$8
art_start_date=$9


# Use sed to update the user-store.txt
# sed -i '' "
# /,${uuid}$/ {
#     s/^[^,]*/${first_name}/
#     s/^[^,]*,[^,]*/&${last_name}/
#     s/^[^,]*,[^,]*,[^,]*/&${country}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*/&${age}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*,[^,]*/&${hiv_status}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*/&${diagnosis_date}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*/&${art_status}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*/&${art_start_date}/
#     s/^[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*/&${escaped_password}/
# }" "$USER_STORE_PATH"

# sed -i '' "
# /,${uuid},/ {
#     s/[^,]*/${first_name}/4
# }" "$USER_STORE_PATH"

sed -i '' "/,${uuid}$/s/$/,$first_name,$last_name,$country,$age,$hiv_status,$diagnosis_date,$art_status,$art_start_date/" "$USER_STORE_PATH"


# sed -i '' "/,${uuid},/s/$/,$first_name,$last_name,$country,$age,$hiv_status,$diagnosis_date,$art_status,$art_start_date/" "$USER_STORE_PATH"



# echo $first_name
# echo "User information updated for UUID: $uuid"