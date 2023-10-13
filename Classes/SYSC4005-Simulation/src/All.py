import queue
import pandas as pd


class Component:
    def __init__(self, name, service_time) -> None:
        self.name = name
        self.service_time = service_time


class Buffer:
    def __init__(self, name: str, maxsize=2) -> None:
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


class Workstation:
    def __init__(self, env, name: str, buffers: list[Buffer]) -> None:
        self.env = env
        self.name = name
        self.buffers = buffers

    def make_product(self):
        if len(self.buffers) == 1:
            # we are in WS1
            service_time = Extract_Data().get_ws1().iloc[0].values[0]
            # drop used datapoint from dataframe, INPLACE
            Extract_Data().get_ws1().drop(
                Extract_Data().get_ws1().index[0], inplace=True
            )
            if self.buffers[0].get_name() is "B1":
                # Single buffer must be B1 with only C1
                while True:
                    component = yield self.buffers[0].get()
                    yield self.env.timeout(service_time)
                    print(
                        f"{self.name} made P1 with {component.name} at {self.env.now:.2f}"
                    )
            else:
                print("Error: Workstation can only have B1 as buffer")
        if len(self.buffers) == 2:
            # Two buffers must be B2 and B3 with C1 and C2
            # OR
            # Two buffers must be B4 and B5 with C1 and C3
            if (
                self.buffers[0].get_name() is "B2"
                and self.buffers[1].get_name() is "B3"
            ):
                # we are in WS2
                service_time = Extract_Data().get_ws2().iloc[0].values[0]
                # drop used datapoint from dataframe, INPLACE
                Extract_Data().get_ws2().drop(
                    Extract_Data().get_ws2().index[0], inplace=True
                )
                while True:
                    component1 = yield self.buffers[0].get()
                    component2 = yield self.buffers[1].get()
                    yield self.env.timeout(service_time)
                    print(
                        f"{self.name} made P2 with {component1.name} and {component2.name} at {self.env.now:.2f}"
                    )
            elif (
                self.buffers[0].get_name() is "B4"
                and self.buffers[1].get_name() is "B5"
            ):
                # we are in WS2
                service_time = Extract_Data().get_ws3().iloc[0].values[0]
                # drop used datapoint from dataframe, INPLACE
                Extract_Data().get_ws3().drop(
                    Extract_Data().get_ws3().index[0], inplace=True
                )
                while True:
                    component1 = yield self.buffers[0].get()
                    component2 = yield self.buffers[1].get()
                    yield self.env.timeout(service_time)
                    print(
                        f"{self.name} made P3 with {component1.name} and {component2.name} at {self.env.now:.2f}"
                    )


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
        else:
            # is None
            print(f"{self.name} inspected 'C1' at {self.env.now:.2f} and discarded it")

        yield self.env.timeout(service_time)

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


class Extract_Data:
    # inspector service time : servinsp1.dat
    # inspector 2 service time FOR C2 : servinsp22.dat
    # inspector 2 service time FOR C3 : servinsp23.dat

    # Workstation 1 service time : ws1.dat
    # Workstation 2 service time : ws2.dat
    # Workstation 3 service time : ws3.dat

    def __init__(self) -> None:
        self.servinsp1 = pd.read_csv("./data/servinsp1.dat", header=None)
        self.servinsp22 = pd.read_csv("./data/servinsp22.dat", header=None)
        self.servinsp33 = pd.read_csv("./data/servinsp23.dat", header=None)
        self.ws1 = pd.read_csv("./data/ws1.dat", header=None)
        self.ws2 = pd.read_csv("./data/ws2.dat", header=None)
        self.ws3 = pd.read_csv("./data/ws3.dat", header=None)

    def get_servinsp1(self):
        return self.servinsp1

    def get_servinsp22(self):
        return self.servinsp22

    def get_servinsp23(self):
        return self.servinsp33

    def get_ws1(self):
        return self.ws1

    def get_ws2(self):
        return self.ws2

    def get_ws3(self):
        return self.ws3
