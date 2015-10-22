from structure.cab import CabStatus
import dijkstra
class DepCab:
    def __init__(self,progress):
        self.progress=progress

    # move the cab on the street 
    def moveCab(self,cab,path):
        verif = False
        if isinstance(cab,CabStatus):
            if cab.cabinfo.locNow.progression < 1:
                cab.cabinfo.locNow.progression= cab.cabinfo.locNow.progression +progress
            else:
                if cab.cabinfo.locNow == cab.cabinfo.destination.location:
                    cab.status='free'
                    verif = True
                else:
                    cab.cabinfo.locNow = cab.cabinfo.locNext
                    cab.cabinfo.locNow.progression =0
                    cab.cabinfo.locNext=path[0]
                    del path[0]
        return verif

    # Call the dijskstra function to find the path
    def dijCall(self,cab,area):                
        dic ={}
        # Getting area local
        for i in area:
            if cab.cabinfo.locNow.area == i.name:
                tmpArea = i
        if isinstance(cab,CabStatus):
            tmpStart = "%s@%s" % (cab.cabinfo.locNow.location,cab.cabinfo.locNow.area)
            tmpEnd = "%s@%s" % (cab.cabinfo.destination.location.location,cab.cabinfo.destination.area)
            if cab.cabinfo.locNow.area == cab.cabinfo.destination.area:
                dic=self.dicCrea()
            else:
                dic=self.dicCreaArea(area)    
            return dijkstra.dij_rec(dic,tmpStart,tmpEnd)
            
    # Create the dictionnaire 
    def dicCrea(self,area):
        dic={}
        for i in area.map.verticesList:
            neigh={}
            for j in area.map.streetsList:
                if j.path[0] == i.name:
                    neigh[j.path[1]+'@'+area.name]=1
                if not(j.oneway):
                    if j.path[1] == i.name:
                        neigh[j.path[0]+'@'+area.name]=1
                
            for j in area.map.bridgesList:
                if j.src == i.name:
                    neigh[j.to.vertex+'@'+area.name]=1
                if j.to.vertex == i.name:
                    neigh[j.src+'@'+area.name]=1
            dic[i.name+'@'+area.name]=neigh
            print dic
        return dic 

    # Create the dictionnaire for multiple area 
    def dicCreaArea(self,tmpArea):
        dic={}
        for area in tmpArea:
            for i in area.map.verticesList:
                neigh={}
                for j in area.map.streetsList:
                    if j.path[0] == i.name:
                        neigh[j.path[1]+'@'+area.name]=1
                    if not(j.oneway):
                        if j.path[1] == i.name:
                            neigh[j.path[0]+'@'+area.name]=1
                
                for j in area.map.bridgesList:
                    if j.src == i.name:
                        neigh[j.to.vertex+'@'+j.to.area]=1
                    if j.to.vertex == i.name:
                        neigh[j.src+'@'+area.name]=1
                dic[i.name+'@'+area.name]=neigh
        return dic 
  

                
