SUMMARY = "IoT sensor agent - MQTT publisher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://sensor-agent.py \
    file://sensor-agent.service \
"

inherit systemd

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/sensor-agent.py ${D}${bindir}/sensor-agent.py

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sensor-agent.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE:${PN} = "sensor-agent.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"