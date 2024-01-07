package com.padr.gys.infra.inbound.common.context;

import com.padr.gys.domain.user.entity.User;

public class UserContext {
    
    private static ThreadLocal<User> threadLocalUser = new ThreadLocal<>();
    private static ThreadLocal<Boolean> threadLocalIsStaff = new ThreadLocal<>();

    public static void setUser(User user) {
        threadLocalUser.set(user);
    }

    public static User getUser() {
        return threadLocalUser.get();
    }

    public static void setIsStaff(Boolean isStaff) {
        threadLocalIsStaff.set(isStaff);
    }

    public static Boolean getIsStaff() {
        return threadLocalIsStaff.get();
    }

    public static void removeUser() {
        threadLocalUser.remove();
    }

    public static void removeIsStaff() {
        threadLocalIsStaff.remove();
    }
}
