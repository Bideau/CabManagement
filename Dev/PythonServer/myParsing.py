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
        tmpCab.status='free'
        self.cabList.append(tmpCab)
        return tmpCab

    # Parsing function of a cab request
    def parsingCabReq(self,myJSON):
	print 'Parsing req cab'
        json_data = json.loads(myJSON)
        tmpCab = CabRequest()
        tmpCab.parsing(json_data)
        # Adding to the cab request list
        self.request(tmpCab)
        return tmpCab

    # Add a new request to the list 
    def request(self,newRequest):
        if isinstance(newRequest,CabRequest):
            self.listRequest.append(newRequest)

    # Parsing function of galileo message
    # Return true if status update
    # Return false if error or cab busy
    def parsingRepArdui(self,myJSON,cab):
	print 'Parsing req ardui'
	print myJSON
	print self.listRequest
	try:
        	json_data = json.loads(myJSON)
	except ValueError, e:
        	verif=False
	else:
                print 'verif arduino'
        	verif=False
        	if 'answer' in json_data:
            		if isinstance(cab,CabStatus):
                		if cab.status == 'free':
                    			if len(self.listRequest) > 0:
						print json_data['answer']
                        			if json_data['answer'] == 'true':
                            				cab.status='busy'
                            				cab.cabinfo.destination = self.listRequest[0]
                            				cab.cabinfo.odometer = 0
                            				verif=True
                        			del self.listRequest[0]
	print 'Verif %s' % verif
	return verif
    
    # Creating path
    def calcPath(self, area,cab):
        self.movement.dijCall(cab,area)
        return self.movement

