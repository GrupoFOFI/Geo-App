#!/bin/bash
for image in *.jpg; do
	convert $image -resize 400 $image
done
