package com.gs.common.web;

import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by WangGenshen on 7/29/16.
 */
public class ServletContextUtil {

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static String getContextPath() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getContextPath();
    }

    /**
     * 获取服务器IP地址,如果有多个ip，则会获取有外网的ip
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getServerIp() {
        String SERVER_IP = null;
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    SERVER_IP = ip.getHostAddress();
                }
            }
        }
        return SERVER_IP;
    }
}
