import json
from structure.map import Area
from structure.cab import CabInfo
class myParsing:
    
    def __init__(self):
        self.carte=[]
        self.pathMap = ''
        self.pathCab = ''


    # Parsing function of the map
    def parsingArea (self,pathMap):
        self.pathMap = pathMap
        myFile = open(self.pathMap)
        myJSON = myFile.read()
        json_data = json.loads(myJSON)
        if 'areas' in json_data:
            for i in json_data['areas']:
                tmpArea = Area()
                #tmpArea.name = i['name']
                tmpArea.parsing(i)
                self.carte.append(tmpArea)
            print self.carte[0]
        myFile.close()

    # Parsing function of the cab
    def parsingCab(self,pathCab):
        self.pathCab = pathCab
        myFile = open(self.pathCab)
        myJSON = myFile.read()
        print myJSON
        json_data = json.loads(myJSON)
        tmpCab = CabInfo()
        tmpCab.parsing(json_data)
        myFile.close()
            

test = myParsing()
test.parsingArea('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/Initialize.json')
test.parsingCab('/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json')

