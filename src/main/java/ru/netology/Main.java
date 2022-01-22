package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private final static String TOMCAT_DIRECTORY = "tomcat";
    private final static int PORT = 9999;

    public static void main(String [] args) {

        final Tomcat tomcat = new Tomcat();
        final Path baseDir;
        try {
            baseDir = Files.createTempDirectory(TOMCAT_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final Connector connector = new Connector();
        connector.setPort(PORT);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
            return;
        }
        tomcat.getServer().await();

    }
}
