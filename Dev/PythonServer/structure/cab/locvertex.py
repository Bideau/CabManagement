class LocVertex:
    def __init__(self):
        self.area = ''
        self.location = ''
        self.locationType = ''
        self.progression = 1

    def parsing(self,value):
#        print 'LocVertex parsing'
 #       print value
        if 'area' in value:
            self.area = value['area']
        if 'location' in value:
            self.location = value['location']
        if 'locationType' in value:
            self.locationType = value['locationType']
        if 'progression' in value:
            self.progression = value['progression']

    def __eq__(self,other):
        test=True
        if self.area != other.area:
            test=False
        if self.location != other.location:
            test=False
        if self.locationType != other.locationType:
            test=False
        if self.progression != other.progression:
            test=False
        return test
