from Buffer import Buffer
from Component import Component
from Extract_Data import Extract_Data


class Inspector1:
    def __init__(self, env, name, B1: Buffer, B2: Buffer, B4: Buffer) -> None:
        self.env = env
        self.name = name
        self.B1 = B1
        self.B2 = B2
        self.B4 = B4

    def inspect(self):
        # inspector inspects the component and places it in the smallest buffer
        smallest_buffer = self.get_smallest_buffer()
        service_time = Extract_Data().get_servinsp1().iloc[0].values[0]
        if smallest_buffer is not None:
            smallest_buffer.put(
                Component("C1", Extract_Data().get_servinsp1().iloc[0].values[0])
            )
            # drop used datapoint from dataframe, INPLACE
            Extract_Data().get_servinsp1().drop(
                Extract_Data().get_servinsp1().index[0], inplace=True
            )
            yield self.env.timeout(service_time)
            print(f"{self.name} finished inspecting 'C1' at {self.env.now:.2f}")

        else:
            # is None
            yield self.env.timeout(service_time)
            print(f"{self.name} inspected 'C1' at {self.env.now:.2f} and discarded it")

    def get_smallest_buffer(self) -> Buffer | None:
        buffer_sizes = [len(self.B1), len(self.B2), len(self.B4)]
        # smallest_buffer_index = buffer_sizes.index(min(buffer_sizes))
        if buffer_sizes[0] != 2 and buffer_sizes[1] != 2 and buffer_sizes[2] != 2:
            if min(buffer_sizes) == len(self.B1):
                return self.B1
            elif min(buffer_sizes) == len(self.B2):
                return self.B2
            elif min(buffer_sizes) == len(self.B4):
                return self.B4
        else:
            return None
