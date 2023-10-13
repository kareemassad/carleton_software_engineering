import pandas as pd


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

    def get_sim_duration(self):
        return 50


# def main():
#     extract_data = Extract_Data()
#     # print(extract_data.get_servinsp1().columns)
#     print(extract_data.get_servinsp1().iloc[0].values[0])
#     # drop the first row in place
#     extract_data.get_servinsp1().drop(
#         extract_data.get_servinsp1().index[0], inplace=True
#     )
#     print(extract_data.get_servinsp1().iloc[0].values)
#     extract_data.get_servinsp1().drop(
#         extract_data.get_servinsp1().index[0], inplace=True
#     )
#     print(extract_data.get_servinsp1().iloc[0].values)
#     # print(extract_data.get_servinsp22().head())
#     # print(extract_data.get_servinsp33().head())
#     # print(extract_data.get_ws1().head())
#     # print(extract_data.get_ws2().head())
#     # print(extract_data.get_ws3().head())


# if __name__ == "__main__":
#     main()
