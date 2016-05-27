    package com.naresh.selenium.models;

import com.naresh.selenium.framework.Utils;

public class UserBuilder {
    public static User admin() {
        User user = new User("admin", "admin");
        user.setFname("Syed");
        user.setLname("naresh");
        return user;
    }

    public static User randomUser() {
        String uuid = Utils.getUUID();
        User user = new User("User_" + uuid, "admin");
        user.setFname("Ivan" + uuid);
        user.setLname("Petrov" + uuid);
        return user;
    }
}
