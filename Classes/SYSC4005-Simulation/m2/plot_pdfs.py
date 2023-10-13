import numpy as np
import scipy.stats as stats
import Extract_Data
import matplotlib.pyplot as plt


# import data from Extract_Data
Extract_Data = Extract_Data.Extract_Data()
ws1 = Extract_Data.get_ws1()
ws2 = Extract_Data.get_ws2()
ws3 = Extract_Data.get_ws3()
servinsp1 = Extract_Data.get_servinsp1()
servinsp22 = Extract_Data.get_servinsp22()
servinsp23 = Extract_Data.get_servinsp23()

# Fit the data to an exponential distribution
loc1, scale1 = stats.expon.fit(ws1)
loc2, scale2 = stats.expon.fit(ws2)
loc3, scale3 = stats.expon.fit(ws3)
loc4, scale4 = stats.expon.fit(servinsp1)
loc5, scale5 = stats.expon.fit(servinsp22)
loc6, scale6 = stats.expon.fit(servinsp23)

# fix linspace
ws1_lin = np.linspace(ws1.min(), ws1.max(), 100)
ws2_lin = np.linspace(ws2.min(), ws2.max(), 100)
ws3_lin = np.linspace(ws3.min(), ws3.max(), 100)
servinsp1_lin = np.linspace(servinsp1.min(), servinsp1.max(), 100)
servinsp22_lin = np.linspace(servinsp22.min(), servinsp22.max(), 100)
servinsp23_lin = np.linspace(servinsp23.min(), servinsp23.max(), 100)


def generateValue(seed=27, a=9516, c=0, m=32749):
    x = (a * seed + c) % m
    seed = x
    return x / m


def plot_inverse_cdfs():
    inv_cdf_ws1 = stats.expon.ppf(ws1_lin, loc=loc1, scale=scale1)
    inv_cdf_ws2 = stats.expon.ppf(ws2_lin, loc=loc2, scale=scale2)
    inv_cdf_ws3 = stats.expon.ppf(ws3_lin, loc=loc3, scale=scale3)
    inv_cdf_servinsp1 = stats.expon.ppf(servinsp1_lin, loc=loc4, scale=scale4)
    # inv_cdf_servinsp22 = stats.expon.ppf(servinsp22_lin, loc=loc5, scale=scale5)
    servinsp22_percentile = (servinsp22_lin - loc5) / scale5
    inv_cdf_servinsp22 = stats.expon.ppf(servinsp22_percentile)
    # inv_cdf_servinsp23 = stats.expon.ppf(servinsp23_lin, loc=loc6, scale=scale6)
    servinsp23_percentile = (servinsp23_lin - loc6) / scale6
    inv_cdf_servinsp23 = stats.expon.ppf(servinsp23_percentile)

    # print(inv_cdf_servinsp22, inv_cdf_servinsp23)
    # print(servinsp22_lin, servinsp23_lin)

    # Create a figure with 2 columns and 3 rows
    fig, axs = plt.subplots(3, 2, figsize=(10, 10))

    # Plot the inverse CDFs
    axs[0, 0].plot(ws1_lin, inv_cdf_ws1)
    axs[0, 0].set_title("Inverse CDF of ws1")
    axs[0, 0].set_xlabel("Probability")
    axs[0, 0].set_ylabel("Value")

    axs[0, 1].plot(ws2_lin, inv_cdf_ws2)
    axs[0, 1].set_title("Inverse CDF of ws2")
    axs[0, 1].set_xlabel("Probability")
    axs[0, 1].set_ylabel("Value")

    axs[1, 0].plot(ws3_lin, inv_cdf_ws3)
    axs[1, 0].set_title("Inverse CDF of ws3")
    axs[1, 0].set_xlabel("Probability")
    axs[1, 0].set_ylabel("Value")

    axs[1, 1].plot(servinsp1_lin, inv_cdf_servinsp1)
    axs[1, 1].set_title("Inverse CDF of servinsp1")
    axs[1, 1].set_xlabel("Probability")
    axs[1, 1].set_ylabel("Value")

    axs[2, 0].plot(servinsp22_lin, inv_cdf_servinsp22)
    axs[2, 0].set_title("Inverse CDF of servinsp22")
    axs[2, 0].set_xlabel("Probability")
    axs[2, 0].set_ylabel("Value")

    axs[2, 1].plot(servinsp23_lin, inv_cdf_servinsp23)
    axs[2, 1].set_title("Inverse CDF of servinsp23")
    axs[2, 1].set_xlabel("Probability")
    axs[2, 1].set_ylabel("Value")

    # Adjust the spacing between the subplots to avoid overlap
    fig.tight_layout(pad=3.0, w_pad=2.0, h_pad=2.0)

    # Display the plots
    plt.show()


