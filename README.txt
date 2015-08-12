
Projekt Name:Theoche
Version: 0.9ish 2015-02-19
Author: Theo Markovic
Descrition: A simple webbserver constructed around the HTTP/0.9-standard. Returns files 
and folders upon correctly formated GET-request (It only supports GET-requests). The program can be executed
by compiling it in your IDE of choice or manually with the javac-executable. The port on which the server listens to
is customised by passing the desired portnumber as a run-argument, if no port is passed the server will listen on port 8080
by default. The Theoche webserver can be configured in the conf.cfg-file to automaticly search for and if found, return a indexfile in any given 
path. The Theoche webserver can also list the contents of directories where no indexfile is found. 
 