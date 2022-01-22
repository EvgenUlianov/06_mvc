package ru.netology;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer  implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.netology");
        context.refresh();

        System.out.println("sdvsdfvsdfvsdfvsdfvsdfvsdfv");

        final DispatcherServlet servlet = new DispatcherServlet(context);
        final ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
