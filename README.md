Remote SMS
==============
Remote SMS is an application for data exchange between smart phone Android and PC inwireless LAN.
Introduction
------------

Our application is designed by Android RF-Lab group (HUST) to help an user don't need to touch his phone but can exchange data (like SMS) on his working PC using wireless LAN connection between them.

Our application in smart phone receives a SMS and send it to a PC using TCP protocol with wireless LAN connection established. And the user also can reply a SMS or sends a new one in his working PC and his phone will send it automatically. Additionally, this application can also receive and notify user whose call to user can answer or not.

Operating principle: Using TCP protocol (IP and port) based on TCP/IP to transmit and receive.

Component
------------
#### Android Applicaion on smart phone
   Server Android:
* [.markdown](http://daringfireball.net/projects/markdown/) -- `gem install redcarpet` (https://github.com/tanoku/redcarpet)
* [.textile](http://www.textism.com/tools/textile/) -- `gem install RedCloth`
* [.rdoc](http://rdoc.sourceforge.net/) -- `gem install rdoc -v 3.6.1`
* [.org](http://orgmode.org/) -- `gem install org-ruby`
* [.creole](http://wikicreole.org/) -- `gem install creole`
* [.mediawiki](http://www.mediawiki.org/wiki/Help:Formatting) -- `gem install wikicloth`
* [.rst](http://docutils.sourceforge.net/rst.html) -- `easy_install docutils`
* [.asciidoc](http://www.methods.co.nz/asciidoc/) -- `brew install asciidoc`
* [.pod](http://search.cpan.org/dist/perl/pod/perlpod.pod) -- `Pod::Simple::HTML`
   
#### C# Application on PC