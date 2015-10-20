import signal, sys, ssl
from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer, SimpleSSLWebSocketServer 
from optparse import OptionParser
from sys import argv
import json

clients = []
carte = []
class SimpleChat(WebSocket):

    def myfonction(self):
        print 'Someone is connected'

    #Function when someone send a message
    def handleMessage(self):
        print self.data
        if self.data == 'Initialisation':
            fic = open('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json')
            message = fic.read()
            tmpJson = json.loads(message)
            tmp = json.dumps(tmpJson)
            clients[-1].sendMessage(unicode(tmp))
        for client in clients:
             client.sendMessage(self.address[0] + ' - ' + self.data)

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
