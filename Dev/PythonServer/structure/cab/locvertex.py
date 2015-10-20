class LocVertex:
    def __init__(self):
        self.area = ''
        self.location = ''
        self.locationType = ''
        self.progression = 0
    def parsing(self,value):
        if 'area' in value:
            self.area = value['area']
        if 'location' in value:
            self.location = value['location']
        if 'locationType' in value:
            self.locationType = value['locationType']
        if 'progression' in value:
            self.progression = value['progression']
