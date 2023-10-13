import queue

from Component import Component

class Buffer:
    def __init__(self, name:str, maxsize=2) -> None:
        self.name = name
        self.buffer = queue.Queue(maxsize=maxsize)
    
    def __len__(self):
        return self.buffer.qsize()
    
    def put(self, component: Component):
        self.buffer.put(component)
        
    def get(self):
        return self.buffer.get()
    
    def get_name(self):
        return self.name