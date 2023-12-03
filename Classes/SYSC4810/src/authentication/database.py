import bcrypt
import sqlite3


def create_database():
    conn = sqlite3.connect('finvest.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS users (username text, password text, role text)''')
    conn.commit()
    add_user(conn, 'Alice', 'password123', 'Regular Client')
    conn.close()
    
def add_user(conn, username, password, role):
    hashed = bcrypt.hashpw(password.encode(), bcrypt.gensalt())
    c = conn.cursor()
    c.execute("INSERT INTO users VALUES (?, ?, ?)", (username, hashed, role))
    conn.commit()

def get_user_credentials(username):
    conn = sqlite3.connect('finvest.db')
    c = conn.cursor()
    c.execute("SELECT password, role FROM users WHERE username=?", (username,))
    credentials = c.fetchone()
    conn.close()
    return {'password': credentials[0], 'role': credentials[1]} if credentials else None
