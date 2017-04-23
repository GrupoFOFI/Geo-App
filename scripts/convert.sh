#!/bin/bash
for d in *; do
	var=$(echo $d | sed "s/\ /\_/" | tr [:upper:] [:lower:])
	mv "$d" "$var"
	var2=$(echo $d | sed "s/\-/\_/g" | tr [:upper:] [:lower:])
	mv "$var" "$var2"
done
