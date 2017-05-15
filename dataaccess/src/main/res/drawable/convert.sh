#!/bin/bash
prefix=$1
for d in *; do
	var=$(echo $d | sed "s/\ /\_/" | tr [:upper:] [:lower:])
	var2=$(echo $var | sed "s/\-/\_/g" | tr [:upper:] [:lower:])
	mv "$d" "$prefix$var2"
done
