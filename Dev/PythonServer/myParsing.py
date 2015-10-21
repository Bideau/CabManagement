import json
from structure.map import Area
from structure.cab import CabRequest
from structure.cab import CabInfo
from structure.cab import CabStatus
from traitementCab import TraitementCab
class myParsing:
    
    def __init__(self):
        self.carte=[]
        self.process=TraitementCab()
        self.cabList=[]
        self.myFile=''

    def readFile(self,path):
        self.myFile = open(path)
        myJSON = self.myFile.read()
        return myJSON
        

    # Parsing function of the map
    def parsingArea (self,myJSON):
        json_data = json.loads(myJSON)
        if 'areas' in json_data:
            for i in json_data['areas']:
                tmpArea = Area()
                tmpArea.parsing(i)
                self.carte.append(tmpArea)
               # print tmpArea
        self.myFile.close()

    # Parsing function of the cab
    def parsingCabInfo(self,myJSON):
        json_data = json.loads(myJSON)
        tmpCab = CabStatus()
        test = CabInfo()
        test.parsing(json_data)
        tmpCab.cabinfo=test
        self.cabList.append(tmpCab)
        self.myFile.close()

    # Parsing function of a cab request
    def parsingCabReq(self,myJSON):
        json_data = json.loads(myJSON)
        tmpCab = CabRequest()
        tmpCab.parsing(json_data)
        # Adding to the cab request list
        self.process.request(tmpCab)
        self.process.calcPath(self.carte,self.cabList[0])
        self.myFile.close()

    def parsingRepArdui(self,myJSON):
        json_data = json.loads(myJSON)

test = myParsing()
test.parsingArea(test.readFile('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json'))
test.parsingCabInfo(test.readFile('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi2.json'))
test.parsingCabReq(test.readFile('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/request2.json'))

