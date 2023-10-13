import random
from Buffer import Buffer
from Component import Component
from Extract_Data import Extract_Data


class Inspector2:
    def __init__(self, env, name, B3: Buffer, B5: Buffer) -> None:
        self.env = env
        self.name = name
        self.B3 = B3
        self.B5 = B5

    def inspect(self):
        # inspector inspects the component and places it in the smallest buffer
        selected_buffer = self.choose_buffer()
        if selected_buffer is self.B3:
            # place Component C2 in B3
            service_time = Extract_Data().get_servinsp22().iloc[0].values[0]
            self.B3.put(Component("C2", service_time))
            # drop used datapoint from dataframe, INPLACE
            Extract_Data().get_servinsp22().drop(
                Extract_Data().get_servinsp22().index[0], inplace=True
            )
            yield self.env.timeout(service_time)
            print(f"{self.name} finished inspecting 'C2' at {self.env.now:.2f}")

        else:
            # place Component C3 in B5
            service_time = Extract_Data().get_servinsp23().iloc[0].values[0]
            self.B5.put(Component("C3", service_time))
            # drop used datapoint from dataframe
            Extract_Data().get_servinsp23().drop(
                Extract_Data().get_servinsp23().index[0], inplace=True
            )
            yield self.env.timeout(service_time)
            print(f"{self.name} finished inspecting 'C3' at {self.env.now:.2f}")

    def choose_buffer(self):
        # 50% chance of choosing B3 and 50% chance of choosing B5
        if random.random() < 0.5:
            return self.B3
        else:
            return self.B5
