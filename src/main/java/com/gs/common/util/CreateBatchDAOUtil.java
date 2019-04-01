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
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-12 08:08:20
 */
public class CreateBatchDAOUtil extends JFrame {

    private static final long serialVersionUID = 1L;

    private JCheckBox checkBox;
    Properties p = new Properties();
    String configFile = "config.ini";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CreateBatchDAOUtil() {
        setResizable(false);

        setTitle("批量创建DAO的小工具");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setBounds(100, 100, 700, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblIp = new JLabel("Bean的简单类名:");
        lblIp.setBounds(80, 13, 130, 15);
        panel.add(lblIp);

        txtClazz = new JTextField();
        txtClazz.setBounds(190, 10, 147, 21);
        panel.add(txtClazz);
        txtClazz.setColumns(10);

        JLabel lbl1 = new JLabel("* 如：User | User,Company，多个类用’,‘号隔开");
        lbl1.setForeground(Color.RED);
        lbl1.setBounds(350, 13, 350, 15);
        panel.add(lbl1);

        JLabel label = new JLabel("包名:");
        label.setBounds(80, 42, 100, 15);
        panel.add(label);

        txtPackageName = new JTextField();
        txtPackageName.setBounds(190, 39, 147, 21);
        panel.add(txtPackageName);
        txtPackageName.setColumns(10);

        JLabel lbl2 = new JLabel("* 如：com.jh.dao，用于导入包");
        lbl2.setForeground(Color.RED);
        lbl2.setBounds(350, 42, 350, 15);
        panel.add(lbl2);

        JLabel label_1 = new JLabel("输出目录:");
        label_1.setBounds(80, 71, 100, 15);
        panel.add(label_1);

        txtFilePath = new JTextField();
        txtFilePath.setBounds(190, 68, 147, 21);
        panel.add(txtFilePath);
        txtFilePath.setColumns(10);

        JLabel lbl3 = new JLabel("如：E:\\，默认在项目的目录下");
        lbl3.setForeground(Color.BLACK);
        lbl3.setBounds(350, 69, 350, 15);
        panel.add(lbl3);

        JLabel lblNewLabel = new JLabel("Bean的包名：");
        lblNewLabel.setBounds(80, 129, 130, 15);
        panel.add(lblNewLabel);

        txtBeanPackageName = new JTextField();
        txtBeanPackageName.setBounds(190, 126, 147, 21);
        panel.add(txtBeanPackageName);
        txtBeanPackageName.setColumns(10);

        JLabel lbl5 = new JLabel("* 如：com.jh.bean,用于导入Bean");
        lbl5.setForeground(Color.RED);
        lbl5.setBounds(357, 126, 350, 15);
        panel.add(lbl5);

        JLabel label_2 = new JLabel("生成包结构目录:");
        label_2.setBounds(79, 100, 100, 15);
        panel.add(label_2);

        txtPackage = new JTextField();
        txtPackage.setBounds(190, 97, 147, 21);
        panel.add(txtPackage);
        txtPackage.setColumns(10);

        JLabel lbl4 = new JLabel("* 如：com.jh.dao,用于自动生成文件夹");
        lbl4.setForeground(Color.RED);
        lbl4.setBounds(350, 97, 350, 15);
        panel.add(lbl4);

        checkBox = new JCheckBox("生成包结构目录");
        checkBox.setSelected(true);
        checkBox.setBounds(190, 150, 147, 23);
        panel.add(checkBox);

        JButton button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go();
            }
        });
        button.setBounds(190, 190, 93, 23);
        panel.add(button);

        txtError = new JLabel("");
        txtError.setForeground(Color.RED);
        txtError.setBounds(145, 230, 150, 15);
        panel.add(txtError);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                export();
                System.exit(0);
            }

        });

        inport();
    }

    private JTextField txtClazz;
    private JTextField txtPackageName;
    private JTextField txtFilePath;
    private JTextField txtPackage;
    private JTextField txtBeanPackageName;
    private JLabel txtError;

    private void go() {

        String clazz = txtClazz.getText();
        String packageName = txtPackageName.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String beanPackageName = txtBeanPackageName.getText();
        boolean createPackage = checkBox.getSelectedObjects() != null;

        if (clazz.equals("") || packageName.equals("") || packages.equals("") || beanPackageName.equals("")) {
            setTips("所有都必须输入");
            return;
        }

        if (filePath.equals("")) {
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
        createDAO(filePath, packageName, clazz, beanPackageName);

    }

    private void export() {
        String clazz = txtClazz.getText();
        String packageName = txtPackageName.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String beanPackageName = txtBeanPackageName.getText();

        p.setProperty("clazz", clazz);
        p.setProperty("packageName", packageName);
        p.setProperty("filePath", filePath);
        p.setProperty("packages", packages);
        p.setProperty("beanPackageName", beanPackageName);

        try {
            OutputStream out = new FileOutputStream(configFile);
            p.store(out, "退出保存文件," + sdf.format(new Date()));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
        txtPackageName.setText(p.getProperty("packageName", ""));
        txtFilePath.setText(p.getProperty("filePath", ""));
        txtPackage.setText(p.getProperty("packages", ""));
        txtBeanPackageName.setText(p.getProperty("beanPackageName", ""));
    }

    public void setTips(String msg) {
        txtError.setText(msg);
    }

    public String getProjSrcPath() {
        String path = System.getProperty("user.dir") + "\\src\\main\\java";
        return path;
    }

    /**
     * 自动创建DAO
     *
     * @param filePath    生成的文件路径
     * @param packageName 生成的包名
     * @param clazz       Bean简单类名，多个Bean用’,‘号隔开
     * @param beanPackageName Bean的包名
     */
    public void createDAO(String filePath, String packageName, String clazz, String beanPackageName) {
        String[] beanNames = clazz.split(",");

        for (int i = 0,len = beanNames.length; i < len; i++) {
            String beanName = beanNames[i];
            String packageinfo = "package " + packageName
                    + ";\r\n\r\n";
            StringBuilder classInfo = new StringBuilder("/**\r\n*");
            StringBuilder importBean = new StringBuilder("import " + beanPackageName + "." + beanName + ";\r\n");
            importBean.append("import org.springframework.stereotype.Repository;\r\n");
            classInfo.append("由Wjhsmart技术支持\r\n*");
            classInfo.append("\r\n");
            classInfo.append("*@author Wjhsmart\r\n");
            classInfo.append("*@since ");
            classInfo.append(sdf.format(new Date()));
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
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateBatchDAOUtil frame = new CreateBatchDAOUtil();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
