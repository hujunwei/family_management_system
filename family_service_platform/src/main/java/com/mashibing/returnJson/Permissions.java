package com.mashibing.returnJson;

import java.util.List;

/**
 * @Description: com.mashibing.returnJson
 */
public class Permissions {
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Permissions{" +
                "permissions=" + permissions +
                '}';
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Permissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
