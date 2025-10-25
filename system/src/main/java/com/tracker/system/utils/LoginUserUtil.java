package com.tracker.system.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.tracker.system.domain.dto.auth.LoginUser;
import com.tracker.system.models.entity.UserDO;

/**
 * Sa-Token 当前登录用户工具类
 */
public class LoginUserUtil {

    /**
     * 获取当前登录的用户对象
     *
     * @return 当前用户信息（未登录返回 null）
     */
    public static UserDO getCurrentUser() {
        if (!StpUtil.isLogin()) {
            return null;
        }
        SaSession session = StpUtil.getSession();
        return (UserDO) session.get("user");
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return 用户ID（未登录返回 null）
     */
    public static Long getCurrentUserId() {
        if (!StpUtil.isLogin()) {
            return null;
        }
        return StpUtil.getLoginIdAsLong();
    }

    /**
     * 判断是否已登录
     */
    public static boolean isLogin() {
        return StpUtil.isLogin();
    }


    /**
     * 更新当前Session中的用户信息
     */
    public static void setCurrentUser(LoginUser user) {
        if (StpUtil.isLogin()) {
            StpUtil.getSession().set("user", user);
        }
    }

    public static void removeCurrentUser() {
        StpUtil.getSession().delete("user");
    }
}
