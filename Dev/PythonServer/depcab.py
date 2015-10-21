from structure.cab import CabStatus
import dijkstra
class DepCab:
    def __init__(self):
        path =[]

    # move the cab on the street 
    def moveCab(self,cab):
        if isinstance(cab,CabStatus):
            if cab.cabinfo.locNow.progression < 1:
                cab.cabinfo.locNow.progression= cab.cabinfo.locNow.progression +0.2
            else:
                if cab.cabinfo.locNow == cab.cabinfo.destination.location:
                    cab.status='free'
                else:
                    cab.cabinfo.locNow = cab.cabinfo.locNext
                    cab.cabinfo.locNow.progression =0
                    cab.cabinfo.locNext=path[0]
                    del path[0]

    # Call the dijskstra function to find the path
    def dijCall(self,cab,area):                
        dic ={}
        for i in area:
            if cab.cabinfo.locNow.area == i.name:
                tmpArea = i
        if isinstance(cab,CabStatus):
            tmpStart = "%s@%s" % (cab.cabinfo.locNow.location,cab.cabinfo.locNow.area)
            tmpEnd = "%s@%s" % (cab.cabinfo.destination.location.location,cab.cabinfo.destination.area)
            print tmpStart
            print tmpEnd
            if cab.cabinfo.locNow.area == cab.cabinfo.destination.area:
                dic=self.dicCrea()
                print dic
                print dijkstra.dij_rec(dic,tmpStart,tmpEnd)
            else:
                dic=self.dicCreaArea(area)    
                #tmpDic=self.dicCrea(i)
                    #dic = dict(dic.items() + tmpDic.items())
                print dic
                print dijkstra.dij_rec(dic,tmpStart,tmpEnd)
            
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
  

                
