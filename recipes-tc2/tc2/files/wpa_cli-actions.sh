#!/bin/sh
echo "udhcp2 " $1 $2 $3 > /dev/ttyS0
if [ "$2" = "CONNECTED" ]; then
    udhcpc -i $1
fi