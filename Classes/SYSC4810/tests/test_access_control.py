import os
import sqlite3
import unittest

import bcrypt

from src.access_control.control import can_perform_action, User
from src.authentication.database import create_database, add_user, get_user
from src.authentication.login import authenticate_user


class TestAccessControl(unittest.TestCase):
    test_db_path = "test_finvest.db"

    @classmethod
    def setUpClass(cls):
        # print(f"Creating test database at: {cls.test_db_path}")
        create_database(cls.test_db_path)
        add_user(cls.test_db_path, "Mischa Lowery", "Password123!", "Regular Client")
        add_user(cls.test_db_path, "Veronica Perez", "Password123!", "Regular Client")
        add_user(cls.test_db_path, "Willow Garza", "Password123!", "Premium Client")
        add_user(cls.test_db_path, "Nala Preston", "Password123!", "Premium Client")
        add_user(cls.test_db_path, "Winston Callahan", "Password123!", "Teller")
        add_user(cls.test_db_path, "Kelan Gough", "Password123!", "Teller")
        add_user(cls.test_db_path, "Nelson Wilkins", "Password123!", "Financial Advisor")
        add_user(cls.test_db_path, "Kelsie Chang", "Password123!", "Financial Advisor")
        add_user(cls.test_db_path, "Howard Linkler", "Password123!", "Compliance Officer")
        add_user(cls.test_db_path, "Stefania Smart", "Password123!", "Compliance Officer")
        add_user(cls.test_db_path, "Kodi Matthews", "Password123!", "Financial Planner")
        add_user(cls.test_db_path, "Malikah Wu", "Password123!", "Financial Planner")
        add_user(cls.test_db_path, "Stacy Kent", "Password123!", "Investment Analyst")
        add_user(cls.test_db_path, "Keikilana Kapahu", "Password123!", "Investment Analyst")
        add_user(cls.test_db_path, "Caroline Lopez", "Password123!", "Technical Support")
        add_user(cls.test_db_path, "Pawel Barclay", "Password123!", "Technical Support")

    @classmethod
    def tearDownClass(cls):
        os.remove("test_finvest.db")

    # Database-related tests
    def test_add_and_get_user(self):
        username = "testuser"
        password = "testpassword"
        role = "Regular Client"
        add_user(self.test_db_path, username, password, role)
        user = get_user(self.test_db_path, username)
        self.assertIsNotNone(user)
        self.assertEqual(user[0], username)
        # self.assertTrue(bcrypt.checkpw(password.encode(), user[1].encode()))
        self.assertEqual(user[3], role)

    # Authentication and access control tests
    def test_user_authentication(self):
        user = authenticate_user("Nala Preston", "Password123!", self.test_db_path)
        self.assertIsNotNone(user)

    def test_invalid_authentication(self):
        user = authenticate_user("Nala Preston", "wrong_password", self.test_db_path)
        self.assertIsNone(user)

    # test each role 
    def test_regular_client_permissions(self):
        user = authenticate_user("Mischa Lowery", "Password123!", self.test_db_path)
        self.assertTrue(can_perform_action(user, "view_account_balance", "account_balance"))
        self.assertFalse(can_perform_action(user, "modify_investment_portfolio", "investments_portfolio"))

    def test_premium_client_permissions(self):
        user = authenticate_user("Willow Garza", "Password123!", self.test_db_path)
        self.assertTrue(can_perform_action(user, "view_investments_portfolio", "investments_portfolio"))
        self.assertTrue(can_perform_action(user, "modify_investment_portfolio", "investments_portfolio"))

    def test_teller_permissions(self):
        user = authenticate_user("Winston Callahan", "Password123!", self.test_db_path)
        self.assertTrue(can_perform_action(user, "view_client_balance", "account_balance"))
        self.assertFalse(can_perform_action(user, "modify_investment_portfolio", "investments_portfolio"))

    # test role fail
    def test_unauthorized_access(self):
        user = authenticate_user("UnauthorizedUser", "Password123!", self.test_db_path)
        self.assertIsNone(user)

    def test_invalid_role(self):
        with self.assertRaises(ValueError):
            User("InvalidRoleUser", "NonExistingRole")


if __name__ == "__main__":
    unittest.main()
