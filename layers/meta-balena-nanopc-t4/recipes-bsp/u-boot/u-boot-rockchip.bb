DESCRIPTION = "FriendlyElec NanoPC-T4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS:append = " bison-native rkbin "

SRC_URI = " \
    git://github.com/u-boot/u-boot;protocol=https;branch=master \
"

SRCREV = "d80bb749fab53da72c4a0e09b8c2d2aaa3103c91"

S = "${WORKDIR}/git"

do_configure:append() {
    oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE} mrproper
}

do_install:append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy:append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}

do_compile[depends] += "rkbin:do_deploy"