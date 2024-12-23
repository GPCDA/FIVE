#!/bin/bash


if [ $# -ne 2 ] ; then
  echo ""
  echo "wav2raw.sh: convert .wav files into .raw files using sox."
  echo "     Usage: $0 wav-directory raw-directory"
  echo ""
else
  for file in $1/*.wav; do
    file_name=`basename $file .wav`
    echo $1/$file_name.wav" --> "$2/$file_name.raw
    sox $1/$file_name.wav $2/$file_name.raw
  done
fi