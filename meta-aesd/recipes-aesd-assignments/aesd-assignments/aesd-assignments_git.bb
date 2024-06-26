inherit update-rc.d
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-asabbagh4.git;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "0253bac1f0360b96abdc5571112dd9fe5f57272d"

S = "${WORKDIR}/git/server"

# TODO: Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
FILES:${PN} += "${bindir}/aesdsocket" \
			   "${sysconfdir}/init.d/aesdsocket-start-stop"
# TODO: customize these as necessary for any libraries you need for your application
# (and remove comment)
TARGET_LDFLAGS += "-pthread -lrt -lm"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
INITSCRIPT_PACKAGES = "${PN}"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d
}
