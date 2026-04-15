SUMMARY = "IoT Demo Image for Raspberry Pi 5"
DESCRIPTION = "IoT Demo Image for Raspberry Pi 5"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " openssh python3 python3-paho-mqtt sensor-agent rauc util-linux"

EXTRA_USERS_PARAMS = "usermod -P '' root;"
