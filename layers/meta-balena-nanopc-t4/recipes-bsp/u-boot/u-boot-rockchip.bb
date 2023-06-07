DESCRIPTION = "FriendlyElec NanoPC-T4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS:append = " bison-native rkbin "

SRC_URI = " \
    git://github.com/u-boot/u-boot;protocol=https;branch=master \
"
SRCREV = "fd4ed6b7e83ec3aea9a2ce21baea8ca9676f40dd"

S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy:append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}

do_compile[depends] += "rkbin:do_deploy"
