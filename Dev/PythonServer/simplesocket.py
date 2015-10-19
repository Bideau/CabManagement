import signal, sys, ssl
from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer, SimpleSSLWebSocketServer 
from optparse import OptionParser



clients = []
class SimpleChat(WebSocket):

    def myfonction(self):
        print 'hello toto'

    def handleMessage(self):
        print self.data
        for client in clients:
            if client != self:
                client.sendMessage(self.address[0] + ' - ' + self.data)
    def handleConnected(self):
        print self.address, 'connected'
       # for client in clients:
        #    client.sendMessage(self.address[0] + u' - connected')
        self.myfonction()
        clients.append(self)
        clients[-1].sendMessage(u'your are connected')
    def handleClose(self):
        clients.remove(self)
        print self.address, 'closed'
        for client in clients:
            client.sendMessage(self.address[0] + u' - disconnected')

server = SimpleWebSocketServer('0.0.0.0', 8000, SimpleChat)
server.serveforever()
