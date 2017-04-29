#!/bin/bash
for d in *; do
	if [ -d "$d" ]; then
		cd "$d"
		mv *.jpg ..
		cd ..
	fi
done
