from src.access_control.roles import roles, acl


class User:
    def __init__(self, name, role_name):
        self.name = name
        if role_name in roles:
            self.role = roles[role_name]
        else:
            raise ValueError(f"Role '{role_name}' does not exist")

    def has_permission(self, permission):
        return permission in self.role.permissions


def can_perform_action(user, action, resource):
    if user is None or user.role.name not in acl.get(resource, {}):
        return False
    allowed_actions = acl[resource][user.role.name]
    return action in allowed_actions
