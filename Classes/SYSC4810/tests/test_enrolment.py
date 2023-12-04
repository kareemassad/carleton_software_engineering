import unittest
from unittest.mock import patch

from src.authentication.database import create_database, get_user, add_user
from src.enrolment.enrolment import enrol_user, user_login


class TestEnrolment(unittest.TestCase):
    db_path = "test_finvest.db"

    @classmethod
    def setUpClass(cls):
        create_database(cls.db_path)
        username = "testuser"
        password = "ValidPassword1!"
        role = "Regular Client"
        if not get_user(cls.db_path, username):
            add_user(cls.db_path, username, password, role)

    @patch("builtins.input", side_effect=["newtestuser", "ValidPassword1!", "Regular Client"])
    @patch("builtins.print")
    def test_enrol_user(self, mock_print, mock_input):
        enrol_user(self.db_path)
        user = get_user(self.db_path, "newtestuser")
        self.assertIsNotNone(user)
        self.assertEqual(user[0], "newtestuser")

    @patch('builtins.input', side_effect=["testuser", "ValidPassword1!"])
    @patch('builtins.print')
    def test_user_login_success(self, mock_print, mock_input):
        user = user_login(self.db_path)
        print(f"Login user: {user}")
        user = get_user(self.db_path, "testuser")
        self.assertIsNotNone(self.db_path)
        self.assertEqual(user[0], "testuser")

    @patch('builtins.input', side_effect=['testuser', 'WrongPassword'])
    @patch('builtins.print')
    def test_user_login_failure(self, mock_print, mock_input):
        user = user_login(self.db_path)
        self.assertIsNone(user)


if __name__ == '__main__':
    unittest.main()
