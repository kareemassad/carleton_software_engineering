import re
import os


def load_common_passwords(file_path):
    if not os.path.exists(file_path):
        return []
    with open(file_path, 'r') as file:
        return [line.strip() for line in file.readlines()]


def is_password_valid(password, username, file_path='common_passwords.txt'):
    common_passwords = load_common_passwords(file_path)

    # Checks for length, complexity, etc.
    if len(password) < 8:
        return False
    if not re.search("[a-z]", password):
        return False
    if not re.search("[A-Z]", password):
        return False
    if not re.search("[0-9]", password):
        return False
    if not re.search("[!@#$%?*]", password):
        return False
    if password in common_passwords:
        return False
    # to account for dates ##/##/##
    if re.match(".*[0-9]{2}/[0-9]{2}/[0-9]{4}.*", password):
        return False
    # for postal code A#A #A#
    if re.match(".*[A-Z][0-9][A-Z] [0-9][A-Z][0-9].*", password):
        return False
    # for phone number (###) ###-####
    if re.match(".*\([0-9]{3}\) [0-9]{3}-[0-9]{4}.*", password):
        return False
    # for license plate ###-### or #######
    if re.match(".*[0-9]{3}-[0-9]{3}.*", password):
        return False
    if username in password:
        return False

    return True
