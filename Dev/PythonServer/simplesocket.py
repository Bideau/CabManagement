import signal, sys, ssl
from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer, SimpleSSLWebSocketServer 
from optparse import OptionParser
from sys import argv
import json
import time

clients = []
carte = []
class SimpleChat(WebSocket):

    def myfonction(self):
        print 'Someone is connected'

    #Function when someone send a message
    def handleMessage(self):
        print self.data
        if self.data == 'Initialisation':
            print 'init'

            fic = open('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json')
            message = fic.read()
            tmpJson = json.loads(message)
            tmp = json.dumps(tmpJson)
            fic.close()
            clients[-1].sendMessage(unicode(tmp))
            print 'init1'
            fic = open('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json')
            message = fic.read()
            tmpJson = json.loads(message)
            tmp = json.dumps(tmpJson)
            fic.close()
            clients[-1].sendMessage(unicode(tmp))
            print 'init2'
            time.sleep(2)
            fic = open('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi3.json')
            print 'init3'
            message = fic.read()
            tmpJson = json.loads(message)
            tmp = json.dumps(tmpJson)
            fic.close()
            clients[-1].sendMessage(unicode(tmp))
            print 'init4'
    #Function when someone connect
    def handleConnected(self):
        print self.address, 'connected'
       # for client in clients:
       #     client.sendMessage(self.address[0] + u' - connected')
          #  client.sendMessage(unicode(tmpJson)) 
        self.myfonction()
        clients.append(self)
        clients[-1].sendMessage(u'you are connected!')
        

    #Function when someone disconnect
    def handleClose(self):
        clients.remove(self)
        print self.address, 'closed'
        for client in clients:
            client.sendMessage(self.address[0] + u' - disconnected')

server = SimpleWebSocketServer('0.0.0.0', 8000, SimpleChat)

server.serveforever()
