import Extract_Data
import pandas as pd
import matplotlib.pyplot as plt
import scipy.stats as stats
import numpy as np
import os


def build_qq_norm_plot(data, name):
    """Builds a Q-Q plot for the input data."""
    stats.probplot(data.values.ravel(), dist="norm", plot=plt)
    plt.title(f"{name}_Q-Q plot")
    os.makedirs("./m2/plots/QQ_plots", exist_ok=True)
    plt.savefig(f"./m2/plots/QQ_plots/{name}_qq_norm.png")
    plt.show()


def build_qq_expon_plot(data, name):
    """Builds a Q-Q plot for the input data."""
    stats.probplot(data.values.ravel(), dist="expon", plot=plt)
    plt.title(f"{name}_Q-Q plot_expon")
    # Create the directory if it doesn't exist
    os.makedirs("./m2/plots/QQ_plots", exist_ok=True)
    plt.savefig(f"./m2/plots/QQ_plots/{name}_qq_expon.png")
    plt.show()


def build_qq_poisson_plot(data, name):
    """Builds a Q-Q plot for the input data."""
    mu = np.mean(data)
    stats.probplot(data.values.ravel(), dist="poisson", sparams=(mu,), plot=plt)
    plt.title(f"{name}_Q-Q_poisson plot")
    # Create the directory if it doesn't exist
    os.makedirs("./m2/plots/QQ_plots", exist_ok=True)
    plt.savefig(f"./m2/plots/QQ_plots/{name}_qq_poisson.png")
    plt.show()


def build_poisson(data, name):
    """Builds a Poisson distribution plot for the input data."""
    mu = np.mean(data)
    x = np.arange(stats.poisson.ppf(0.01, mu), stats.poisson.ppf(0.99, mu))
    pmf = stats.poisson.pmf(x, mu)
    plt.plot(x, pmf, "bo", ms=8)
    plt.vlines(x, 0, pmf, colors="b", lw=5, alpha=0.5)  # type: ignore
    plt.title(f"{name}_Poisson distribution plot")
    plt.savefig(f"{name}_poisson.png", "./m2/plots/QQ_plots")
    plt.show()


def build_normal(data):
    """Builds a normal distribution plot for the input data."""
    mu, std = stats.norm.fit(data)
    xmin, xmax = plt.xlim()
    x = np.linspace(xmin, xmax, 100)
    p = stats.norm.pdf(x, mu, std)
    plt.plot(x, p, "k", linewidth=2)
    plt.title("Normal distribution plot")
    plt.show()


def build_exponential(data, name):
    """Builds an exponential distribution plot for the input data."""
    loc, scale = stats.expon.fit(data)
    xmin, xmax = plt.xlim()
    x = np.linspace(xmin, xmax, 100)
    p = stats.expon.pdf(x, loc, scale)
    plt.plot(x, p, "k", linewidth=2)
    plt.title(f"{name}_Exponential distribution plot")
    plt.savefig(f"{name}_exponential.png")
    plt.show()
    # save the plot


def build_weibull_constant_beta(data):
    """Builds a Weibull distribution plot with constant beta for the input data."""
    shape, loc, scale = stats.weibull_min.fit(data, floc=0)
    xmin, xmax = plt.xlim()
    x = np.linspace(xmin, xmax, 100)
    p = stats.weibull_min.pdf(x, shape, loc, scale)
    plt.plot(x, p, "k", linewidth=2)
    plt.title("Weibull distribution plot with constant beta")
    plt.show()


def build_weibull_double_param(data):
    """Builds a Weibull distribution plot with double parameters for the input data."""
    shape, loc, scale = stats.weibull_min.fit(data)
    xmin, xmax = plt.xlim()
    x = np.linspace(xmin, xmax, 100)
    p = stats.weibull_min.pdf(x, shape, loc, scale)
    plt.plot(x, p, "k", linewidth=2)
    plt.title("Weibull distribution plot with double parameters")
    plt.show()


def build_log_normal(data):
    """Builds a log-normal distribution plot for the input data."""
    s, mu, _ = stats.lognorm.fit(data)
    xmin, xmax = plt.xlim()
    x = np.linspace(xmin, xmax, 100)
    p = stats.lognorm.pdf(x, s=s, loc=0, scale=np.exp(mu))
    plt.plot(x, p, "k", linewidth=2)
    plt.title("Log-normal distribution")
    plt.show()


# import data from Extract_Data
Extract_Data = Extract_Data.Extract_Data()
ws1 = Extract_Data.get_ws1()
ws2 = Extract_Data.get_ws2()
ws3 = Extract_Data.get_ws3()
servinsp1 = Extract_Data.get_servinsp1()
servinsp22 = Extract_Data.get_servinsp22()
servinsp23 = Extract_Data.get_servinsp23()

# build_qq_plot(ws1)
# build_poisson(ws1)
# build_normal(ws1)
# build_exponential(ws1)
# build_weibull_constant_beta(ws1)
# build_weibull_double_param(ws1)
# build_qq_expon_plot(ws1, "ws1")
# build_qq_expon_plot(ws2, "ws2")
# build_qq_expon_plot(ws3, "ws3")
# build_qq_poisson_plot(servinsp1, "servinsp1")
build_qq_expon_plot(servinsp1, "servinsp1")
build_qq_expon_plot(servinsp22, "servinsp22")
build_qq_expon_plot(servinsp23, "servinsp23")
