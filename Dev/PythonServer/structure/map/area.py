from map import Map
class Area:
    #Area object
    def __init__(self):
        self.name = ''
        self.map = Map()
    def parsing(self,value):
        if 'name' in value:   
            self.name = value['name']
        if 'map' in value:   
            self.map.parsing(value['map'])
    def __str__(self):
        return "Name : %s ; Map : %s" % (self.name,self.map)
