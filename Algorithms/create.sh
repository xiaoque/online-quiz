#!/bin/bash

# Check for input
if [ -z "$1" ]; then
  echo "Usage: $0 \"title name\""
  exit 1
fi

raw_title="$*"

# Convert to Title-Case-With-Dashes
slug=$(echo "$raw_title" | awk '{
  for (i=1; i<=NF; i++) {
    $i = toupper(substr($i,1,1)) tolower(substr($i,2))
  }
  print
}' | tr ' ' '-')

# Today's date
today=$(date +%F)

# Final filename
filename="${slug}.md"

# Replace {{date}} in template
sed "s/{{date}}/$today/g" template.md > "$filename"

echo "$filename Created."
