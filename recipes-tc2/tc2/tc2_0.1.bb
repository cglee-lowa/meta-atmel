SUMMARY = "tc2"
DESCRIPTION = "tc2 tuner control test"
LICENSE = "CLOSED"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*  tc2 tuner control                          *");
    bb.plain("*  /etc/rc.local                              *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

SRC_URI = "file://tc2"
SRC_URI += "file://rc-local.service"
SRC_URI += "file://rc.local"
SRC_URI += "file://.bashrc"

SRC_URI += "file://90-wlan-autoconnect.rules"
SRC_URI += "file://auto-connect-wlan.sh"
SRC_URI += "file://wpa_cli.service"
SRC_URI += "file://wpa_cli-actions.sh"

# 파일을 복사할 설치 경로를 지정
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/tc2 ${D}${bindir}

    install -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/rc.local ${D}${sysconfdir}
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/rc-local.service ${D}${systemd_system_unitdir}/rc-local.service
    install -d ${D}/home/root
    install -m 0755 ${WORKDIR}/.bashrc ${D}/home/root/.bashrc
    # wlan
    install -d ${D}/etc/udev/rules.d
    install -m 0755 ${WORKDIR}/90-wlan-autoconnect.rules ${D}/etc/udev/rules.d/90-wlan-autoconnect.rules
    install -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/auto-connect-wlan.sh ${D}/usr/bin/auto-connect-wlan.sh
    install -d ${D}/etc/systemd/system
    install -m 0755 ${WORKDIR}/wpa_cli.service ${D}/etc/systemd/system/wpa_cli.service
    install -d ${D}/etc/wpa_supplicant
    install -m 0755 ${WORKDIR}/wpa_cli-actions.sh ${D}/etc/wpa_supplicant/wpa_cli-actions.sh
}

# 패키징 시 파일 경로를 지정 (필수는 아니지만, 명시적으로 설정)
FILES_${PN} += "${bindir}/tc2"
FILES_${PN} += "${sysconfdir}/rc.local"
FILES:${PN} += "${systemd_system_unitdir}/rc-local.service"
FILES:${PN} += "/home/root/.bashrc"
FILES:${PN} += "/etc/udev/rules.d/90-wlan-autoconnect.rules"
FILES:${PN} += "/usr/bin/auto-connect-wlan.sh"
FILES:${PN} += "/etc/systemd/system/wpa_cli.service"
FILES:${PN} += "/etc/wpa_supplicant/wpa_cli-actions.sh"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
DEPENDS += " zlib"
SYSTEMD_SERVICE:${PN} += "rc-local.service"
SYSTEMD_SERVICE:${PN} += "wpa_cli.service"
inherit systemd