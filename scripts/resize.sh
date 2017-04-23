#!/bin/bash
for image in *.jpg; do
	convert $image -resize 400x400! $image
done
