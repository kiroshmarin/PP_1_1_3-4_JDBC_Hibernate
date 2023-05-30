package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Антон", "Антонов", (byte) 7);
        service.saveUser("Петр", "Петров", (byte) 33);
        service.saveUser("Анастасия", "Снастева", (byte) 54);
        service.saveUser("Евгений", "Сухой", (byte) 21);

        service.getAllUsers().forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
