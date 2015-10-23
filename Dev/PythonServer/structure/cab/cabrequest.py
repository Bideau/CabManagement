from locvertex import LocVertex
class CabRequest:
    def __init__(self):
        self.area = ''
        self.location = LocVertex()
    def parsing(self,value):
#        print 'Request parsing'
        if 'area' in value:
            self.area = value['area']
        if 'location' in value:
            self.location.parsing(value['location'])
