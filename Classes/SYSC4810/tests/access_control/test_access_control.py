import unittest
import sys
import os
sys.path.append(os.path.abspath("../../src"))
from authentication.login import authenticate_user
from access_control.access_control import can_perform_action

class TestAccessControl(unittest.TestCase):
    def test_user_authentication(self):
        user = authenticate_user("Alice", "password123")
        self.assertIsNotNone(user)
        
    def test_invalid_authentication(self):
        user = authenticate_user("Alice", "wrong_password")
        self.assertIsNone(user)
        
    def test_access_control(self):
        user = authenticate_user("Alice", "password123")
        self.assertTrue(can_perform_action(user, "view_account"))
        self.assertFalse(can_perform_action(user, "modify_investment"))
        
if __name__ == "__main__":
    unittest.main()