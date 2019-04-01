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
 *由Wjhsmart技术支持
 *
 *@author Wjhsmart
 *@since 2017-04-12 08:08:20
 */
public class CreateMapperUtil extends JFrame {

    private static final long serialVersionUID = 1L;

    private JCheckBox checkBox;
    Properties p = new Properties();
    String configFile = "config.ini";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CreateMapperUtil() {
        setResizable(false);

        setTitle("自动创建Mapper的小工具");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setBounds(100, 100, 600, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblIp = new JLabel("Bean的全限定名:");
        lblIp.setBounds(80, 13, 130, 15);
        panel.add(lblIp);

        txtClazz = new JTextField();
        txtClazz.setBounds(190, 10, 147, 21);
        panel.add(txtClazz);
        txtClazz.setColumns(10);

        JLabel lbl1 = new JLabel("* 如：com.jh.bean.User");
        lbl1.setForeground(Color.RED);
        lbl1.setBounds(345, 13, 350, 15);
        panel.add(lbl1);

        JLabel label_1 = new JLabel("输出目录:");
        label_1.setBounds(80, 41, 100, 15);
        panel.add(label_1);

        txtFilePath = new JTextField();
        txtFilePath.setBounds(190, 38, 147, 21);
        panel.add(txtFilePath);
        txtFilePath.setColumns(10);

        JLabel lbl5 = new JLabel("如：E:\\，不指定默认在项目目录下");
        lbl5.setForeground(Color.BLACK);
        lbl5.setBounds(345, 41, 350, 15);
        panel.add(lbl5);

        JLabel label_2 = new JLabel("生成包结构目录:");
        label_2.setBounds(79, 70, 100, 15);
        panel.add(label_2);

        txtPackage = new JTextField();
        txtPackage.setBounds(190, 67, 147, 21);
        panel.add(txtPackage);
        txtPackage.setColumns(10);

        JLabel lbl9 = new JLabel("* 如：com.jh.mapper,用于自动生成文件夹");
        lbl9.setForeground(Color.RED);
        lbl9.setBounds(345, 70, 350, 15);
        panel.add(lbl9);

        JLabel lblNewLabel = new JLabel("DAO的全限定名：");
        lblNewLabel.setBounds(80, 99, 130, 15);
        panel.add(lblNewLabel);

        txtDaoClazz = new JTextField();
        txtDaoClazz.setBounds(190, 96, 147, 21);
        panel.add(txtDaoClazz);
        txtDaoClazz.setColumns(10);

        JLabel lbl2 = new JLabel("* 如：com.jh.dao，用于导入包");
        lbl2.setForeground(Color.RED);
        lbl2.setBounds(345, 99, 350, 15);
        panel.add(lbl2);

        checkBox = new JCheckBox("生成包结构目录");
        checkBox.setSelected(true);
        checkBox.setBounds(190, 120, 147, 23);
        panel.add(checkBox);

        JButton button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go();
            }
        });
        button.setBounds(190, 160, 93, 23);
        panel.add(button);

        txtError = new JLabel("");
        txtError.setForeground(Color.RED);
        txtError.setBounds(145, 200, 150, 15);
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
    private JTextField txtFilePath;
    private JTextField txtPackage;
    private JTextField txtDaoClazz;
    private JLabel txtError;

    private void go() {

        String clazz = txtClazz.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String daoClazz = txtDaoClazz.getText();
        boolean createPackage = checkBox.getSelectedObjects() != null;

        if (clazz.equals("") || packages.equals("") || daoClazz.equals("")) {
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
        createMapper(filePath, clazz, daoClazz);

    }

    private void export() {
        String clazz = txtClazz.getText();
        String filePath = txtFilePath.getText();
        String packages = txtPackage.getText();
        String daoClazz = txtDaoClazz.getText();

        p.setProperty("clazz", clazz);
        p.setProperty("filePath", filePath);
        p.setProperty("packages", packages);
        p.setProperty("daoClazz", daoClazz);

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
        txtFilePath.setText(p.getProperty("filePath", ""));
        txtPackage.setText(p.getProperty("packages", ""));
        txtDaoClazz.setText(p.getProperty("daoClazz", ""));
    }

    public void setTips(String msg) {
        txtError.setText(msg);
    }

    public String getProjSrcPath() {
        String path = System.getProperty("user.dir")+"\\src\\main\\resources";
        return path;
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
                    CreateMapperUtil frame = new CreateMapperUtil();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
