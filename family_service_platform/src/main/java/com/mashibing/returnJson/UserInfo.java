package com.mashibing.returnJson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Description: com.mashibing.returnJson
 */
public class UserInfo {
    private String name;
    private String avartar;
    private Permissions role;

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", avartar='" + avartar + '\'' +
                ", role=" + role +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public Permissions getRole() {
        return role;
    }

    public void setRole(Permissions role) {
        this.role = role;
    }

    public UserInfo(String name, Permissions role) {
        this.name = name;
        this.avartar = "/avartar2.jpg";
        this.role = role;
    }
}
