SUMMARY = "IoT Demo Image for Raspberry Pi 5"

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " openssh python3 python3-paho-mqtt sensor-agent rauc"

