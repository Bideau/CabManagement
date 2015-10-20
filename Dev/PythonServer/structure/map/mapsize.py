class MapSize:
    def __init__(self):
        self.w = 0
        self.h = 0 
    def parsing(self,value):
        if 'w' in value:
            self.w = value['w']
        if 'h' in value:
            self.h = value['h']
