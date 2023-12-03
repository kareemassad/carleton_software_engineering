from access_control.access_control import can_perform_action
from access_control.login import authenticate_user
from access_control.database import create_database

def main():
    create_database()
    user = authenticate_user("Alice", "password123")
    if user:
        action = "view_account"
        if can_perform_action(user, action):
            print(f"Access granted to {action}")
        else:
            print(f"Access denied to {action}")

if __name__ == "__main__":
    main()