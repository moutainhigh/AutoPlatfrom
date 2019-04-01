package com.gs.dao;

import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface UserDAO extends BaseDAO<String, User>{

    public List<User> queryByUser(@Param("pager")Pager pager, @Param("companyId")String companyId);

    public int countByUser(String companyId);

    /*查询所有管理员 */
    public List<User> queryByAdminPager(Pager pager);

    /*统计所有管理员的个数*/
    public int  countAdmin();

    /*查询汽修公司管理员 */
    public List<User> queryByCompanyAdminPager(Pager pager);

    /*统计汽修公司管理员的个数*/
    public int  countCompanyAdmin();

    /*查询系统管理员 */
    public List<User> queryBySystemAdminPager(Pager pager);

    /*统计系统管理员的个数*/
    public int  countSystemAdmin();

    /*分页查询所有车主个数*/
    public int countByCustomer();

    /*分页查询所有车主*/
    public List<User> queryCustomerPager(@Param("pager")Pager pager);

    /*分页查询所有员工*/
    public List<User> queryPeoplePager(@Param("pager") Pager pager, @Param("user")User user);

    /*登陆*/
    public User queryLogin(User user);


    /**添加管理员*/
    public void insertAdmin(User user);

    /*根据手机号查询id*/
    public User queryByPhone(String phone);

    /**查询可用员工个数*/
    public int countPeopleEmp(User user);

    /*验证手机号*/
    public int queryPhone(String userPhone);

    /*验证邮箱*/
    public int queryEmail(String userEmail);

    /*验证身份证*/
    public int queryIdentity(String userIdentity);


    /**更新最后一次登陆的时间*/
    public void updateLoginTime(String userId);

    /**统计当前登陆者公司的所有员工*/
    public int countCompanyEmp(User user);

    /**修改管理员*/
    public void updateAdmin(User user);

    /**条件查询管理员*/
    public List<User> selectQuery(@Param("pager") Pager pager, @Param("userName") String userName, @Param("userPhone") String userPhone, @Param("userEmail") String userEmail);

    /**统计条件查询管理员的个数*/
    public int countSelectAdmin(@Param("userName") String userName, @Param("userPhone") String userPhone, @Param("userEmail") String userEmail);

    /*查询自己公司的员工*/
    public List<User> queryUser(String companyId);

    /*分页查询所有不可用车主个数*/
    public int countStatus();

    /*分页查询所有不可用车主*/
    public List<User> queryCustomerPagerStatus(@Param("pager") Pager pager);

    /*分页查询所有车主个数*/
    public int countCustomer();

    /*分页查询所有车主*/
    public List<User> queryCustomer(@Param("pager") Pager pager);

    /*条件查询车主个数*/
    public int selectCountCustomer(User user);

    /*条件查询车主*/
    public List<User> selectCustomer(@Param("pager") Pager pager, @Param("user")User user);

    /*分页查询所有不可用员工个数*/
    public int countStatusEmp(User user);

    /*分页查询所有不可用员工*/
    public List<User> queryPeoplePagerStatus(@Param("pager") Pager pager, @Param("user")User user);

    /*分页查询所有员工个数*/
    public int countAllEmp(@Param("user")User user);

    /*分页查询所有员工*/
    public List<User> queryPeoplePagerAll(@Param("pager") Pager pager, @Param("user")User user);

    /*条件查询员工个数*/
    public int countSelectQueryEmp(@Param("user")User user, @Param("role")Role role, @Param("company")Company company);

    /*条件查询员工*/
    public List<User> selectQueryEmp(@Param("pager") Pager pager, @Param("user")User user, @Param("role")Role role, @Param("company")Company company);

    /*查询自己公司的技师*/
    public List<User> queryByCompanyRole(@Param("user") User user);

    public int updatePwd(@Param("user") User user);


    public int updateMessage(User user);

    /**
     * 查询平台管理员的信息
     * @return
     */
    public User queryAdmin();
    /*
    *  根据手机号或邮箱修改密码
    * */
    public int updatePwdPhone(@Param("user") User user);

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    public User queryUserByPhone(String phone);
}