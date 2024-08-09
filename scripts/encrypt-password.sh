#!/bin/bash

USER_STORE_PATH="C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt"

uuid=$1
userpassword=$2

encryptedpassword=$(openssl passwd -salt 24uuid ${userpassword})

echo "encrypted password: $encryptedpassword" 


escaped_password=$(printf '%s\n' "$encryptedpassword" | sed 's/[]\/$*.^|[]/\\&/g')

echo "escaped passowrd: $escaped_password"

sed -i '' "/,$uuid$/s/[^,]*/${escaped_password}/2" C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt