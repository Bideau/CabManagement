from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer, SimpleSSLWebSocketServer 
#from optparse import OptionParser
import signal, sys, ssl
from sys import argv
#from netaddr import *
from structure.cab import CabStatus
from mouvCab import MouvCab
#from threadCab import ThreadCab
#from threadInterface import ThreadInterface
from myParsing import myParsing
import json
import time
from Queue import Queue
import threading

pathJSON='/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/'
#pathJSON='/home/pi/json/'
clients = []
cabclients = []
carte = []
cabs=[]
mouvCab=MouvCab()
parse = myParsing()
mapJSON=''
class SimpleChat(WebSocket):

    #Function when someone send a message
    def handleMessage(self):
        print self.data
        verif=False
        if self.address[0] == '192.168.3.10':
       # if IPv4Address(self.address[0]) in IPv4Network('192.168.3.0/24'):
            #Ethernet Client
            tmpCab=cabs[cabclients.index(self)]
            verif=parse.parsingRepArdui(self.data,tmpCab)
            if verif:
                print 'launch Move Galileo'
                mouvCab.calcPath(carte,tmpCab)
                print 'launch Thread Galileo'
                t = threading.Thread(target=mouvCab.galileoThread, args=(tmpCab,self,carte,parse.listRequest))
                t.start()
        else:
      #  elif IPv4Address(self.address[0]) in IPv4Network('192.168.4.0/24'):
            #Wifi client
            tmpCab=parse.parsingCabReq(self.data)
            string=parse.readFile(pathJSON+'taxi.json')
            jsonString = json.loads(string)
           # mouvIter.interfaceThread(cabs,self,clients,jsonString)
            t = threading.Thread(target=mouvCab.interfaceThread, args=(cabs,self,clients,jsonString))
            t.start()
            


    #Function when someone connect
    def handleConnected(self):
        mouvement=MouvCab()
        print self.address, 'connected'
	tmp = '%s' % self.address[0]
        if tmp == '192.168.3.10':
       # if IPv4Address(self.address[0]) in IPv4Network('192.168.3.0/24'):
            #Ethernet Client
            cabclients.append(self)
            tmp=parse.readFile(pathJSON+'taxi.json')
            tmpCab=parse.parsingCabInfo(tmp)
            cabs.append(tmpCab)
            #test=mouving.moveCab(cab,path)
            toGalileo={"numberofclients":len(parse.listRequest),"cabstatus":tmpCab.status,"distancetravelled":tmpCab.cabinfo.odometre}
            tmpJSON=json.dumps(toGalileo)
            self.sendMessage(unicode(tmpJSON))
            print 'Ethernet is connected'
        else: 
     # elif IPv4Address(self.address[0]) in IPv4Network('192.168.4.0/24'):
            #Wifi client
            clients.append(self)
	    tmp=json.loads(mapJSON)
            self.sendMessage(unicode(json.dumps(tmp)))
            print 'Wifi is connected'

    #Function when someone disconnect
    def handleClose(self):
        clients.remove(self)
        print self.address, 'closed'

server = SimpleWebSocketServer('0.0.0.0', 8000, SimpleChat)
mapJSON = parse.readFile(pathJSON+'test.json')
carte=parse.parsingArea(mapJSON)
#print carte
main_thread = threading.currentThread()
for t in threading.enumerate():
    if t is not main_thread:
        t.join()
server.serveforever()
