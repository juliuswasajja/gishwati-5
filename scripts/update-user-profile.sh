##!/bin/bash
#
#USER_FILE="C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt"
#
## Function to update user information
#update_user() {
#    local email="$1"
#    local field="$2"
#    local new_value="$3"
#
#    # Check if user exists
#    if ! grep -q "$email" "$USER_FILE"; then
#        echo "User not found."
#        return
#    fi
#
#    # Field mapping
#    case $field in
#        1) field_name="Email";;
#        2) field_name="Password";;
#        3) field_name="UUID";;
#        4) field_name="First Name";;
#        5) field_name="Last Name";;
#        6) field_name="HIV Status";;
#        7) field_name="Diagnosis Date";;
#        8) field_name="ART Status";;
#        9) field_name="ART Start Date";;
#        *) echo "Invalid field number."; return;;
#    esac
#
#    # Update user information
#    awk -v email="$email" -v field="$field" -v new_value="$new_value" -F"," 'BEGIN {OFS=","} {
#        if ($0 ~ email) {
#            $field = new_value
#        }
#        print
#    }' "$USER_FILE" > tmp && mv tmp "$USER_FILE"
#    echo "$field_name updated successfully."
#}
#
## Execute the function
#update_user "$1" "$2" "$3"

# !/bin/bash

USER_FILE="C:\\Users\\STUDENT-11\\IdeaProjects\\gishwati-5\\data-store\\user-store.txt"

# Function to update user information
update_user() {
    local email="$1"
    local field_name="$2"
    local new_value="$3"

    # Check if user exists
    if ! grep -q "$email" "$USER_FILE"; then
        echo "User not found."
        return
    fi

    # Determine the field index based on the field name
    case $field_name in
        "Email") field_index=1 ;;
        "Password") field_index=2 ;;
        "Role") field_index=3 ;;
        "UUID") field_index=4;;
        "First Name") field_index=5 ;;
        "Last Name") field_index=6 ;;
        "Date of Birth") field_index=7 ;;
        "Country") field_index=8 ;;
        "HIV Status") field_index=9 ;;
        "Diagnosis Date") field_index=10 ;;
        "ART Status") field_index=11 ;;
        "ART Start date") field_index=12 ;;
        "Life Expectancy") field_index=13 ;;
        *) echo "Invalid field name."; return ;;
    esac

    # Update user information
    awk -v email="$email" -v field="$field_index" -v new_value="$new_value" -F"," 'BEGIN {OFS=","} {
        if ($1 == email) {
            $field = new_value
        }
        print
    }' "$USER_FILE" > tmp && mv tmp "$USER_FILE"

    echo "$field_name updated successfully."
}

# Execute the function
update_user "$1" "$2" "$3"
