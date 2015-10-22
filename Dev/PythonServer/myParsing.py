import json
from structure.map import Area
from structure.cab import CabRequest
from structure.cab import CabInfo
from structure.cab import CabStatus
class myParsing:
    
    def __init__(self):
        self.cabList=[]
        self.listRequest=[]

    # Read a file and return a string
    def readFile(self,path):
        myFile = open(path)
        myJSON = myFile.read()
        myFile.close()
        return myJSON
        

    # Parsing function of the map
    def parsingArea (self,myJSON):
        carte=[]
        json_data = json.loads(myJSON)
        if 'areas' in json_data:
            for i in json_data['areas']:
                tmpArea = Area()
                tmpArea.parsing(i)
                carte.append(tmpArea)
        return carte

    # Parsing function of the cab
    def parsingCabInfo(self,myJSON):
        json_data = json.loads(myJSON)
        tmpCab = CabStatus()
        test = CabInfo()
        test.parsing(json_data)
        tmpCab.cabinfo=test
        self.cabList.append(tmpCab)
        return tmpCab

    # Parsing function of a cab request
    def parsingCabReq(self,myJSON):
        json_data = json.loads(myJSON)
        tmpCab = CabRequest()
        tmpCab.parsing(json_data)
        # Adding to the cab request list
        self.request(tmpCab)
        self.calcPath(self.carte,self.cabList[0])
        return tmpCab

    # Add a new request to the list 
    def request(self,newRequest):
        if isinstance(newRequest,CabRequest):
            self.listRequest.append(newRequest)

    # Parsing function of galileo message
    # Return true if status update
    # Return false if error or cab busy
    def parsingRepArdui(self,myJSON,cab):
        json_data = json.loads(myJSON)
        verif=False
        if 'answer' in json_data:
            if isinstance(cab,CabStatus):
                if cab.status == 'free':
                    if len(listRequest) > 0:
                        if json_data[answer] == 'true':
                            cab.status='busy'
                            cab.cabinfo.destination = listRequest[0]
                            cab.cabinfo.odometer = 0
                            verif=True
                        del listRequest[0]
        return verif
    
    # Creating path
    def calcPath(self, area,cab):
        self.movement.dijCall(cab,area)
        return self.movement

