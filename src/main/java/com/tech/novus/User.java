package com.tech.novus;

import java.util.ArrayList;

public class User {
    public String username;
    public String password;
    public String email;
    public ArrayList<String> groups;
    public boolean admin;
    public boolean profile_updatable;
    public boolean internal_password_disabled;
    public boolean disable_ui_access;

    public User(boolean disable_ui_access, boolean internal_password_disabled, boolean profile_updatable, boolean admin, ArrayList<String> groups, String email, String password, String username) {
        this.disable_ui_access = disable_ui_access;
        this.internal_password_disabled = internal_password_disabled;
        this.profile_updatable = profile_updatable;
        this.admin = admin;
        this.groups = groups;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isProfile_updatable() {
        return profile_updatable;
    }

    public void setProfile_updatable(boolean profile_updatable) {
        this.profile_updatable = profile_updatable;
    }

    public boolean isInternal_password_disabled() {
        return internal_password_disabled;
    }

    public void setInternal_password_disabled(boolean internal_password_disabled) {
        this.internal_password_disabled = internal_password_disabled;
    }

    public boolean isDisable_ui_access() {
        return disable_ui_access;
    }

    public void setDisable_ui_access(boolean disable_ui_access) {
        this.disable_ui_access = disable_ui_access;
    }

    public void populateFields(){
        this.username = "JavaUser";
        this.password = "Cookies1234!!";
        this.email = "mail@gmail.com";
        this.groups = new ArrayList<>();
        this.groups.add("Tester");
        this.admin = false;
        this.profile_updatable = false;
        this.internal_password_disabled = false;
        this.disable_ui_access = false;
    }
}
