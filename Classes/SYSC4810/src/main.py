from src.enrolment.enrolment import enrol_user, user_login
from src.authentication.database import create_database
from src.access_control.control import can_perform_action


def main():
    create_database()

    while True:
        print("\nFinvest Holdings")
        print("Client Holdings and Information System")
        print("---------------------------------------------------")
        print("1. Sign Up")
        print("2. Sign In")
        print("3. Exit")
        choice = input("Choose an option: ")

        if choice == "1":
            enrol_user("finvest.db")
        elif choice == "2":
            user = user_login("finvest.db")
            if user:
                print(f"\nUser Information:")
                print(f"User ID: {user.name}")
                print(f"Role: {user.role.name}")
                print(f"Permissions: {', '.join(user.role.permissions)}")

                while True:
                    print("\nOptions:")
                    print("1. Logout")
                    print("2. Exit")
                    action_choice = input("Choose an action: ")
                    if action_choice == "1":
                        break
                    elif action_choice == "2":
                        exit()
                    else:
                        print("Invalid option.")
            else:
                print("Invalid login credentials.")
        elif choice == "3":
            break
        else:
            print("Invalid choice. Please try again.")


if __name__ == "__main__":
    main()
