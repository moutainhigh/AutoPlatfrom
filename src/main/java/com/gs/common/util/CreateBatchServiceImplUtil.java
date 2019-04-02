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
public class CreateBatchServiceImplUtil extends JFrame {

    private static final long serialVersionUID = 1L;

    private JCheckBox checkBox;
    Properties p = new Properties();
    String configFile = "config.ini";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CreateBatchServiceImplUtil() {
        setResizable(false);

        setTitle("批量创建ServiceImpl的小工具");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setBounds(100, 100, 800, 350);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblIp = new JLabel("Bean的简单类名:");
        lblIp.setBounds(80, 13, 130, 15);
        panel.add(lblIp);

        txtClazz = new JTextField();
        txtClazz.setBounds(200, 10, 147, 21);
        panel.add(txtClazz);
        txtClazz.setColumns(10);

        JLabel lbl1 = new JLabel("* 如：User|User,Company,多个类用’,‘号隔开");
        lbl1.setForeground(Color.RED);
        lbl1.setBounds(350, 13, 350, 15);
        panel.add(lbl1);

        JLabel label = new JLabel("包名:");
        label.setBounds(80, 42, 100, 15);
        panel.add(label);

        txtPackageName = new JTextField();
        txtPackageName.setBounds(200, 39, 147, 21);
        panel.add(txtPackageName);
        txtPackageName.setColumns(10);

        JLabel lbl4 = new JLabel("* 如：com.jh.service.impl，用于导入包");
        lbl4.setForeground(Color.RED);
        lbl4.setBounds(350, 42, 350, 15);
        panel.add(lbl4);

        JLabel label1 = new JLabel("输出目录:");
        label1.setBounds(80, 71, 100, 15);
        panel.add(label1);

        txtFilePath = new JTextField();
        txtFilePath.setBounds(200, 68, 147, 21);
        panel.add(txtFilePath);
        txtFilePath.setColumns(10);

        JLabel lbl5 = new JLabel("如：E:\\,默认创建在项目目录下");
        lbl5.setForeground(Color.BLACK);
        lbl5.setBounds(350, 71, 350, 15);
        panel.add(lbl5);

        JLabel label2 = new JLabel("生成包结构目录:");
        label2.setBounds(79, 100, 100, 15);
        panel.add(label2);

        txtPackage = new JTextField();
        txtPackage.setBounds(200, 97, 147, 21);
        panel.add(txtPackage);
        txtPackage.setColumns(10);

        JLabel lbl8 = new JLabel("* 如：com.jh.service.impl,用于自动生成文件夹");
        lbl8.setForeground(Color.RED);
        lbl8.setBounds(350, 100, 350, 15);
        panel.add(lbl8);

        JLabel lblNewLabel = new JLabel("Bean的包名：");
        lblNewLabel.setBounds(80, 129, 150, 15);
        panel.add(lblNewLabel);

        txtBeanPackageName = new JTextField();
        txtBeanPackageName.setBounds(200, 126, 147, 21);
        panel.add(txtBeanPackageName);
        txtBeanPackageName.setColumns(10);

        JLabel lbl11 = new JLabel("* 如：com.jh.bean");
        lbl11.setForeground(Color.RED);
        lbl11.setBounds(358, 129, 180, 15);
        panel.add(lbl11);

        JLabel lbldaoLabel = new JLabel("DAO的全限定名：");
        lbldaoLabel.setBounds(80, 158, 150, 15);
        panel.add(lbldaoLabel);

        txtDAOClazz = new JTextField();
        txtDAOClazz.setBounds(200, 155, 147, 21);
        panel.add(txtDAOClazz);
        txtDAOClazz.setColumns(10);

        JLabel lbl13 = new JLabel("* 如：com.jh.dao.UserDAO,用于导入包");
        lbl13.setForeground(Color.RED);
        lbl13.setBounds(350, 158, 350, 15);
        panel.add(lbl13);

        JLabel lblServiceLabel = new JLabel("Service的全限定名：");
        lblServiceLabel.setBounds(80, 187, 150, 15);
        panel.add(lblServiceLabel);

        txtServiceClazz = new JTextField();
        txtServiceClazz.setBounds(200, 184, 147, 21);
        panel.add(txtServiceClazz);
        txtServiceClazz.setColumns(10);

        JLabel lbl14 = new JLabel("* 如：com.jh.service.UserService,用于导入包");
        lbl14.setForeground(Color.RED);
        lbl14.setBounds(350, 187, 350, 15);
        panel.add(lbl14);

        JLabel lblPageLabel = new JLabel("分页工具类的全限定名：");
        lblPageLabel.setBounds(80, 216, 150, 15);
        panel.add(lblPageLabel);

        txtPageClazz = new JTextField();
        txtPageClazz.setBounds(200, 213, 147, 21);
        panel.add(txtPageClazz);
        txtPageClazz.setColumns(10);

        JLabel lbl15 = new JLabel("* 如：com.gs.common.bean.Pager,用于导入包");
        lbl15.setForeground(Color.RED);
        lbl15.setBounds(350, 216, 350, 15);
        panel.add(lbl15);

        checkBox = new JCheckBox("生成包结构目录");
        checkBox.setSelected(true);
        checkBox.setBounds(200, 239, 147, 23);
        panel.add(checkBox);

        JButton button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go();
            }
        });
        button.setBounds(200, 268, 93, 23);
        panel.add(button);

        txtError = new JLabel("");
        txtError.setForeground(Color.RED);
        txtError.setBounds(145, 297, 150, 15);
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
    private JTextField txtPackageName;
    private JTextField txtFilePath;
    private JTextField txtPackage;
    private JTextField txtBeanPackageName;
    private JLabel txtError;

    private void go() {

        String clazz = txtClazz.getText();
        String daoClazz = txtDAOClazz.getText();
        String serviceClazz = txtServiceClazz.getText();
        String pageClazz = txtPageClazz.getText();
        String packageName = txtPackageName.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String beanPackageName = txtBeanPackageName.getText();
        boolean createPackage = checkBox.getSelectedObjects() != null;

        if ("".equals(clazz) || "".equals(packageName) || "".equals(beanPackageName) ||
                "".equals(packages) || "".equals(daoClazz) || "".equals(serviceClazz) || "".equals(pageClazz)) {
            setTips("所有都必须输入");
            return;
        }

        if ("".equals(filePath)) {
            filePath = getProjSrcPath();
        }

        if (filePath != null && !filePath.isEmpty()) {
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
        }
        File dir = new File(filePath);
        if (createPackage) {
            dir = new File(filePath + packages.replaceAll("\\.", "/"));
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        filePath = dir.getAbsolutePath();
        createServiceImpl(filePath, packageName, clazz, daoClazz, serviceClazz, pageClazz, beanPackageName);

    }

    private void export() {
        String clazz = txtClazz.getText();
        String daoClazz = txtDAOClazz.getText();
        String serviceClazz = txtServiceClazz.getText();
        String pageClazz = txtPageClazz.getText();
        String packageName = txtPackageName.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String beanPackageName = txtBeanPackageName.getText();

        p.setProperty("clazz", clazz);
        p.setProperty("daoClazz", daoClazz);
        p.setProperty("serviceClazz", serviceClazz);
        p.setProperty("pageClazz", pageClazz);
        p.setProperty("packageName", packageName);
        p.setProperty("filePath", filePath);
        p.setProperty("packages", packages);
        p.setProperty("beanPackageName", beanPackageName);



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
        txtPackageName.setText(p.getProperty("packageName", ""));
        txtFilePath.setText(p.getProperty("filePath", ""));
        txtPackage.setText(p.getProperty("packages", ""));
        txtBeanPackageName.setText(p.getProperty("beanPackageName", ""));
    }

    public void setTips(String msg) {
        txtError.setText(msg);
    }

    public String getProjSrcPath() {
        String path = System.getProperty("user.dir")+"\\src\\main\\java";
        return path;
    }

    /**
     * 自动创建ServiceImpl实现类
     * @param filePath 生成的文件路径
     * @param packageName 生成的包名
     * @param clazz Bean的简单类名
     * @param daoClazz DAO的全限定名
     * @param serviceClazz service的全限定名
     * @param pageClazz 分页类的全限定名
     * @param beanPackageName Bean的包名
     */
    public void createServiceImpl(String filePath, String packageName, String clazz, String daoClazz, String serviceClazz, String pageClazz, String beanPackageName) {
        String[] beanNames = clazz.split(",");

        for (String beanName : beanNames) {
            String lowerBeanName = lowerFirestChar(beanName);
            String packageinfo = "package " + packageName
                    + ";\r\n\r\n";
            StringBuilder classInfo = new StringBuilder("/**\r\n*");

            StringBuilder importBean = new StringBuilder("import " + beanPackageName + "." + beanName + ";\r\n");
            importBean.append("import org.springframework.stereotype.Service;\r\n");
            importBean.append("import javax.annotation.Resource;\r\n");
            importBean.append("import java.util.List;\r\n");

            importBean.append("import ").append(daoClazz).append(".").append(beanName).append("DAO;\r\n");
            importBean.append("import ").append(serviceClazz).append(".").append(beanName).append("Service;\r\n");
            importBean.append("import ").append(pageClazz).append(";\r\n");

            classInfo.append("\r\n*");
            classInfo.append("\r\n");
            classInfo.append("*@author qm\r\n");
            classInfo.append("*@since ");
            classInfo.append(sdf.format(new Date()));
            classInfo.append("\r\n*/\r\n");

            classInfo.append("@Service\r\n");
            classInfo.append("public class ").append(beanName).append("ServiceImpl implements ").append(beanName).append("Service ").append("{\r\n");
            classInfo.append("\r\n");
            classInfo.append("\t@Resource\r\n");
            classInfo.append("\tprivate ").append(beanName).append("DAO ").append(lowerBeanName).append("DAO;");
            classInfo.append("\r\n\r\n");

            classInfo.append("\tpublic int insert(").append(beanName).append(" ").append(lowerBeanName).append(") { return ").append(lowerBeanName).append("DAO.insert(").append(lowerBeanName).append("); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int batchInsert(List<").append(beanName).append("> list) { return ").append(lowerBeanName).append("DAO.batchInsert(list); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int delete(").append(beanName).append(" ").append(lowerBeanName).append(") { return ").append(lowerBeanName).append("DAO.delete(").append(lowerBeanName).append("); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int deleteById(String id) {\n" + "        return ").append(lowerBeanName).append("DAO.deleteById(id);\n").append("    }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int batchDelete(List<").append(beanName).append("> list) { return ").append(lowerBeanName).append("DAO.batchDelete(list); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int update(").append(beanName).append(" ").append(lowerBeanName).append(") { return ").append(lowerBeanName).append("DAO.update(").append(lowerBeanName).append("); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int batchUpdate(List<").append(beanName).append("> list) { return ").append(lowerBeanName).append("DAO.batchUpdate(list); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic List<").append(beanName).append("> queryAll() { return ").append(lowerBeanName).append("DAO.queryAll(); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic List<").append(beanName).append("> queryByStatus(String status) { return ").append(lowerBeanName).append("DAO.queryByStatus(status); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic ").append(beanName).append(" query(").append(beanName).append(" ").append(lowerBeanName).append(") { return ").append(lowerBeanName).append("DAO.query(").append(lowerBeanName).append("); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic ").append(beanName).append(" queryById(String id) { return ").append(lowerBeanName).append("DAO.queryById(id); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic List<").append(beanName).append("> queryByPager(Pager pager) { return ").append(lowerBeanName).append("DAO.queryByPager(pager); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int count() { return ").append(lowerBeanName).append("DAO.count(); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int inactive(String id) { return ").append(lowerBeanName).append("DAO.inactive(id); }");
            classInfo.append("\r\n");
            classInfo.append("\tpublic int active(String id) { return ").append(lowerBeanName).append("DAO.active(id); }");
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
        EventQueue.invokeLater(() -> {
            try {
                CreateBatchServiceImplUtil frame = new CreateBatchServiceImplUtil();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
