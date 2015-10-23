The Rasp is configured as an access point and a dhcp for the WiFi and the ethernet.
First it is necessary to install the package hostapd and isc-dhcp-server.
Rasp configuration is composed of four file.
The file dhcpcd.conf is positionned in /etc/dhcp/ . It described the configuration of the network and the management of subnet.
The file hostapd.conf is positionned in /etc/hostapd/ .It described the configuration of the access point with the channel, the encryption type and the password.
The file interface is positionned in /etc/network/ . It described the configuration of different interfaces.
The file isc-dhcp-server is positionned in /etc/default/ . It described the interfaces use for the dhcp server.


