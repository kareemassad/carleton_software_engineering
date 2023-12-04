# test_password_checker.py
import unittest
from src.authentication.password_checker import is_password_valid


class TestPasswordChecker(unittest.TestCase):
    def test_valid_password(self):
        self.assertTrue(is_password_valid("Soccer123!", "user"))

    def test_password_length_short(self):
        self.assertFalse(is_password_valid("Short1!", "user"))

    def test_no_number(self):
        self.assertFalse(is_password_valid("NoNumber!", "user"))

    def test_user_id_match(self):
        self.assertFalse(is_password_valid("user123!", "user"))

    def test_no_special_char(self):
        self.assertFalse(is_password_valid("NoSpecial0", "user"))

    def test_no_uppercase(self):
        self.assertFalse(is_password_valid("nouppercase1!", "user"))

    def test_no_lowercase(self):
        self.assertFalse(is_password_valid("NOLOWERCASE1!", "user"))

    def test_common_password(self):
        self.assertFalse(is_password_valid("password123!", "user"))

    def test_date_format_password(self):
        self.assertFalse(is_password_valid("01/01/2000", "user"))


if __name__ == "__main__":
    unittest.main()
