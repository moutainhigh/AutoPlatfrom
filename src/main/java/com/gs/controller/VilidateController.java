package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.User;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.VilidateService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xiao-Qiang on 2017/5/19.
 */
@Controller
@RequestMapping("vilidate")
public class VilidateController {

    private Logger logger = (Logger) LoggerFactory.getLogger(VilidateController.class);

    @Resource
    private VilidateService vilidateService;

    @ResponseBody
    @RequestMapping(value = "queryIsExist_userPhone", method = RequestMethod.GET)
    public String queryIsExistUserPhone(@Param("userPhone")String userPhone, @Param("editPhone") String editPhone) {
        try {
            logger.info("手机号验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPhone.equals(userPhone)) {
                int isExist = vilidateService.queryDataIsExistUserPhone(userPhone);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("手机号验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_userEmail", method = RequestMethod.GET)
    public String queryIsExistUserEmail(@Param("userEmail")String userEmail, @Param("editEmail") String editEmail) {
        try {
            logger.info("邮箱验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editEmail.equals(userEmail)) {
                int isExist = vilidateService.queryDataIsExistUserEmail(userEmail);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("邮箱验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_userIdentity", method = RequestMethod.GET)
    public String queryIsExistUserIdentity(@Param("userIdentity")String userIdentity, @Param("editIdentity") String editIdentity) {
        try {
            logger.info("身份证验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editIdentity.equals(userIdentity)) {
                int isExist = vilidateService.queryDataIsExistUserIdentity(userIdentity);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("身份证验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_roleName", method = RequestMethod.GET)
    public String queryIsExistRoleName(String roleName) {
        try {
            logger.info("角色英文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            int isExist = vilidateService.queryDataIsExistRoleName(roleName);
            if (isExist > 0) {
                result = false;
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("角色英文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_roleDes", method = RequestMethod.GET)
    public String queryIsExistRoleDes(@Param("roleDes") String roleDes, @Param("editDes") String editDes) {
        try {
            logger.info("角色中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editDes.equals(roleDes)) {
                int isExist = vilidateService.queryDataIsExistRoleDes(roleDes);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("角色中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_moduleName", method = RequestMethod.GET)
    public String queryIsExistModuleName(@Param("moduleName") String moduleName, @Param("editName") String editName) {
        try {
            logger.info("模块名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editName.equals(moduleName)) {
                int isExist = vilidateService.queryDataIsExistModuleName(moduleName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("模块名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_PZHN", method = RequestMethod.GET)
    public String queryIsExistPermissionZHName(@Param("permissionZHName") String permissionZHName, @Param("editZhName") String editZhName) {
        try {
            logger.info("权限中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editZhName.equals(permissionZHName)) {
                int isExist = vilidateService.queryDataIsExistPermissionZHName(permissionZHName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("权限中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_PN", method = RequestMethod.GET)
    public String queryIsExistPermissionName(@Param("permissionName") String permissionName, @Param("editPName") String editPName) {
        try {
            logger.info("权限名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPName.equals(permissionName)) {
                int isExist = vilidateService.queryDataIsExistPermissionName(permissionName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("权限名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_supplyName", method = RequestMethod.GET)
    public String queryIsExistSupplyName(@Param("supplyName") String supplyName, @Param("editSupplyName") String editSupplyName) {
        try {
            logger.info("供应商中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editSupplyName.equals(supplyName)) {
                int isExist = vilidateService.queryDataIsExistSupplyName(supplyName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("供应商中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_supplyTypeName", method = RequestMethod.GET)
    public String queryIsExistSupplyTypeName(@Param("supplyTypeName") String supplyTypeName, @Param("editSupplyTypeName") String editSupplyTypeName) {
        try {
            logger.info("供应商分类中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editSupplyTypeName.equals(supplyTypeName)) {
                int isExist = vilidateService.queryDataIsExistSupplyTypeName(supplyTypeName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("供应商分类中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_companyName", method = RequestMethod.GET)
    public String queryIsExistCompanyName(@Param("companyName") String companyName, @Param("editName") String editName) {
        try {
            logger.info("公司名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editName.equals(companyName)) {
                int isExist = vilidateService.queryDataIsExistCompanyName(companyName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("公司名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_companyTel", method = RequestMethod.GET)
    public String queryIsExistCompany(@Param("companyTel") String companyTel, @Param("editCompanyTel") String editCompanyTel) {
        try {
            logger.info("公司电话验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editCompanyTel.equals(companyTel)) {
                int isExist = vilidateService.queryDataIsExistCompanyTel(companyTel);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("公司电话验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_companyPP", method = RequestMethod.GET)
    public String queryIsExistCompanyPP(@Param("companyPricipalPhone")String companyPricipalPhone, @Param("editPhone") String editPhone) {
        try {
            logger.info("公司负责人手机号验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPhone.equals(companyPricipalPhone)) {
                int isExist = vilidateService.queryDataIsExistUserPhone(companyPricipalPhone);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("公司负责人手机号验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_companyWebsite", method = RequestMethod.GET)
    public String queryIsExistCompanyWebsite(@Param("companyWebsite") String companyWebsite, @Param("editWebsite") String editWebsite) {
        try {
            logger.info("公司官网验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editWebsite.equals(companyWebsite)) {
                int isExist = vilidateService.queryDataIsExistCompanyWebsite(companyWebsite);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("公司官网验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_ColorName", method = RequestMethod.GET)
    public String queryIsExistColorName(@Param("colorName") String colorName, @Param("editColorName") String editColorName) {
        try {
            logger.info("颜色名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editColorName.equals(colorName)) {
                int isExist = vilidateService.queryDataIsExistColorName(colorName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("颜色名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_BrandName", method = RequestMethod.GET)
    public String queryIsExistBrandName(@Param("brandName") String brandName, @Param("editBrandName") String editBrandName) {
        try {
            logger.info("品牌名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editBrandName.equals(brandName)) {
                int isExist = vilidateService.queryDataIsExistBrandName(brandName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("品牌名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_PlateName", method = RequestMethod.GET)
    public String queryIsExistPlateName(@Param("plateName") String plateName, @Param("editPlateName") String editPlateName) {
        try {
            logger.info("车牌名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPlateName.equals(plateName)) {
                int isExist = vilidateService.queryDataIsExistPlateName(plateName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("车牌名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_ModelName", method = RequestMethod.GET)
    public String queryIsExistModelName(@Param("modelName") String modelName, @Param("editModelName") String editModelName) {
        try {
            logger.info("车型名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editModelName.equals(modelName)) {
                int isExist = vilidateService.queryDataIsExistModelName(modelName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("车型名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_accTypeName", method = RequestMethod.GET)
    public String queryIsExistAccTypeName(@Param("accTypeName") String accTypeName, @Param("editTypeName") String editTypeName) {
        try {
            logger.info("配件分类名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editTypeName.equals(accTypeName)) {
                int isExist = vilidateService.queryDataIsExistAccTypeName(accTypeName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("配件分类名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_accName", method = RequestMethod.GET)
    public String queryIsExistAccName(@Param("accName") String accName, @Param("editName") String editName) {
        try {
            logger.info("配件名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editName.equals(accName)) {
                int isExist = vilidateService.queryDataIsExistAccTypeName(accName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("配件名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_inTypeName", method = RequestMethod.GET)
    public String queryIsExistInTypeName(@Param("inTypeName") String inTypeName, @Param("editTypeName") String editTypeName) {
        try {
            logger.info("收入类型名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editTypeName.equals(inTypeName)) {
                int isExist = vilidateService.queryDataIsExistInTypeName(inTypeName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("收入类型名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_outTypeName", method = RequestMethod.GET)
    public String queryIsExistOutTypeName(@Param("outTypeName") String outTypeName, @Param("editTypeName") String editTypeName) {
        try {
            logger.info("支出类型名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editTypeName.equals(outTypeName)) {
                int isExist = vilidateService.queryDataIsExistOuTypeName(outTypeName, SessionGetUtil.getUser());
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("支出类型名称验证失败，出现了异常");
            return null;
        }
    }
}
