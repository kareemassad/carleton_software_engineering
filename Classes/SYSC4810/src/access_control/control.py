from access_control.role import roles

class User:
    def __init__(self, name, role_name):
        self.name = name
        self.role = roles[role_name]
        
    def has_permission(self, permission):
        return permission in self.role.permissions

def can_perform_action(user, action):
    return user.has_permission(action)