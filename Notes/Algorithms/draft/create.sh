#!/bin/bash

# Check for input
if [ -z "$1" ]; then
  echo "Usage: $0 \"title name\""
  exit 1
fi

raw_title="$*"

slug=$(echo "$raw_title" | sed -E 's/^([0-9]+)\.\s*(.*)/\1.\2/' | awk '{
  num_and_dot = ""; rest = "";
  if (match($0, /^[0-9]+\./)) {
    num_and_dot = substr($0, RSTART, RLENGTH);
    rest = substr($0, RLENGTH + 1);
  }
  n = split(rest, words, " ");
  for (i=1; i<=n; i++) {
    words[i] = toupper(substr(words[i],1,1)) tolower(substr(words[i],2));
  }
  print num_and_dot join(words, "-");
}
function join(arr, sep,    out, i) {
  out = arr[1];
  for (i=2; i in arr; i++) {
    out = out sep arr[i];
  }
  return out;
}')


# Today's date
today=$(date +%F)

# Final filename
filename="${slug}.md"

# Replace {{date}} in template
sed "s/{{date}}/$today/g" template.md > "$filename"

echo "$filename Created."
