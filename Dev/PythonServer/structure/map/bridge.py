from dest import Dest
class Bridge:
    def __init__(self):
        self.src = ''
        self.weight = 0.0
        self.to = Dest()
    def parsing(self,value):
        if 'src' in value:
            self.src = value['src']
        if 'weight' in value:
            self.weight = value['weight']
        if 'to' in value:
            self.to.parsing(value['to'])
