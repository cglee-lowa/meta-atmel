#!/bin/sh
echo "udhcpc degraded start " $IFACE > /dev/ttyS0
if [ "$IFACE" == "wwan0" ]; then
    udhcpc -i $IFACE&
fi
echo "udhcpc degraded end " $IFACE > /dev/ttyS0
