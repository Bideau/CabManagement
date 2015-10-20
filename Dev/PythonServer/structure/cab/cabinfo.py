from cabrequest import CabRequest
from locvertex import LocVertex
class CabInfo:
    def __init__(self):
        self.odometre = 0
        self.destination = CabRequest()
        self.locNow = LocVertex()
        self.locNext = LocVertex()
    def parsing(self,value):
        if 'odometer' in value:
            self.odometre = value['odometer']
        if 'destination' in value:
            self.destination.parsing(value['destination'])
        if 'locNow' in value:
            self.locNow.parsing(value['locNow'])
        if 'locNext' in value:
            self.locNext.parsing(value['locNext'])

