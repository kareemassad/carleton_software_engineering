import simpy
import Buffer, Component, Extract_Data, Inspector1, Inspector2, Workstation


def main():
    # B1, B2, B3, B4, B5, Inspector1, then inspector2, Ws1, Ws2, Ws3

    # #init env
    env = simpy.Environment()

    # init data
    data = Extract_Data.Extract_Data()

    # init buffers
    B1 = Buffer.Buffer("B1")
    B2 = Buffer.Buffer("B2")
    B3 = Buffer.Buffer("B3")
    B4 = Buffer.Buffer("B4")
    B5 = Buffer.Buffer("B5")

    # init inspectors
    inspector1 = Inspector1.Inspector1(env, "Inspector1", B1, B2, B4)
    inspector2 = Inspector2.Inspector2(env, "Inspector2", B3, B5)

    # init workstations
    ws1 = Workstation.Workstation(env, "Workstation1", [B1])
    ws2 = Workstation.Workstation(env, "Workstation2", [B2, B3])
    ws3 = Workstation.Workstation(env, "Workstation3", [B4, B5])

    # start simulatin'
    env.process(inspector1.inspect())
    env.process(inspector2.inspect())
    env.process(ws1.make_product())
    # env.process(ws2.make_product())
    # env.process(ws3.make_product())

    # set simulation duration
    #
    env.run(until=data.get_sim_duration())


if __name__ == "__main__":
    main()
