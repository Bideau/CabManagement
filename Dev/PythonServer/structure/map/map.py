from mapsize import MapSize
from vertex import Vertex
from street import Street
from bridge import Bridge
class Map:
    def __init__(self):
        self.verticesList = []
        self.streetsList = []
        self.bridgesList = []
        self.weight = MapSize()   
    def parsing (self,value):
        if 'vertices' in value:
            for i in value['vertices']:
                tmpVertex = Vertex()
                tmpVertex.parsing(i)
                self.verticesList.append(tmpVertex)
        if 'streets' in value:
            for i in value['streets']:
                tmpStreet = Street()
                tmpStreet.parsing(i)
                self.streetsList.append(tmpStreet)
        if 'bridges' in value:
            for i in value['bridges']:
                tmpBridge = Bridge()
                tmpBridge.parsing(i)
                self.bridgesList.append(tmpBridge)
        if 'weight' in value:
            self.weight.parsing(value['weight'])
    def __str__(self):
        string= "Weight : %s; Vertices : " % self.weight
        for i in self.verticesList:
            string = " %s %s" % (string,i)
        string = " %s; Street : " % (string)
        for i in self.streetsList:
            string = " %s %s" % (string,i)
        string = " %s; Bridge : " % (string)
        for i in self.bridgesList:
            string = " %s %s" % (string,i)
        return string
