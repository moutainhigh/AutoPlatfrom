package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.dao.VilidateDAO;
import com.gs.service.VilidateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Xiao-Qiang on 2017/5/18.
 */
@Service
public class VilidateServiceImpl implements VilidateService {

    @Resource
    private VilidateDAO vilidateDAO;

    @Override
    public int queryDataIsExistCompanyName(String companyName) {
        return vilidateDAO.queryDataIsExistCompanyName(companyName);
    }

    @Override
    public int queryDataIsExistCompanyTel(String companyTel) {
        return vilidateDAO.queryDataIsExistCompanyTel(companyTel);
    }

    @Override
    public int queryDataIsExistCompanyWebsite(String companyWebsite) {
        return vilidateDAO.queryDataIsExistCompanyWebsite(companyWebsite);
    }

    @Override
    public int queryDataIsExistUserEmail(String userEmail) {
        return vilidateDAO.queryDataIsExistUserEmail(userEmail);
    }

    @Override
    public int queryDataIsExistUserPhone(String userPhone) {
        return vilidateDAO.queryDataIsExistUserPhone(userPhone);
    }

    @Override
    public int queryDataIsExistUserIdentity(String userIdentity) {
        return vilidateDAO.queryDataIsExistUserIdentity(userIdentity);
    }

    @Override
    public int queryDataIsExistRoleName(String roleName) {
        return vilidateDAO.queryDataIsExistRoleName(roleName);
    }

    @Override
    public int queryDataIsExistRoleDes(String roleDes) {
        return vilidateDAO.queryDataIsExistRoleDes(roleDes);
    }

    @Override
    public int queryDataIsExistModuleName(String moduleName) {
        return vilidateDAO.queryDataIsExistModuleName(moduleName);
    }

    @Override
    public int queryDataIsExistPermissionName(String permissionName) {
        return vilidateDAO.queryDataIsExistPermissionName(permissionName);
    }

    @Override
    public int queryDataIsExistPermissionZHName(String permissionZHName) {
        return vilidateDAO.queryDataIsExistPermissionZHName(permissionZHName);
    }

    @Override
    public int queryDataIsExistBrandName(String brandName) {
        return vilidateDAO.queryDataIsExistBrandName(brandName);
    }

    @Override
    public int queryDataIsExistModelName(String modelName) {
        return vilidateDAO.queryDataIsExistModelName(modelName);
    }

    @Override
    public int queryDataIsExistColorName(String colorName) {
        return vilidateDAO.queryDataIsExistColorName(colorName);
    }

    @Override
    public int queryDataIsExistPlateName(String plateName) {
        return vilidateDAO.queryDataIsExistPlateName(plateName);
    }

    @Override
    public int queryDataIsExistFixName(String maintainName, User user) {
        return vilidateDAO.queryDataIsExistFixName(maintainName, user);
    }

    @Override
    public int queryDataIsExistSupplyTypeName(String supplyTypeName, User user) {
        return vilidateDAO.queryDataIsExistSupplyTypeName(supplyTypeName, user);
    }

    @Override
    public int queryDataIsExistSupplyName(String supplyName, User user) {
        return vilidateDAO.queryDataIsExistSupplyName(supplyName, user);
    }

    @Override
    public int queryDataIsExistAccTypeName(String accTypeName, User user) {
        return vilidateDAO.queryDataIsExistAccTypeName(accTypeName, user);
    }

    @Override
    public int queryDataIsExistAccName(String accName, User user) {
        return vilidateDAO.queryDataIsExistAccName(accName, user);
    }

    @Override
    public int queryDataIsExistInTypeName(String inTypeName, User user) {
        return vilidateDAO.queryDataIsExistInTypeName(inTypeName, user);
    }

    @Override
    public int queryDataIsExistOuTypeName(String ouTypeName, User user) {
        return vilidateDAO.queryDataIsExistOuTypeName(ouTypeName, user);
    }
}
