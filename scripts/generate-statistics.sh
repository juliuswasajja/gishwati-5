#!/bin/bash

# Define input and output files
input_file="../data-store/user-store.txt"
patient_data_file="../data-store/statistics.csv"
statistics_file="../data-store/statistics.csv"

# Create patient data CSV file
echo "email,uuid,role,patient_id,first_name,last_name,dob,nationality,has_scholarship,admission_date,has_family,diagnosis_date,age" > "$patient_data_file"

# Parse the input file and populate the patient data CSV file
while IFS=, read -r email password role patient_id first_name last_name dob nationality has_scholarship admission_date has_family diagnosis_date age; do
    echo "$email,$patient_id,$role,$patient_id,$first_name,$last_name,$dob,$nationality,$has_scholarship,$admission_date,$has_family,$diagnosis_date,$age" >> "$patient_data_file"
done < <(tail -n +2 "$input_file") # Skip the header line

# Compute statistics
ages=()
while IFS=, read -r email password role patient_id first_name last_name dob nationality has_scholarship admission_date has_family diagnosis_date age; do
    ages+=("$age")
done < <(tail -n +2 "$input_file") # Skip the header line

# Sort ages and compute statistics
sorted_ages=$(printf "%s\n" "${ages[@]}" | sort -n)
count=${#ages[@]}
average=$(echo "${sorted_ages[@]}" | awk '{ sum += $1 } END { if (NR > 0) print sum / NR }')
median=$(echo "$sorted_ages" | awk -v c=$count '
    { a[NR] = $1 }
    END {
        if (c % 2 == 1) {
            print a[(c + 1) / 2]
        } else {
            print (a[c / 2] + a[c / 2 + 1]) / 2
        }
    }')

# Percentiles (25th, 50th, 75th)
percentile_25=$(echo "$sorted_ages" | awk -v p=0.25 -v c=$count '
    { a[NR] = $1 }
    END {
        print a[int(p * c + 0.5)]
    }')
percentile_50=$(echo "$sorted_ages" | awk -v p=0.50 -v c=$count '
    { a[NR] = $1 }
    END {
        print a[int(p * c + 0.5)]
    }')
percentile_75=$(echo "$sorted_ages" | awk -v p=0.75 -v c=$count '
    { a[NR] = $1 }
    END {
        print a[int(p * c + 0.5)]
    }')

# Create statistics CSV file
echo "Metric,Value" > "$statistics_file"
echo "Average,$average" >> "$statistics_file"
echo "Median,$median" >> "$statistics_file"
echo "25th Percentile,$percentile_25" >> "$statistics_file"
echo "50th Percentile,$percentile_50" >> "$statistics_file"
echo "75th Percentile,$percentile_75" >> "$statistics_file"

echo "Statistics have been generated to $statistics_file, respectively."
