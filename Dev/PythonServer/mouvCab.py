from structure.cab import CabRequest
from structure.cab import CabStatus
from depcab import DepCab
import time
import json
class MouvCab:

    def __init__(self):
        self.sleepTime=2
        self.path=[]
        self.listRequest=[]
        self.mouv=DepCab(0.2)
        
    def galileoThread(self,cab,client,areas,listRequest):
        self.listRequest=listRequest
        if isinstance(cab,CabStatus):
            cab.cabinfo.odometre=0
            cab.cabinfo.locNow.progression=0
            test=True
            print 'Start galileo'
            while test:
                test=self.mouv.moveCab(cab,self.path,areas)
                toGalileo={"numberofclients":len(self.listRequest),"cabstatus":cab.status,"distancetravelled":cab.cabinfo.odometre}
                tmpJSON=json.dumps(toGalileo)
                client.sendMessage(unicode(tmpJSON))
#                cab.cabinfo.locNow.progression=cab.cabinfo.locNow.progression+0.2
                print cab.cabinfo.locNow.progression
                time.sleep(1)


    def interfaceThread(self,cabs,client,clients,string):
        while len(cabs)<1:
            time.sleep(2)
        cab=self.updateCabs(cabs)
        print cab
        while client in clients:
            tmpJSON=json.dumps(string)
            client.sendMessage(unicode(tmpJSON))
            time.sleep(1)
            string={"odometer": cab.cabinfo.odometre ,"destination": {
"area": cab.cabinfo.destination.area ,
"location":{
"area": cab.cabinfo.destination.location.area,"locationType":cab.cabinfo.destination.location.locationType,
"location":cab.cabinfo.destination.location.location}},
"locNow":{
"area": cab.cabinfo.locNow.area,"locationType":cab.cabinfo.locNow.locationType,
"location":cab.cabinfo.locNow.location},
"locNext":{
"area": cab.cabinfo.locNext.area,"locationType":cab.cabinfo.locNext.locationType,
"location":cab.cabinfo.locNext.location}} 
                
    def updateCabs(self,cabs):
        return cabs[0]

    # Creating path
    def calcPath(self, area,cab):
        self.path=self.mouv.dijCall(cab,area)
        return self.path
                
