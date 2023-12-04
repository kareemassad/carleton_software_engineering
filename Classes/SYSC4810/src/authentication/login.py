import bcrypt
from src.access_control.control import User
from src.authentication.database import get_user_credentials


def authenticate_user(username, password, db_path="finvest.db"):
    credentials = get_user_credentials(username, db_path)
    if credentials:
        stored_hashed_password = credentials['password'].encode()

        if bcrypt.checkpw(password.encode(), stored_hashed_password):
            return User(username, credentials['role'])
    return None
