from structure.cab import CabRequest
from structure.cab import CabStatus
from depcab import DepCab
from traitementCab import TraitementCab
import time
import json
class MouvCab:

    def __init__(self):
        sleepTime=2
        self.client=''
        mouving=DepCab(0.2)
        process=TraitementCab()
        
    def galileoThread(self,cab,client,path):
        if isinstance(cab,CabStatus):
            test=True
            while test:
                test=mouving.moveCab(cab,path)
                toGalileo={"numberofclients":len(process.listRequest),"cabstatus":cab.status,"distancetravelled":cab.cabinfo.odometre}
                tmpJSON=json.dumps(toGalileo)
                self.client.sendMessage(unicode(tmpJSON))
                time.sleep(2)


    def interfaceThread(self,cabs,client,clients):
        while len(cabs)<1:
            time.sleep(2)
        cab=self.updateCabs()
        while client in clients:
            tmpJSON=json.dumps(cab.cabinfo)
            self.client.sendMessage(unicode(tmpJSON))
            time.sleep(2)
                
    def updateCabs(self,cabs):
        return cabs[0]

    # Creating path
    def calcPath(self, area,cab):
       self.mouving.dijCall(cab,area)
       return self.mouving
                
