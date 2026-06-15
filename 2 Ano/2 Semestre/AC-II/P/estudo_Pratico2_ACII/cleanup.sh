#!/bin/bash
#
# Quality-of-Life script to clean up compile artifacts from all .c files in Prep_P2 and its subdirectories.
#
# Usage:
#   ./compileAll.sh [-h]
#
# Options:
#   -h, --help       Show this help message
#
# Nelson Ramos, 124921.

if [[ "$1" == "-h" ]] || [[ "$1" == "--help" ]]; then
    cat << EOF
AC2 Cleanup Helper Script, v0.0.1 (May 2026)
Nelson Ramos, 124921

Usage: ./cleanup.sh [-h]

Remove all compile artifacts (.o, .elf) from Prep_P2 and subdirectories.

Options:
  -h, --help       Show this help message
EOF
    exit 0
fi

echo "!!Cleaning up compile artifacts in Prep_P2!!"
echo

removed_count=0

# Remove .o files
for file in $(find . -name "*.o" -type f); do
    echo "Removing: $file"
    rm "$file"
    ((removed_count++))
done

# Remove .elf files
for file in $(find . -name "*.elf" -type f); do
    echo "Removing: $file"
    rm "$file"
    ((removed_count++))
done

# Remove .map files
for file in $(find . -name "*.map" -type f); do
    echo "Removing: $file"
    rm "$file"
    ((removed_count++))
done

# Remove .hex files
for file in $(find . -name "*.hex" -type f); do
    echo "Removing: $file"
    rm "$file"
    ((removed_count++))
done

echo
echo "Cleanup complete"
echo "Removed $removed_count artifact(s)"