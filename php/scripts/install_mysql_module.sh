#!/bin/bash

echo "install PHP module for Mysql..."
sudo apt-get -y -q install php5-mysql || error_exit $? "Failed on: sudo apt-get install -y -q php5-mysql"

echo "restart Apache2 to launch php5-mysql"
sudo /etc/init.d/apache2 restart
