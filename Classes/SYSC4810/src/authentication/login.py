import bcrypt
from ..authentication.database import get_user_credentials
from ..access_control.access_control import User

def authenticate_user(username, password):
    credentials = get_user_credentials(username)
    if credentials and bcrypt.checkpw(password.encode(), credentials['password'].encode()):
        return User(username, credentials['role'])
    else:
        return None
