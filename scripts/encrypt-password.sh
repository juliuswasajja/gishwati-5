#!/bin/bash

USER_STORE_PATH="../data-store/user-store.txt"

lpmtuuid=$1
lpmtuserpassword=$2

lpmtencryptedpassword=$(openssl passwd -salt 24uuid ${lpmtuserpassword})

echo "encrypted password: $lpmtencryptedpassword" 


escaped_password=$(printf '%s\n' "$lpmtencryptedpassword" | sed 's/[]\/$*.^|[]/\\&/g')

echo "escaped passowrd: $escaped_password"

sed -i '' "/,$lpmtuuid$/s/[^,]*/${escaped_password}/2" ../data-store/user-store.txt



