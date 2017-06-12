#!/bin/bash
for video in *.mp4; do
	newname=$(echo $video | sed "s/\.mp4/_small\.mp4/")
	echo $newname
	ffmpeg -i $video -s 640x480 -b:v 3072k -vcodec mp4 -acodec copy $newname
	rm $video
done
