from structure.cab import CabRequest
from depcab import DepCab
class TraitementCab:

    def __init__(self):
        self.listRequest=[]
        self.movement=DepCab()
    
    # Add a new request to the list    
    def request(self,newRequest):
        if isinstance(newRequest,CabRequest):
            self.listRequest.append(newRequest)

    # Set the cab Status
    def cabReponse(self,answer,cab):
        if isinstance(cab,CabStatus):
            if answer:
                cab.status='busy'
            else:
                cab.status='free'
            cab.cabinfo.destination = listRequest[0] 
            cab.cabinfo.odometer = 0
            del listRequest[0]

    def calcPath(self, area,cab):
        self.movement.dijCall(cab,area)
        print self.movement           
