class Street:
    def __init__(self):
        self.name = ''
        self.oneway = 'false'
        self.path = []
    def parsing (self,value):
        if 'name' in value:
            self.name = value['name']
        if 'oneway' in value:
            self.oneway = value['oneway']
        if 'path' in value:
            for i in value['path']:
                self.path.append(i)
