from Buffer import Buffer
from Extract_Data import Extract_Data


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
            if self.buffers[0].get_name() == "B1":
                # Single buffer must be B1 with only C1
                # while True:
                # C1 now used, get() auto removes
                component = self.buffers[0].get()

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
                self.buffers[0].get_name() == "B2"
                and self.buffers[1].get_name() == "B3"
            ):
                # we are in WS2
                service_time = Extract_Data().get_ws2().iloc[0].values[0]
                # drop used datapoint from dataframe, INPLACE
                Extract_Data().get_ws2().drop(
                    Extract_Data().get_ws2().index[0], inplace=True
                )
                # while True:
                # C1 & C2 now used, get() auto removes
                component1 = self.buffers[0].get()
                component2 = self.buffers[1].get()
                yield self.env.timeout(service_time)
                print(
                    f"{self.name} made P2 with {component1.name} and {component2.name} at {self.env.now:.2f}"
                )

            elif (
                self.buffers[0].get_name() == "B4"
                and self.buffers[1].get_name() == "B5"
            ):
                # we are in WS2
                service_time = Extract_Data().get_ws3().iloc[0].values[0]
                # drop used datapoint from dataframe, INPLACE
                Extract_Data().get_ws3().drop(
                    Extract_Data().get_ws3().index[0], inplace=True
                )
                # while True:
                # C1 & C3 now used, get() auto removes
                component1 = self.buffers[0].get()
                component2 = self.buffers[1].get()

                yield self.env.timeout(service_time)
                print(
                    f"{self.name} made P3 with {component1.name} and {component2.name} at {self.env.now:.2f}"
                )
