#!/bin/sh
echo $INTERFACE $ACTION "start" >/dev/ttyS0
if [ "$INTERFACE" == "wlan0" ] && [ "$ACTION" == "add" ]; then
    systemctl restart wpa_supplicant&
    systemctl restart wpa_cli.service&
fi
if [ "$INTERFACE" == "wlan0" ] && [ "$ACTION" == "remove" ]; then
    systemctl stop wpa_cli.service&
    systemctl stop wpa_supplicant&
    /usr/bin/killall wpa_supplicant
    /usr/bin/killall udhcpc 
fi
if [ "$INTERFACE" == "wwan0" ] && [ "$ACTION" == "add" ]; then
    ifconfig $INTERFACE up
fi
if [ "$INTERFACE" == "wwan0" ] && [ "$ACTION" == "remove" ]; then
    ifconfig $INTERFACE down
fi
echo $INTERFACE $ACTION "end" >/dev/ttyS0