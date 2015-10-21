class Vertex:
    def __init__(self):
        self.name = ''
        self.x = 0.0
        self.y = 0.0
    def parsing (self,value):
        if 'name' in value:
            self.name = value['name']
        if 'x' in value:
            self.x = value['x']
        if 'y' in value:
            self.y = value['y']
    def __str__(self):
        return "Name : %s; X : %s; Y : %s" % (self.name,self.x,self.y)
