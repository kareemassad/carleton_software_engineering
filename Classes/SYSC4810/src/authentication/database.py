import bcrypt
import sqlite3


def create_database(db_path="finvest.db"):
    print(f"db path: {db_path}")
    conn = sqlite3.connect(db_path)
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS users (
                    username text PRIMARY KEY, 
                    hashed_password BLOB NOT NULL, 
                    salt TEXT NOT NULL,
                    role text NOT NULL
                )
            ''')
    conn.commit()
    conn.close()


def add_user(db_path, username, password, role):
    conn = sqlite3.connect(db_path)
    c = conn.cursor()

    # Check if the user already exists
    c.execute("SELECT * FROM users WHERE username = ?", (username,))
    if c.fetchone():
        print(f"User '{username}' already exists. Skipping.")
        pass
    else:
        salt = bcrypt.gensalt()
        hashed_password = bcrypt.hashpw(password.encode(), salt)
        c.execute('''
            INSERT INTO users (username, hashed_password, salt, role) 
            VALUES (?, ?, ?, ?)
        ''', (username, hashed_password.decode(), salt.decode(), role))
        conn.commit()

    conn.close()


def get_user(db_path, username):
    conn = sqlite3.connect(db_path)
    c = conn.cursor()
    c.execute('SELECT username, hashed_password, salt, role FROM users WHERE username = ?', (username,))
    user = c.fetchone()
    conn.close()
    return user


def verify_password(db_path, username, password):
    user = get_user(db_path, username)
    if user:
        _, stored_hash, salt, _ = user
        return bcrypt.checkpw(password.encode(), stored_hash.encode())
    return False


def get_user_credentials(username, db_path="finvest.db"):
    conn = sqlite3.connect(db_path)
    c = conn.cursor()
    c.execute("SELECT hashed_password, role FROM users WHERE username=?", (username,))
    credentials = c.fetchone()
    conn.close()
    return {'password': credentials[0], 'role': credentials[1]} if credentials else None

