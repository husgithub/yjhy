package com.yjhy.util;

import com.yjhy.entity.SysAccount;
import org.springframework.stereotype.Component;


@Component
public class SessionUtil {

    private static final ThreadLocal<SysAccount> ADMIN_INFO = new ThreadLocal<>();

    public static void setLoginAdmin(SysAccount admin) {
        ADMIN_INFO.set(admin);
    }

    public static SysAccount getAdmin() {
        return ADMIN_INFO.get();
    }

    public static String getAdminName() {
        SysAccount admin = getAdmin();
        return admin == null ? "" : admin.getAccountName();
    }
}
