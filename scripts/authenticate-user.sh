#!/bin/bash

USER_STORE_PATH="../data-store/user-store.txt"
email=$1
password=$2

# Encrypt the submitted password
encrypted_password=$(openssl passwd -salt 24uuid ${password})

# Check if the email and encrypted password match a record in the file
user_data=$(grep "^${email},${encrypted_password}," "$USER_STORE_PATH")

if [ -z "$user_data" ]; then
    echo "Authentication failed - from bash"

    exit 1
else
    # echo "Authentication successful"
    echo "$user_data"
    exit 0
fi