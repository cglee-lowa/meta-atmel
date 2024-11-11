# shc_4.0.3.bb
LICENSE = "CLOSED"
SRCREV = "${AUTOREV}"
UBRANCH = "master"
SRC_URI = "git://github.com/neurobin/shc.git;protocol=https;branch=${UBRANCH}"
S = "${WORKDIR}/git"
PACKAGE_ARCH = "${MACHINE_ARCH}"