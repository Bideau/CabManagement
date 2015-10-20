class Dest:

    def __init__(self):
        self.area = ''
        self.vertex = ''
    def parsing(self,value):
        if 'area' in value:
            self.area = value['area']
        if 'vertex' in value:
            self.vertex = value['vertex']
