DESCRIPTION = "FriendlyElec NanoPC-T4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS:append = " bison-native rkbin "

SRC_URI = " \
    git://github.com/u-boot/u-boot;protocol=https;branch=master \
"
SRCREV = "v2019.10"

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
