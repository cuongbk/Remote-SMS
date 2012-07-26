Remote SMS
==============
Remote SMS is an application for data exchange between smart phone Android and PC in wireless LAN. It was designed by Android RF-Lab group in HUST
Introduction
------------

Our application is designed to help an user don't need to touch his phone but can exchange data (like SMS) on his working PC using wireless LAN connection between them.

Our application in smart phone receives a SMS and sends it to a PC using TCP protocol with wireless LAN connection established. And the user also can reply a SMS or sends a new one in his working PC and his phone will send it automatically. Additionally, this application can also receive and notify user to know whether answer the call or not.

Operating principle: Using TCP protocol (IP and port) based on TCP/IP to transmit and receive.

Component
------------
#### Android Applicaion on smart phone
   AndroidServer:
* [Androidmainifest.xml](https://github.com/cuongbk/Remote-SMS/blob/master/AndroidServer/AndroidManifest.xml) -- `inform classes` 
* [AndroidManifest.ﬁrst.xml](https://github.com/cuongbk/Remote-SMS/blob/master/AndroidServer/AndroidManifest.%EF%AC%81rst.xml) -- `inform Help class`
* [res\drawable](https://github.com/cuongbk/Remote-SMS/tree/master/AndroidServer/res/drawable-hdpi) -- `contain file.png`
* [res\layout](https://github.com/cuongbk/Remote-SMS/tree/master/AndroidServer/res/layout) -- `design interface`
* [res\values](https://github.com/cuongbk/Remote-SMS/tree/master/AndroidServer/res/values) -- `define the value of string or color`
* [src\cuong\server](https://github.com/cuongbk/Remote-SMS/tree/master/AndroidServer/src/cuong/server) -- `source code`
* [bin]() -- `including installation file `
   
#### C# Application on PC
   Client PC:
* [.resx and .Design.cs](https://github.com/cuongbk/Remote-SMS/tree/master/ClientPC/ClientPC) -- `interface` 
* [.cs](https://github.com/cuongbk/Remote-SMS/tree/master/ClientPC/ClientPC) -- `source code`
* [bin\Debug](https://github.com/cuongbk/Remote-SMS/tree/master/ClientPC/ClientPC/bin/Debug) -- `contain the files was created by project`

Instruction
------------------
1. Install to convert a laptop into a device transmitting WIFI
2. Install AndroidServer.apk to smartphone and Setup.exe to PC
3. Run Remote SMS on Smartphone and Client PC on PC
4. Enter the IP Address on Client PC by IP on Remote SMS's interface on Smartphone
5. Enter the No. Phone(+84....) to No. Phone textbox and Message to message's textbox
6. Click or press Enter button to send SMS
7. ....
