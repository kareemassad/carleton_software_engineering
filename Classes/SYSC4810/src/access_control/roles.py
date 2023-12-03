class Role:
    def __init__(self, name, permissions):
        self.name = name
        self.permissions = permissions


roles = {
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
