package com.gs.bean;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:21
 */
public class Permission {
    private String permissionId;
    private String permissionName;
    private String permissionZHName;
    private String permissionDes;
    private String moduleId;
    private String permissionStatus;
    private Module module;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getPermissionId() {
        return this.permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionZHName() {
        return this.permissionZHName;
    }

    public void setPermissionZHName(String permissionZHName) {
        this.permissionZHName = permissionZHName;
    }

    public String getPermissionDes() {
        return this.permissionDes;
    }

    public void setPermissionDes(String permissionDes) {
        this.permissionDes = permissionDes;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getPermissionStatus() {
        return this.permissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

}