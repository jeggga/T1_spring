package org.example;

import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);

        userService.createUser("Username1");
        userService.createUser("Username2");
        userService.createUser("Username3");

        System.out.println("--------------------------");

        System.out.println(userService.getUser("Username2").getUsername());

        System.out.println("--------------------------");

        userService.getAllUsers()
                .forEach(users -> System.out.println(users.getUsername()));

        System.out.println("--------------------------");

        userService.deleteUserByName("Username3");

        System.out.println("--------------------------");

        System.out.println(userService.getUser("Username3").getUsername());
    }
}