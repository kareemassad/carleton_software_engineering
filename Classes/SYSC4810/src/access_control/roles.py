import pandas as pd

from tabulate import tabulate


class Role:
    def __init__(self, name, permissions):
        self.name = name
        self.permissions = permissions


roles: dict[str, Role] = {
    "Regular Client": Role(
        "Regular Client",
        [
            "view_account_balance",
            "view_investments_portfolio",
            "view_financial_advisor_contact",
        ],
    ),
    "Premium Client": Role(
        "Premium Client",
        [
            "view_account_balance",
            "view_investments_portfolio",
            "modify_investment_portfolio",
            "view_financial_advisor_contact",
            "view_investment_analyst_contact",
        ],
    ),
    "Teller": Role(
        "Teller",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "access_system_during_business_hours",
        ],
    ),
    "Financial Advisor": Role(
        "Financial Advisor",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "modify_client_investment_portfolio",
            "view_private_consumer_instruments",
        ],
    ),
    "Compliance Officer": Role(
        "Compliance Officer",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "validate_investment_portfolio_modifications",
        ],
    ),
    "Financial Planner": Role(
        "Financial Planner",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "modify_client_investment_portfolio",
            "view_money_market_instruments",
            "view_private_consumer_instruments",
        ],
    ),
    "Investment Analyst": Role(
        "Investment Analyst",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "view_derivatives_trading",
            "modify_client_investment_portfolio",
            "view_money_market_instruments",
            "view_interest_instruments",
            "view_private_consumer_instruments",
        ],
    ),
    "Technical Support": Role(
        "Technical Support",
        [
            "view_client_balance",
            "view_client_investment_portfolio",
            "view_client_information",
            "request_client_account_access",
        ],
    ),
}

acl = {
    "account_balance": {
        "Regular Client": ["view_account_balance"],
        "Premium Client": ["view_account_balance"],
        "Teller": ["view_client_balance"],
        "Financial Advisor": ["view_client_balance"],
        "Compliance Officer": ["view_client_balance"],
        "Financial Planner": ["view_client_balance"],
        "Investment Analyst": ["view_client_balance"],
        "Technical Support": ["view_client_balance"],
    },
    "investments_portfolio": {
        "Regular Client": ["view_investments_portfolio"],
        "Premium Client": ["view_investments_portfolio", "modify_investment_portfolio"],
        "Teller": ["view_client_investment_portfolio"],
        "Financial Advisor": ["view_client_investment_portfolio", "modify_client_investment_portfolio"],
        "Compliance Officer": ["view_client_investment_portfolio"],
        "Financial Planner": ["view_client_investment_portfolio", "modify_client_investment_portfolio"],
        "Investment Analyst": ["view_client_investment_portfolio", "modify_client_investment_portfolio"],
        "Technical Support": ["view_client_investment_portfolio"],
    },
    "financial_advisor_contact": {
        "Regular Client": ["view_financial_advisor_contact"],
        "Premium Client": ["view_financial_advisor_contact"],
        "Technical Support": ["view_client_information"],
    },
    "investment_analyst_contact": {
        "Premium Client": ["view_investment_analyst_contact"],
        "Technical Support": ["view_client_information"],
    },
    "private_consumer_instruments": {
        "Financial Advisor": ["view_private_consumer_instruments"],
        "Financial Planner": ["view_private_consumer_instruments"],
        "Investment Analyst": ["view_private_consumer_instruments"],
    },
    "money_market_instruments": {
        "Financial Planner": ["view_money_market_instruments"],
        "Investment Analyst": ["view_money_market_instruments"],
    },
    "interest_instruments": {
        "Investment Analyst": ["view_interest_instruments"],
    },
    "derivatives_trading": {
        "Investment Analyst": ["view_derivatives_trading"],
    },
    "access_system_during_business_hours": {
        "Teller": ["access_system_during_business_hours"],
    },
    "validate_investment_portfolio_modifications": {
        "Compliance Officer": ["validate_investment_portfolio_modifications"],
    },
    "request_client_account_access": {
        "Technical Support": ["request_client_account_access"],
    },
    "view_client_information": {
        "Technical Support": ["view_client_information"],
    },

}


def create_access_matrix_df(roles, acl):
    resources = sorted(acl.keys())
    data = []

    for role_name, role in roles.items():
        permissions_row = [', '.join(acl[resource].get(role_name, [])) for resource in resources]
        data.append([role_name] + permissions_row)

    df = pd.DataFrame(data, columns=["Roles/Resources"] + resources)
    return df


# Generate the DataFrame
matrix_df = create_access_matrix_df(roles, acl)

# Convert the DataFrame to markdown table format
markdown_table = tabulate(matrix_df, headers='keys', tablefmt='pipe', showindex=False)

# save the markdown table
with open("access_matrix.md", "w") as f:
    f.write(markdown_table)