def plot_pdfs():
    # Generate the PDFs
    ws1_pdf = stats.expon.pdf(ws1_lin, loc=loc1, scale=scale1)
    ws2_pdf = stats.expon.pdf(ws2_lin, loc=loc2, scale=scale2)
    ws3_pdf = stats.expon.pdf(ws3_lin, loc=loc3, scale=scale3)
    servinsp1_pdf = stats.expon.pdf(servinsp1_lin, loc=loc4, scale=scale4)
    servinsp22_pdf = stats.expon.pdf(servinsp22_lin, loc=loc5, scale=scale5)
    servinsp23_pdf = stats.expon.pdf(servinsp23_lin, loc=loc6, scale=scale6)

    # Create a figure with 2 columns and 3 rows
    fig, axs = plt.subplots(3, 2, figsize=(10, 10))

    # Plot the PDFs
    axs[0, 0].plot(ws1_lin, ws1_pdf)
    axs[0, 0].set_title("PDF of ws1")
    axs[0, 0].set_xlabel("Value")
    axs[0, 0].set_ylabel("Probability Density")

    axs[0, 1].plot(ws2_lin, ws2_pdf)
    axs[0, 1].set_title("PDF of ws2")
    axs[0, 1].set_xlabel("Value")
    axs[0, 1].set_ylabel("Probability Density")

    axs[1, 0].plot(ws3_lin, ws3_pdf)
    axs[1, 0].set_title("PDF of ws3")
    axs[1, 0].set_xlabel("Value")
    axs[1, 0].set_ylabel("Probability Density")

    axs[1, 1].plot(servinsp1_lin, servinsp1_pdf)
    axs[1, 1].set_title("PDF of servinsp1")
    axs[1, 1].set_xlabel("Value")
    axs[1, 1].set_ylabel("Probability Density")

    axs[2, 0].plot(servinsp22_lin, servinsp22_pdf)
    axs[2, 0].set_title("PDF of servinsp22")
    axs[2, 0].set_xlabel("Value")
    axs[2, 0].set_ylabel("Probability Density")

    axs[2, 1].plot(servinsp23_lin, servinsp23_pdf)
    axs[2, 1].set_title("PDF of servinsp23")
    axs[2, 1].set_xlabel("Value")
    axs[2, 1].set_ylabel("Probability Density")

    # fig.text(0.5, 0.04, "Value", ha="center")
    # fig.text(0.04, 0.5, "Probability Density", va="center", rotation="vertical")

    # Adjust the spacing between the subplots to avoid overlap
    fig.tight_layout(pad=3.0, w_pad=2.0, h_pad=2.0)

    # Display the plots
    plt.show()


def plot_cdfs():
    cdf_ws1 = stats.expon.cdf(ws1_lin, loc=loc1, scale=scale1)
    cdf_ws2 = stats.expon.cdf(ws2_lin, loc=loc2, scale=scale2)
    cdf_ws3 = stats.expon.cdf(ws3_lin, loc=loc3, scale=scale3)
    cdf_servinsp1 = stats.expon.cdf(servinsp1_lin, loc=loc4, scale=scale4)
    cdf_servinsp22 = stats.expon.cdf(servinsp22_lin, loc=loc5, scale=scale5)
    cdf_servinsp23 = stats.expon.cdf(servinsp23_lin, loc=loc6, scale=scale6)

    # Create a figure with 2 columns and 3 rows
    fig, axs = plt.subplots(3, 2, figsize=(10, 10))

    # Plot the PDFs
    axs[0, 0].plot(ws1_lin, cdf_ws1)
    axs[0, 0].set_title("CDF of ws1")
    axs[0, 0].set_xlabel("Value")
    axs[0, 0].set_ylabel("Cumulative Density")

    axs[0, 1].plot(ws2_lin, cdf_ws2)
    axs[0, 1].set_title("CDF of ws2")
    axs[0, 1].set_xlabel("Value")
    axs[0, 1].set_ylabel("Cumulative Density")

    axs[1, 0].plot(ws3_lin, cdf_ws3)
    axs[1, 0].set_title("CDF of ws3")
    axs[1, 0].set_xlabel("Value")
    axs[1, 0].set_ylabel("Cumulative Density")

    axs[1, 1].plot(servinsp1_lin, cdf_servinsp1)
    axs[1, 1].set_title("CDF of servinsp1")
    axs[1, 1].set_xlabel("Value")
    axs[1, 1].set_ylabel("Cumulative Density")

    axs[2, 0].plot(servinsp22_lin, cdf_servinsp22)
    axs[2, 0].set_title("CDF of servinsp22")
    axs[2, 0].set_xlabel("Value")
    axs[2, 0].set_ylabel("Cumulative Density")

    axs[2, 1].plot(servinsp23_lin, cdf_servinsp23)
    axs[2, 1].set_title("CDF of servinsp23")
    axs[2, 1].set_xlabel("Value")
    axs[2, 1].set_ylabel("Cumulative Density")

    # fig.text(0.5, 0.04, "Value", ha="center")
    # fig.text(0.04, 0.5, "Probability Density", va="center", rotation="vertical")

    # Adjust the spacing between the subplots to avoid overlap
    fig.tight_layout(pad=3.0, w_pad=2.0, h_pad=2.0)

    # Display the plots
    plt.show()


random_value = generateValue()
ws1_rand_value = stats.expon.ppf(random_value, loc=loc1, scale=scale1)
ws2_rand_value = stats.expon.ppf(random_value, loc=loc2, scale=scale2)
ws3_rand_value = stats.expon.ppf(random_value, loc=loc3, scale=scale3)
servinsp1_rand_value = stats.expon.ppf(random_value, loc=loc4, scale=scale4)
servinsp22_rand_value = stats.expon.ppf(random_value, loc=loc5, scale=scale5)
servinsp23_rand_value = stats.expon.ppf(random_value, loc=loc6, scale=scale6)

print(
    f"ws1_rand_value: {ws1_rand_value}, ws2_rand_value: {ws2_rand_value}, ws3_rand_value: {ws3_rand_value}, servinsp1_rand_value: {servinsp1_rand_value}, servinsp22_rand_value: {servinsp22_rand_value}, servinsp23_rand_value: {servinsp23_rand_value}"
)

# plot_inverse_cdfs()
