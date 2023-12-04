# enrolment.py
from src.access_control.roles import roles
from src.authentication.database import add_user, verify_password
from src.authentication.login import authenticate_user
from src.authentication.password_checker import is_password_valid


def enrol_user(db_path):
    print("Enrolment Process")
    username = input("Enter username: ")
    while True:
        password = input("Enter password: ")
        if is_password_valid(password, username):
            break
        print("Invalid password. Please try again.")
    while True:
        role = input("Enter role: ")
        if role in roles:
            break
        print("Invalid role. Please enter one of the following roles: ")
        print("------------------------------------")
        for role_name in roles.keys():
            print(role_name)
        print("------------------------------------")

    add_user(db_path, username, password, role)
    print("USER ENROLMENT SUCCESSFUL.")


def user_login(db_path):
    print("Login Process")
    username = input("Enter username: ")
    password = input("Enter password: ")
    if verify_password(db_path, username, password):
        print("ACCESS GRANTED")
        return authenticate_user(username, password, db_path)
    else:
        print("ACCESS DENIED")
        return None
