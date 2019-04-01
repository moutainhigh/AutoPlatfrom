package com.gs.common.util;

import com.gs.common.Constants;
import org.apache.shiro.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-11.
 */
public class CheckRoleUtil {

    /**
     * 根据传递进来的角色名称判断当前登入的用户是否是该角色
     * @param roleName 角色名称，多个角色名称用“，”隔开
     * @return
     */
    public static boolean checkRoles(String roleName) {
        String[] rolesNames = roleName.split(",");
        List<String> roles = new ArrayList<String>();
        for (int i = 0, len = rolesNames.length; i < len; i++) {
            roles.add(rolesNames[i]);
        }
        boolean[] isRoles = SecurityUtils.getSubject().hasRoles(roles);
        for (int j = 0, len = isRoles.length; j < len; j++) {
            if (isRoles[j]) {
                return true;
            }
        }
       return false;
    }
}
