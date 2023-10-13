import numpy as np
from scipy.stats import t
import Extract_Data


class TwoSidedTTest:
    def __init__(self, data, alpha=0.05):
        self.data = data
        self.alpha = alpha
        self.mean = np.mean(data)
        self.std_dev = np.std(data, ddof=1)
        self.n = len(data)
        self.t_val = t.ppf(1 - alpha / 2, self.n - 1)

    def confidence_interval(self):
        width = self.t_val * self.std_dev / np.sqrt(self.n)
        lower_bound = self.mean - width
        upper_bound = self.mean + width
        return (lower_bound, upper_bound)

    def reject_null_hypothesis(self, mu):
        se = self.std_dev / np.sqrt(self.n)
        t_stat = (self.mean - mu) / se
        p_val = 2 * (1 - t.cdf(abs(t_stat), self.n - 1))
        return p_val < self.alpha

    def validate_model(self, epsilon):
        ci = self.confidence_interval()
        best_case_error = abs(self.mean - ci[0])
        worst_case_error = abs(self.mean - ci[1])

        if ci[0] > epsilon or ci[1] < epsilon:
            print("CI does not contain epsilon")
            if best_case_error > epsilon:
                print(
                    "Best-case error is > epsilon, model needs to be revised and improved."
                )
            elif worst_case_error <= epsilon:
                print("Worst-case error is <= epsilon, accept the model.")
            else:
                print(
                    "Best-case error is <= epsilon, additional replications are necessary."
                )

        else:
            print("CI contains epsilon")
            if worst_case_error > epsilon or best_case_error > epsilon:
                print(
                    "Either the best-case or worst-case error is > epsilon, additional replications are necessary."
                )
            else:
                print("Worst-case error is <= epsilon, accept the model.")


class ColumnTTest:
    def __init__(self, col, epsilon, replications_factor):
        self.col = col
        self.epsilon = epsilon
        self.t_test = TwoSidedTTest(col)
        self.replications_factor = replications_factor

    def validate_model(self):
        print(f"Column {self.col.name}:")
        ci = self.t_test.confidence_interval()
        print(f"\t95% CI: {ci}")
        if ci[1] - ci[0] > 0.2 * self.t_test.mean:
            n = int(
                (
                    self.replications_factor
                    * (
                        self.t_test.t_val
                        * self.t_test.std_dev
                        / (0.1 * self.t_test.mean)
                    )
                    ** 2
                )
            )
            print(f"\tT-test failed, replications required: {n}")
        else:
            self.t_test.validate_model(self.epsilon)


def main(precision=0.1, replications_factor=1):
    extract_data = Extract_Data.Extract_Data()
    data_sets = [
        extract_data.get_servinsp1(),
        extract_data.get_servinsp22(),
        extract_data.get_servinsp23(),
        extract_data.get_ws1(),
        extract_data.get_ws2(),
        extract_data.get_ws3(),
    ]
    for i, data in enumerate(data_sets):
        print(f"Data set {i+1}:")
        data = data.drop(data.index[0])
        data = data.drop(data.index[0])
        data = data.astype(float)
        for col in data.columns:
            col_data = data[col]
            t_test = ColumnTTest(col_data, 0.1, replications_factor)
            t_test.validate_model()


if __name__ == "__main__":
    main()
