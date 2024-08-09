#!/bin/bash

USER_STORE_PATH="../data-store/user-store.txt"

# Check if the UUID exists in the file
uuid_exists() {
    uuid=$1
    grep -q ",$uuid" "$USER_STORE_PATH"
}

# # Main script
# echo "Enter UUID:"
# read input_uuid

if uuid_exists "$1"; then
    echo "UUID exists. Please enter a password:"
    # read password
    # echo "Password received for UUID: $input_uuid"  # Placeholder for further action
else
    echo "UUID not found. Please check and try again."
fi