package com.gs.service;

import com.gs.bean.User;

/**
 * Created by Xiao-Qiang on 2017/5/18.
 */
public interface VilidateService {

    /*公司表名称验证唯一**/
     int queryDataIsExistCompanyName(String companyName);

    /*公司表电话验证唯一**/
     int queryDataIsExistCompanyTel(String companyTel);

    /*公司表官网验证唯一**/
     int queryDataIsExistCompanyWebsite(String companyWebsite);

    /**用户表邮箱验证唯一*/
     int queryDataIsExistUserEmail(String userEmail);

    /**用户表手机验证唯一*/
     int queryDataIsExistUserPhone(String userPhone);

    /**用户表身份证验证唯一*/
     int queryDataIsExistUserIdentity(String userIdentity);

    /**角色表英文名称验证唯一*/
     int queryDataIsExistRoleName(String roleName);

    /**角色表中文名称验证唯一*/
     int queryDataIsExistRoleDes(String roleDes);

    /**模块表名称验证唯一*/
     int queryDataIsExistModuleName(String moduleName);

    /**权限表名称验证唯一*/
     int queryDataIsExistPermissionName(String permissionName);

    /**权限表中文名称验证唯一*/
     int queryDataIsExistPermissionZHName(String permissionZHName);

    /**品牌表名称验证唯一*/
     int queryDataIsExistBrandName(String brandName);

    /**车型表名称验证唯一*/
     int queryDataIsExistModelName(String modelName);

    /**顔色表名称验证唯一*/
     int queryDataIsExistColorName(String colorName);

    /**车牌表名称验证唯一*/
     int queryDataIsExistPlateName(String plateName);

    /**项目表名称验证唯一*/
     int queryDataIsExistFixName(String maintainName, User user);

    /**供应商分类表名称验证唯一*/
     int queryDataIsExistSupplyTypeName(String supplyTypeName, User user);

    /**供应商表名称验证唯一*/
     int queryDataIsExistSupplyName(String supplyName, User user);

    /**配件分类表名称验证唯一*/
     int queryDataIsExistAccTypeName(String accTypeName, User user);

    /**配件表名称验证唯一*/
     int queryDataIsExistAccName(String accName, User user);

    /**收入类型表名称验证唯一*/
     int queryDataIsExistInTypeName(String inTypeName, User user);

    /**支出类型表名称验证唯一*/
     int queryDataIsExistOuTypeName(String ouTypeName, User user);
}
