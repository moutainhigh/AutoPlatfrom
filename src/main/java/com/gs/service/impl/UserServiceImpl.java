package com.gs.service.impl;

import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.gs.dao.UserDAO;
import com.gs.service.UserService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public int insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public int batchInsert(List<User> list) {
        return userDAO.batchInsert(list);
    }

    @Override
    public int delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public int deleteById(String id) {
        return userDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<User> list) {
        return userDAO.batchDelete(list);
    }

    @Override
    public int update(User user) {
        return userDAO.update(user);
    }

    @Override
    public int batchUpdate(List<User> list) {
        return userDAO.batchUpdate(list);
    }

    @Override
    public List<User> queryAll(User user) {
        return userDAO.queryAll(user);
    }

    @Override
    public List<User> queryByStatus(String status, User user) {
        return userDAO.queryByStatus(status, user);
    }

    @Override
    public User query(User user, User user1) {
        return userDAO.query(user, user1);
    }

    @Override
    public User queryById(String id) {
        return userDAO.queryById(id);
    }

    @Override
    public List<User> queryByPager(Pager pager, User user) {
        return userDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return userDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return userDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return userDAO.active(id);
    }

    @Override
    public List<User> queryByUser(Pager pager, String companyId) {
        return userDAO.queryByUser(pager, companyId);
    }

    @Override
    public int countByUser(String companyId) {
        return userDAO.countByUser(companyId);
    }

    @Override
    public List<User> queryByAdminPager(Pager pager) {
        return userDAO.queryByAdminPager(pager);
    }

    @Override
    public int countAdmin() {
        return userDAO.countAdmin();
    }

    @Override
    public List<User> queryByCompanyAdminPager(Pager pager) {
        return userDAO.queryByCompanyAdminPager(pager);
    }

    @Override
    public int countCompanyAdmin() {
        return userDAO.countCompanyAdmin();
    }

    @Override
    public List<User> queryBySystemAdminPager(Pager pager) {
        return userDAO.queryBySystemAdminPager(pager);
    }

    @Override
    public int countSystemAdmin() {
        return userDAO.countSystemAdmin();
    }

    @Override
    public int countByCustomer() {
        return userDAO.countByCustomer();
    }

    @Override
    public List<User> queryCustomerPager(Pager pager) {
        return userDAO.queryCustomerPager(pager);
    }

    @Override
    public List<User> queryPeoplePager(Pager pager, User user) {
        return userDAO.queryPeoplePager(pager, user);
    }

    @Override
    public User queryLogin(User user) {
        return userDAO.queryLogin(user);
    }

    @Override
    public User queryByPhone(String phone) {
        return userDAO.queryByPhone(phone);
    }

    @Override
    public int queryPhone(String userPhone) {
        return userDAO.queryPhone(userPhone);
    }

    @Override
    public int queryEmail(String userEmail) {
        return userDAO.queryEmail(userEmail);
    }

    @Override
    public int queryIdentity(String userIdentity) {
        return userDAO.queryIdentity(userIdentity);
    }

    @Override
    public void updateAdmin(User user) {
        userDAO.updateAdmin(user);
    }

    @Override
    public List<User> selectQuery(Pager pager, String userName, String userPhone, String userEmail) {
        return userDAO.selectQuery(pager, userName, userPhone, userEmail);
    }

    @Override
    public int countSelectAdmin(String userName, String userPhone, String userEmail) {
        return userDAO.countSelectAdmin(userName, userPhone, userEmail);
    }

    @Override
    public List<User> queryUser(String companyId) {
        return userDAO.queryUser(companyId);
    }

    @Override
    public int countStatus() {
        return userDAO.countStatus();
    }

    @Override
    public List<User> queryCustomerPagerStatus(Pager pager) {
        return userDAO.queryCustomerPagerStatus(pager);
    }

    @Override
    public int countCustomer() {
        return userDAO.countCustomer();
    }

    @Override
    public List<User> queryCustomer(Pager pager) {
        return userDAO.queryCustomer(pager);
    }

    @Override
    public int selectCountCustomer(User user) {
        return userDAO.selectCountCustomer(user);
    }

    @Override
    public List<User> selectCustomer(Pager pager, User user) {
        return userDAO.selectCustomer(pager, user);
    }

    @Override
    public int countStatusEmp(User user) {
        return userDAO.countStatusEmp(user);
    }

    @Override
    public List<User> queryPeoplePagerStatus(Pager pager, User user) {
        return userDAO.queryPeoplePagerStatus(pager, user);
    }

    @Override
    public int countAllEmp(User user) {
        return userDAO.countAllEmp(user);
    }

    @Override
    public List<User> queryPeoplePagerAll(Pager pager, User user) {
        return userDAO.queryPeoplePagerAll(pager, user);
    }

    @Override
    public int countSelectQueryEmp(User user, Role role, Company company) {
        return userDAO.countSelectQueryEmp(user, role, company);
    }

    @Override
    public List<User> selectQueryEmp(Pager pager, User user, Role role, Company company) {
        return userDAO.selectQueryEmp(pager, user, role, company);
    }

    @Override
    public List<User> queryByCompanyRole(User user) {
        return userDAO.queryByCompanyRole(user);
    }

    @Override
    public int updatePwd(User user) {
        return userDAO.updatePwd(user);
    }

    @Override
    public int updateMessage(User user) {
        return userDAO.updateMessage(user);
    }

    @Override
    public User queryAdmin() {
        return userDAO.queryAdmin();
    }

    @Override
    public int updatePwdPhone(User user) {
        return userDAO.updatePwdPhone(user);
    }

    @Override
    public User queryUserByPhone(String phone) {
        return userDAO.queryByPhone(phone);
    }


    @Override
    public void insertAdmin(User user) {
        userDAO.insertAdmin(user);
    }

    @Override
    public void updateLoginTime(String userId) {
        userDAO.updateLoginTime(userId);
    }

    @Override
    public int countCompanyEmp(User user) {
        return userDAO.countCompanyEmp(user);
    }

    @Override
    public int countPeopleEmp(User user) {
        return userDAO.countPeopleEmp(user);
    }
}