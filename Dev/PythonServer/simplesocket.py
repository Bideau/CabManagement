import signal, sys, ssl
from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer, SimpleSSLWebSocketServer 
#from optparse import OptionParser
from sys import argv
from netaddr import *
from mouvCab import MouvCab
from myParsing import myParsing
import json
import time

clients = []
cabclients = []
carte = []
cabs=[]
mouvement=MouvCab()
parse = myParsing()
class SimpleChat(WebSocket):

    # Initialize the map    
    def __init__(self):
        server = SimpleWebSocketServer('0.0.0.0', 8000, SimpleChat)
        myJSON = parse.readFile('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json')
        carte=parse.parsingArea(myJSON)
        print carte
        server.serveforever()

    #Function when someone send a message
    def handleMessage(self):
        print self.data
        if IPv4Address(self.address[0]) in IPv4Network('192.168.3.0/24'):
            #Ethernet Client
            tmpCab=cabs[cabclients.index(self)]
            if parse.parsingRepArdui(self.data,tmpCab):
                path=mouvement.calcPath(carte,tmpCab)
                t = threading.Thread(target=galileoThread, args=(mouvement,tmpCab,self,path))
                t.start()
        elif IPv4Address(self.address[0]) in IPv4Network('192.168.4.0/24'):
            #Wifi client
            tmpCab=parse.parsingCabReq(self.data)
            parse.request=(tmpCab)
            


    #Function when someone connect
    def handleConnected(self):
        print self.address, 'connected'
        if IPv4Address(self.address[0]) in IPv4Network('192.168.3.0/24'):
            #Ethernet Client
            cabclients.append(self)
            tmpCab=CabStatus()
            cab.append(tmpCab)
            print 'Ethernet is connected'
        elif IPv4Address(self.address[0]) in IPv4Network('192.168.4.0/24'):
            #Wifi client
            clients.append(self)
            t = threading.Thread(target=interfaceThread, args=(mouvement,cabs,self,clients))
            t.start()
            print 'Wifi is connected'
        

    #Function when someone disconnect
    def handleClose(self):
        clients.remove(self)
        print self.address, 'closed'
       # for client in clients:
           # client.sendMessage(self.address[0] + u' - disconnected')

server = SimpleChat()
