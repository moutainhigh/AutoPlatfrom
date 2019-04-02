package com.gs.common.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 *
 *@author qm
 *@since 2017-04-12 08:08:20
 */
public class CreateCommonUtil extends JFrame {

    private static final long serialVersionUID = 1L;

    private JCheckBox checkBox;
    Properties p = new Properties();
    String configFile = "config.ini";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CreateCommonUtil() {
        setResizable(false);

        setTitle("自动创建DAO,Service,ServiceImpl,Mapper的小工具");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setBounds(100, 100, 800, 600);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblIp = new JLabel("Bean的全限定名:");
        lblIp.setBounds(80, 13, 130, 15);
        panel.add(lblIp);

        txtClazz = new JTextField();
        txtClazz.setBounds(240, 10, 180, 21);
        panel.add(txtClazz);
        txtClazz.setColumns(10);

        JLabel lbl1 = new JLabel("* 如：com.jh.bean.User");
        lbl1.setForeground(Color.RED);
        lbl1.setBounds(430, 13, 350, 15);
        panel.add(lbl1);

        JLabel label = new JLabel("DAO的包名:");
        label.setBounds(80, 43, 180, 15);
        panel.add(label);

        txtDaoPackageName = new JTextField();
        txtDaoPackageName.setBounds(240, 40, 180, 21);
        panel.add(txtDaoPackageName);
        txtDaoPackageName.setColumns(10);

        JLabel lbl2 = new JLabel("* 如：com.jh.dao，用于导入包");
        lbl2.setForeground(Color.RED);
        lbl2.setBounds(430, 43, 350, 15);
        panel.add(lbl2);

        JLabel label1 = new JLabel("Service的包名:");
        label1.setBounds(80, 73, 180, 15);
        panel.add(label1);

        txtServicePackageName = new JTextField();
        txtServicePackageName.setBounds(240, 70, 180, 21);
        panel.add(txtServicePackageName);
        txtServicePackageName.setColumns(10);

        JLabel lbl3 = new JLabel("* 如：com.jh.service，用于导入包");
        lbl3.setForeground(Color.RED);
        lbl3.setBounds(430, 73, 350, 15);
        panel.add(lbl3);

        JLabel label2 = new JLabel("ServiceImpl的包名:");
        label2.setBounds(80, 103, 180, 15);
        panel.add(label2);

        txtServiceImplPackageName = new JTextField();
        txtServiceImplPackageName.setBounds(240, 100, 180, 21);
        panel.add(txtServiceImplPackageName);
        txtServiceImplPackageName.setColumns(10);

        JLabel lbl4 = new JLabel("* 如：com.jh.service.impl，用于导入包");
        lbl4.setForeground(Color.RED);
        lbl4.setBounds(430, 103, 350, 15);
        panel.add(lbl4);

        JLabel labelOne = new JLabel("输出目录:");
        labelOne.setBounds(80, 133, 180, 15);
        panel.add(labelOne);

        txtFilePath = new JTextField();
        txtFilePath.setBounds(240, 130, 180, 21);
        panel.add(txtFilePath);
        txtFilePath.setColumns(10);

        JLabel lbl5 = new JLabel("* 如：E:\\");
        lbl5.setForeground(Color.RED);
        lbl5.setBounds(430, 133, 350, 15);
        panel.add(lbl5);

        JLabel labelTwo = new JLabel("生成DAO结构目录:");
        labelTwo.setBounds(79, 163, 180, 15);
        panel.add(labelTwo);

        txtDaoPackage = new JTextField();
        txtDaoPackage.setBounds(240, 160, 180, 21);
        panel.add(txtDaoPackage);
        txtDaoPackage.setColumns(10);

        JLabel lbl6 = new JLabel("* 如：com.jh.dao,用于自动生成文件夹");
        lbl6.setForeground(Color.RED);
        lbl6.setBounds(430, 163, 350, 15);
        panel.add(lbl6);

        JLabel labelThree = new JLabel("生成Service结构目录:");
        labelThree.setBounds(79, 193, 180, 15);
        panel.add(labelThree);

        txtServicePackage = new JTextField();
        txtServicePackage.setBounds(240, 190, 180, 21);
        panel.add(txtServicePackage);
        txtServicePackage.setColumns(10);

        JLabel lbl7 = new JLabel("* 如：com.jh.service,用于自动生成文件夹");
        lbl7.setForeground(Color.RED);
        lbl7.setBounds(430, 193, 350, 15);
        panel.add(lbl7);

        JLabel labelFour = new JLabel("生成ServiceImp结构目录:");
        labelFour.setBounds(79, 223, 180, 15);
        panel.add(labelFour);

        txtServiceImplPackage = new JTextField();
        txtServiceImplPackage.setBounds(240, 220, 180, 21);
        panel.add(txtServiceImplPackage);
        txtServiceImplPackage.setColumns(10);

        JLabel lbl8 = new JLabel("* 如：com.jh.service.impl,用于自动生成文件夹");
        lbl8.setForeground(Color.RED);
        lbl8.setBounds(430, 223, 350, 15);
        panel.add(lbl8);

        JLabel labelFive = new JLabel("生成Mapper结构目录:");
        labelFive.setBounds(79, 250, 180, 15);
        panel.add(labelFive);

        txtMapperPackage = new JTextField();
        txtMapperPackage.setBounds(240, 250, 180, 21);
        panel.add(txtMapperPackage);
        txtMapperPackage.setColumns(10);

        JLabel lbl9 = new JLabel("* 如：com.jh.mapper,用于自动生成文件夹");
        lbl9.setForeground(Color.RED);
        lbl9.setBounds(430, 253, 350, 15);
        panel.add(lbl9);

        JLabel lblNewLabel = new JLabel("Dao的描述信息：");
        lblNewLabel.setBounds(80, 283, 180, 15);
        panel.add(lblNewLabel);

        txtDaoDes = new JTextField();
        txtDaoDes.setBounds(240, 280, 180, 21);
        panel.add(txtDaoDes);
        txtDaoDes.setColumns(10);

        JLabel lbl10 = new JLabel("如：用户的DAO");
        lbl10.setForeground(Color.BLACK);
        lbl10.setBounds(430, 283, 180, 15);
        panel.add(lbl10);

        JLabel lblNewLabel1 = new JLabel("Service的描述信息：");
        lblNewLabel1.setBounds(80, 313, 350, 15);
        panel.add(lblNewLabel1);

        txtServiceDes = new JTextField();
        txtServiceDes.setBounds(240, 310, 180, 21);
        panel.add(txtServiceDes);
        txtServiceDes.setColumns(10);

        JLabel lbl11 = new JLabel("如：用户的Service");
        lbl11.setForeground(Color.BLACK);
        lbl11.setBounds(430, 313, 180, 15);
        panel.add(lbl11);

        JLabel lblNewLabel2 = new JLabel("ServiceImpl的描述信息：");
        lblNewLabel2.setBounds(80, 343, 350, 15);
        panel.add(lblNewLabel2);

        txtServiceImplDes = new JTextField();
        txtServiceImplDes.setBounds(240, 340, 180, 21);
        panel.add(txtServiceImplDes);
        txtServiceImplDes.setColumns(10);

        JLabel lbl12 = new JLabel("如：用户的Service实现类");
        lbl12.setForeground(Color.BLACK);
        lbl12.setBounds(430, 343, 350, 15);
        panel.add(lbl12);

        JLabel lbldaoLabel = new JLabel("DAO的全限定名：");
        lbldaoLabel.setBounds(80, 373, 180, 15);
        panel.add(lbldaoLabel);

        txtDAOClazz = new JTextField();
        txtDAOClazz.setBounds(240, 370, 180, 21);
        panel.add(txtDAOClazz);
        txtDAOClazz.setColumns(10);

        JLabel lbl13 = new JLabel("* 如：com.jh.dao.UserDAO,用于导入包");
        lbl13.setForeground(Color.RED);
        lbl13.setBounds(430, 373, 350, 15);
        panel.add(lbl13);

        JLabel lblServiceLabel = new JLabel("Service的全限定名：");
        lblServiceLabel.setBounds(80, 403, 180, 15);
        panel.add(lblServiceLabel);

        txtServiceClazz = new JTextField();
        txtServiceClazz.setBounds(240, 400, 180, 21);
        panel.add(txtServiceClazz);
        txtServiceClazz.setColumns(10);

        JLabel lbl14 = new JLabel("* 如：com.jh.service.UserService,用于导入包");
        lbl14.setForeground(Color.RED);
        lbl14.setBounds(430, 403, 350, 15);
        panel.add(lbl14);

        JLabel lblPageLabel = new JLabel("分页工具类的全限定名：");
        lblPageLabel.setBounds(80, 443, 180, 15);
        panel.add(lblPageLabel);

        txtPageClazz = new JTextField();
        txtPageClazz.setBounds(240, 440, 180, 21);
        panel.add(txtPageClazz);
        txtPageClazz.setColumns(10);

        JLabel lbl15 = new JLabel("* 如：com.gs.common.bean.Pager,用于导入包");
        lbl15.setForeground(Color.RED);
        lbl15.setBounds(430, 443, 300, 15);
        panel.add(lbl15);

        checkBox = new JCheckBox("生成包结构目录");
        checkBox.setSelected(true);
        checkBox.setBounds(240, 463, 180, 23);
        panel.add(checkBox);

        JButton button = new JButton("执行");
        button.addActionListener(e -> go());
        button.setBounds(240, 493, 93, 23);
        panel.add(button);

        txtError = new JLabel("");
        txtError.setForeground(Color.RED);
        txtError.setBounds(145, 513, 150, 15);
        panel.add(txtError);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                export();
                System.exit(0);
            }

        });

        inport();
    }

    private JTextField txtClazz;
    private JTextField txtDAOClazz;
    private JTextField txtServiceClazz;
    private JTextField txtPageClazz;
    private JTextField txtDaoPackageName;
    private JTextField txtServicePackageName;
    private JTextField txtServiceImplPackageName;
    private JTextField txtFilePath;
    private JTextField txtDaoPackage;
    private JTextField txtServicePackage;
    private JTextField txtServiceImplPackage;
    private JTextField txtMapperPackage;
    private JTextField txtDaoDes;
    private JTextField txtServiceDes;
    private JTextField txtServiceImplDes;
    private JLabel txtError;

    private void go() {

        String clazz = txtClazz.getText();
        String daoClazz = txtDAOClazz.getText();
        String serviceClazz = txtServiceClazz.getText();
        String pageClazz = txtPageClazz.getText();
        String daoPackageName = txtDaoPackageName.getText();
        String servicePackageName = txtServicePackageName.getText();
        String serviceImplPackageName = txtServiceImplPackageName.getText();
        String filePath = txtFilePath.getText();
        String daoPackage = txtDaoPackage.getText();
        String servicePackage = txtServicePackage.getText();
        String serviceImplPackage = txtServiceImplPackage.getText();
        String mapperPackage = txtMapperPackage.getText();
        String daoDes = txtDaoDes.getText();
        String serviceDes = txtServiceDes.getText();
        String serviceImplDes = txtServiceImplDes.getText();
        boolean createPackage = checkBox.getSelectedObjects() != null;

        if ("".equals(clazz) || "".equals(daoPackageName) || "".equals(filePath)
                || "".equals(daoPackage) || "".equals(daoClazz) || "".equals(serviceClazz) || "".equals(pageClazz)
                || "".equals(servicePackageName) || "".equals(serviceImplPackageName) || "".equals(servicePackage)
                ||   "".equals(mapperPackage) || "".equals(serviceImplPackage)) {
            setTips("所有都必须输入");
            return;
        }

        if (!filePath.isEmpty()) {
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
        }
        String path = filePath;
        File daoDir = new File(path);
        File serviceDir = new File(path);
        File serviceImplDir = new File(path);
        File mapperDir = new File(path);
        if (createPackage) {
            daoDir = new File(path + daoPackage.replaceAll("\\.", "/"));
            if (!daoDir.exists()) {
                daoDir.mkdirs();
            }
            serviceDir = new File(path + servicePackage.replaceAll("\\.", "/"));
            if (!serviceDir.exists()) {
                serviceDir.mkdirs();
            }
            serviceImplDir = new File(path + serviceImplPackage.replaceAll("\\.", "/"));
            if (!serviceImplDir.exists()) {
                serviceImplDir.mkdirs();
            }
            mapperDir = new File(path + mapperPackage.replaceAll("\\.", "/"));
            if (!mapperDir.exists()) {
                mapperDir.mkdirs();
            }

        }
        String daoPath = daoDir.getAbsolutePath();
        String servicePath = serviceDir.getAbsolutePath();
        String serviceImplPath = serviceImplDir.getAbsolutePath();
        String mapperPath = mapperDir.getAbsolutePath();
        createServiceImpl(serviceImplPath, serviceImplPackageName, clazz, serviceDes, daoClazz, serviceClazz, pageClazz);
        createDAO(daoPath, daoPackageName, clazz, daoDes);
        createService(servicePath, servicePackageName, clazz, serviceDes);
        createMapper(mapperPath, clazz, daoClazz);
    }

    private void export() {
        String clazz = txtClazz.getText();
        String daoClazz = txtDAOClazz.getText();
        String serviceClazz = txtServiceClazz.getText();
        String pageClazz = txtPageClazz.getText();
        String daoPackageName = txtDaoPackageName.getText();
        String servicePackageName = txtServicePackageName.getText();
        String serviceImplPackageName = txtServiceImplPackageName.getText();
        String filePath = txtFilePath.getText();
        String daoPackage = txtDaoPackage.getText();
        String servicePackage = txtServicePackage.getText();
        String serviceImplPackage = txtServiceImplPackage.getText();
        String mapperPackage = txtMapperPackage.getText();
        String daoDes = txtDaoDes.getText();
        String serviceDes = txtServiceDes.getText();
        String serviceImplDes = txtServiceImplDes.getText();

        p.setProperty("clazz", clazz);
        p.setProperty("daoClazz", daoClazz);
        p.setProperty("serviceClazz", serviceClazz);
        p.setProperty("pageClazz", pageClazz);
        p.setProperty("daoPackageName", daoPackageName);
        p.setProperty("servicePackageName", servicePackageName);
        p.setProperty("serviceImplPackageName", serviceImplPackageName);
        p.setProperty("filePath", filePath);
        p.setProperty("daoPackage", daoPackage);
        p.setProperty("servicePackage", servicePackage);
        p.setProperty("serviceImplPackage", serviceImplPackage);
        p.setProperty("mapperPackage", mapperPackage);
        p.setProperty("daoDes", daoDes);
        p.setProperty("serviceDes", serviceDes);
        p.setProperty("serviceImplDes", serviceImplDes);

        try {
            OutputStream out = new FileOutputStream(configFile);
            p.store(out, "退出保存文件," + sdf.format(new Date()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void inport() {
        File config = new File(configFile);
        if (config.exists()) {
            try {
                InputStream is = new FileInputStream(config);
                p.load(is);
                is.close();
                setUIVal();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setUIVal() {
        txtClazz.setText(p.getProperty("clazz", ""));
        txtDAOClazz.setText(p.getProperty("daoClazz", ""));
        txtServiceClazz.setText(p.getProperty("serviceClazz", ""));
        txtPageClazz.setText(p.getProperty("pageClazz", ""));
        txtDaoPackageName.setText(p.getProperty("daoPackageName", ""));
        txtServicePackageName.setText(p.getProperty("servicePackageName", ""));
        txtServiceImplPackageName.setText(p.getProperty("serviceImplPackageName", ""));
        txtFilePath.setText(p.getProperty("filePath", ""));
        txtDaoPackage.setText(p.getProperty("daoPackage", ""));
        txtServicePackage.setText(p.getProperty("servicePackage", ""));
        txtServiceImplPackage.setText(p.getProperty("serviceImplPackage", ""));
        txtMapperPackage.setText(p.getProperty("mapperPackage", ""));
        txtDaoDes.setText(p.getProperty("daoDes", ""));
        txtServiceDes.setText(p.getProperty("serviceDes", ""));
        txtServiceImplDes.setText(p.getProperty("serviceImplDes", ""));
    }

    public void setTips(String msg) {
        txtError.setText(msg);
    }

    /**
     * 自动创建DAO
     * @param filePath 生成的文件路径
     * @param packageName 生成的包名
     * @param clazz Bean的全限定名
     * @param des DAO的描述信息
     */
    public void createDAO(String filePath, String packageName, String clazz, String des) {

        String beanName = clazz.substring(clazz.lastIndexOf(".")+1,clazz.length());
        String packageinfo = "package " + packageName
                + ";\r\n\r\n";
        StringBuilder classInfo = new StringBuilder("/**\r\n*");
        StringBuilder importBean = new StringBuilder("import " + clazz + ";\r\n");
        importBean.append("import org.springframework.stereotype.Repository;\r\n");
        classInfo.append("\r\n*");
        classInfo.append("\r\n");
        classInfo.append("*@author qm\r\n");
        classInfo.append("*@since ");
        classInfo.append(sdf.format(new Date()));
        classInfo.append("\r\n");
        classInfo.append("*@des " + des);
        classInfo.append("\r\n*/\r\n");

        classInfo.append("@Repository\r\n");
        classInfo.append("public interface ")
                .append(beanName + "DAO extends BaseDAO<String, " + beanName + ">").append("{\r\n");
        classInfo.append("\r\n");
        classInfo.append("}");

        File file = new File(filePath, beanName + "DAO.java");
        System.out.println("文件路径：" + file.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(packageinfo);
            fw.write(importBean.toString());
            fw.write(classInfo.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动生成Service
     * @param filePath 生成的文件路径
     * @param packageName 生成的包名
     * @param clazz Bean的全限定名
     * @param des Service的描述信息
     */
    public void createService(String filePath, String packageName, String clazz, String des) {
        String beanName = clazz.substring(clazz.lastIndexOf(".")+1,clazz.length());
        String packageinfo = "package " + packageName
                + ";\r\n\r\n";
        StringBuilder classInfo = new StringBuilder("/**\r\n*");
        String importBean = "import " + clazz + ";\r\n\r\n";
        classInfo.append("\r\n*");
        classInfo.append("\r\n");
        classInfo.append("*@author qm\r\n");
        classInfo.append("*@since ");
        classInfo.append(sdf.format(new Date()));
        classInfo.append("\r\n");
        classInfo.append("*@des " + des);
        classInfo.append("\r\n*/\r\n");

        classInfo.append("public interface ")
                .append(beanName + "Service extends BaseService<String, " + beanName + ">").append("{\r\n");
        classInfo.append("\r\n");
        classInfo.append("}");

        File file = new File(filePath, beanName + "Service.java");
        System.out.println("文件路径：" + file.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(packageinfo);
            fw.write(importBean);
            fw.write(classInfo.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动创建ServiceImpl实现类
     * @param filePath 生成的文件路径
     * @param packageName 生成的包名
     * @param clazz Bean的全限定名
     * @param des DAO的描述信息
     * @param daoClazz DAO的全限定名
     * @param serviceClazz service的全限定名
     * @param pageClazz 分页类的全限定名
     */
    public void createServiceImpl(String filePath, String packageName, String clazz, String des, String daoClazz, String serviceClazz, String pageClazz) {

        String beanName = clazz.substring(clazz.lastIndexOf(".")+1,clazz.length());
        String lowerBeanName = lowerFirestChar(beanName);
        String packageinfo = "package " + packageName
                + ";\r\n\r\n";
        StringBuilder classInfo = new StringBuilder("/**\r\n*");

        StringBuilder importBean = new StringBuilder("import " + clazz + ";\r\n");
        importBean.append("import org.springframework.stereotype.Service;\r\n");
        importBean.append("import javax.annotation.Resource;\r\n");
        importBean.append("import java.util.List;\r\n");

        importBean.append("import " + daoClazz + ";\r\n");
        importBean.append("import " + serviceClazz + ";\r\n");
        importBean.append("import " + pageClazz + ";\r\n");

        classInfo.append("\r\n*");
        classInfo.append("\r\n");
        classInfo.append("*@author qm\r\n");
        classInfo.append("*@since ");
        classInfo.append(sdf.format(new Date()));
        classInfo.append("\r\n");
        classInfo.append("*@des " + des);
        classInfo.append("\r\n*/\r\n");

        classInfo.append("@Service\r\n");
        classInfo.append("public class ")
                .append(beanName + "ServiceImpl implements " + beanName + "Service ").append("{\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t@Resource\r\n");
        classInfo.append("\tprivate " + beanName + "DAO " + lowerBeanName + "DAO;");
        classInfo.append("\r\n\r\n");

        classInfo.append("\tpublic int insert(" + beanName + " " + lowerBeanName + ") { return " + lowerBeanName + "DAO.insert(" + lowerBeanName + "); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int batchInsert(List<" + beanName + "> list) { return " + lowerBeanName + "DAO.batchInsert(list); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int delete(" + beanName + " " + lowerBeanName + ") { return " + lowerBeanName + "DAO.delete(" + lowerBeanName + "); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int deleteById(String id) {\n" +
                "        return " + lowerBeanName + "DAO.deleteById(id);\n" +
                "    }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int batchDelete(List<" + beanName + "> list) { return " + lowerBeanName + "DAO.batchDelete(list); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int update(" + beanName + " " + lowerBeanName + ") { return " + lowerBeanName + "DAO.update(" + lowerBeanName + "); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int batchUpdate(List<" + beanName + "> list) { return " + lowerBeanName + "DAO.batchUpdate(list); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic List<" + beanName + "> queryAll() { return " + lowerBeanName + "DAO.queryAll(); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic List<" + beanName + "> queryByStatus(String status) { return " + lowerBeanName + "DAO.queryByStatus(status); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic " + beanName + " query(" + beanName + " " + lowerBeanName + ") { return " + lowerBeanName + "DAO.query(" + lowerBeanName + "); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic " + beanName + " queryById(String id) { return " + lowerBeanName + "DAO.queryById(id); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic List<" + beanName + "> queryByPager(Pager pager) { return " + lowerBeanName + "DAO.queryByPager(pager); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int count() { return " + lowerBeanName + "DAO.count(); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int inactive(String id) { return " + lowerBeanName + "DAO.inactive(id); }");
        classInfo.append("\r\n");
        classInfo.append("\tpublic int active(String id) { return " + lowerBeanName + "DAO.active(id); }");
        classInfo.append("\r\n");

        classInfo.append("\r\n");
        classInfo.append("}");

        File file = new File(filePath, beanName + "ServiceImpl.java");
        System.out.println("文件路径：" + file.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(packageinfo);
            fw.write(importBean.toString());
            fw.write(classInfo.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动创建Mapper
     * @param filePath 生成的文件路径
     * @param clazz Bean的全限定名
     * @param daoClazz DAO的全限定名
     */
    public void createMapper(String filePath, String clazz, String daoClazz) {

        String beanName = clazz.substring(clazz.lastIndexOf(".")+1,clazz.length());
        String lowerBeanName = lowerFirestChar(beanName);
        String packageInfo = "<!DOCTYPE mapper\r\n\tPUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\r\n\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n\r\n";
        StringBuilder classInfo = new StringBuilder("<mapper namespace=\"" + daoClazz + "\">\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t<resultMap id=\"" + lowerBeanName + "ResultMap\" type=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t</resultMap>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<insert id=\"insert\"  parameterType=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</insert>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<insert id=\"batchInsert\"  parameterType=\"list\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</insert>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<delete id=\"delete\" parameterType=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</delete>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<delete id=\"deleteById\" parameterType=\"string\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</delete>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<delete id=\"batchDelete\" parameterType=\"list\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</delete>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<update id=\"update\" parameterType=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</update>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<update id=\"batchUpdate\" parameterType=\"list\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</update>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"queryAll\" resultMap=\"" + lowerBeanName + "ResultMap\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"queryByStatus\" resultMap=\"" + lowerBeanName + "ResultMap\" parameterType=\"string\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"query\" parameterType=\"" + lowerBeanName + "\" resultType=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"queryById\" parameterType=\"string\" resultType=\"" + lowerBeanName + "\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"queryByPager\" resultType=\"list\" resultMap=\"" + lowerBeanName + "ResultMap\" parameterType=\"com.gs.common.bean.Pager\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<select id=\"count\" resultType=\"int\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</select>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<update id=\"inactive\" parameterType=\"string\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</update>\r\n");
        classInfo.append("\r\n");

        classInfo.append("\t<update id=\"active\" parameterType=\"string\">\r\n");
        classInfo.append("\t\t<![CDATA[\r\n");
        classInfo.append("\r\n");
        classInfo.append("\t\t]]>\r\n");
        classInfo.append("\t</update>\r\n");
        classInfo.append("\r\n");

        classInfo.append("</mapper>");
        classInfo.append("\r\n");


        File file = new File(filePath, beanName + "Mapper.xml");
        System.out.println("文件路径：" + file.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(packageInfo);
            fw.write(classInfo.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首字母变大写
     * @param src 要改变的字符串
     * @return
     */
    public String upperFirestChar(String src) {
        return src.substring(0, 1).toUpperCase().concat(src.substring(1));
    }

    /**
     * 首字母变小写
     * @param src 要改变的字符串
     * @return
     */
    public String lowerFirestChar(String src) {
        return src.substring(0, 1).toLowerCase().concat(src.substring(1));
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateCommonUtil frame = new CreateCommonUtil();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
