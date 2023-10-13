import Extract_Data
import pandas as pd
import matplotlib.pyplot as plt


def calculate_iqr(data: pd.DataFrame) -> pd.Series:
    q1 = data.quantile(0.25)
    q3 = data.quantile(0.75)
    iqr = q3 - q1
    return iqr.rename("iqr")


def calculate_bin_width(data: pd.DataFrame, iqr):
    n = len(data)
    bin_width = 2 * iqr / n ** (1 / 3)
    return int(round((data.max() - data.min()) / bin_width))


# import data from Extract_Data
Extract_Data = Extract_Data.Extract_Data()
ws1 = Extract_Data.get_ws1()
ws2 = Extract_Data.get_ws2()
ws3 = Extract_Data.get_ws3()
servinsp1 = Extract_Data.get_servinsp1()
servinsp22 = Extract_Data.get_servinsp22()
servinsp23 = Extract_Data.get_servinsp23()

# calculate iqr for each data set
ws1_iqr = calculate_iqr(ws1).iloc[0]
ws2_iqr = calculate_iqr(ws2).iloc[0]
ws3_iqr = calculate_iqr(ws3).iloc[0]
servinsp1_iqr = calculate_iqr(servinsp1).iloc[0]
servinsp22_iqr = calculate_iqr(servinsp22).iloc[0]
servinsp23_iqr = calculate_iqr(servinsp23).iloc[0]

# concatenate the iqr series into a single dataframe
# iqr_df = pd.concat(
#     [ws1_iqr, ws2_iqr, ws3_iqr, servinsp1_iqr, servinsp22_iqr, servinsp23_iqr], axis=1
# )

# ws1_n = len(ws1)
# ws1_bin_width = 2 * ws1_iqr * (ws1_n ** (-1 / 3))
# print(f"ws1_iqr: {ws1_iqr}, ws1_n: {ws1_n}, ws1_bin_width: {ws1_bin_width}")

# plot the data using histogram and calculated width
bins_ws1 = calculate_bin_width(ws1, ws1_iqr)
bins_ws2 = calculate_bin_width(ws2, ws2_iqr)
bins_ws3 = calculate_bin_width(ws3, ws3_iqr)
bins_servinsp1 = calculate_bin_width(servinsp1, servinsp1_iqr)
bins_servinsp22 = calculate_bin_width(servinsp22, servinsp22_iqr)
bins_servinsp23 = calculate_bin_width(servinsp23, servinsp23_iqr)

# print all bins
print(
    f"bins_ws1: {bins_ws1}, bins_ws2: {bins_ws2}, bins_ws3: {bins_ws3}, bins_servinsp1: {bins_servinsp1}, bins_servinsp22: {bins_servinsp22}, bins_servinsp23: {bins_servinsp23}"
)

fig, axes = plt.subplots(nrows=3, ncols=2, figsize=(10, 10))

# plot each histogram on its own subplot
axes[0, 0].hist(ws1, bins=bins_ws1)
axes[0, 0].set_title("ws1")
axes[0, 0].set_xlabel(f"Bin Size: {bins_ws1}")

axes[0, 1].hist(ws2, bins=bins_ws2)
axes[0, 1].set_title("ws2")
axes[0, 1].set_xlabel(f"Bin Size: {bins_ws2}")

axes[1, 0].hist(ws3, bins=bins_ws3)
axes[1, 0].set_title("ws3")
axes[1, 0].set_xlabel(f"Bin Size: {bins_ws3}")

axes[1, 1].hist(servinsp1, bins=bins_servinsp1)
axes[1, 1].set_title("servinsp1")
axes[1, 1].set_xlabel(f"Bin Size: {bins_servinsp1}")

axes[2, 0].hist(servinsp22, bins=bins_servinsp22)
axes[2, 0].set_title("servinsp22")
axes[2, 0].set_xlabel(f"Bin Size: {bins_servinsp22}")

axes[2, 1].hist(servinsp23, bins=bins_servinsp23)
axes[2, 1].set_title("servinsp23")
axes[2, 1].set_xlabel(f"Bin Size: {bins_servinsp23}")

# adjust layout and show the plot
# set common labels
fig.text(0.5, 0.04, "Values", ha="center")
fig.text(0.04, 0.5, "Frequency", va="center", rotation="vertical")

# adjust spacing between subplots
plt.subplots_adjust(left=0.1, bottom=0.1, right=0.9, top=0.9, wspace=0.4, hspace=0.4)

# show the plot
plt.show()

# save the plots
fig.savefig("./m2/plots/data_histogram_plots.png")

# # plot all 6 histograms
# plt.hist(ws1, bins=bins_ws1, label="ws1")
# plt.hist(ws2, bins=bins_ws2, label="ws2")
# plt.hist(ws3, bins=bins_ws3, label="ws3")
# plt.hist(servinsp1, bins=bins_servinsp1, label="servinsp1")
# plt.hist(servinsp22, bins=bins_servinsp22, label="servinsp22")
# plt.hist(servinsp23, bins=bins_servinsp23, label="servinsp23")

# # show the plot
# plt.legend()
# plt.show()
