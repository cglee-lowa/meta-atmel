#!/bin/sh
if ip link show wwan0 > /dev/null 2>&1; then
    udhcpc -i wwan0
fi