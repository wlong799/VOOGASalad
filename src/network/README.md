# Multi-client Network Transport
# Team Overwatch

Dear 308 people,

We all share the sorrow and misery from the weeks spent toiling through bugs and features. I hope this utility would be useful to help us all finish strong in the days to come. 

## Disclaimer

All source files are distributed "as is" in the hope that it will be useful.

The tool set comes with no warranty, and no author or distributor accepts any responsibility for the consequences of its use. 

Permission is granted to anyone to make or distribute copies of this source code, either as received or modified, in any medium, provided that this copyright and nonwarranty notices are preserved, and that our team is appropriately credited. 

## How to

In INetworkConfig, update the `PROD_SERVER_NAME` to the hostname of the machine on which you would like to deploy your server. You could also change `SERVER_PORT` to any available port > 1024 if you like. 

To start the server, run ServerMain.java.

It is recommended that you export your project to a jar file for easy deployment. 

The INetworkClient is the API provided by this infrastructure, and NetworkClient is its implementation. The java documentation is complete and ready for consult. 

The ServerClientTest is a good example to see how the API is used. It runs multiple nodes on the same host.

*Caviar* 

The daemon thread runs as long as server is up. If you get `Port number already in use` error, make sure you have kill the previous server instance. 
