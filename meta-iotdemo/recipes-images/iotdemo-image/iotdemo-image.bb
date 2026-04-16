SUMMARY = "IoT Demo Image for Raspberry Pi 5"
DESCRIPTION = "IoT Demo Image for Raspberry Pi 5"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " openssh python3 python3-paho-mqtt sensor-agent rauc util-linux wpa-supplicant wpa-supplicant-config"
