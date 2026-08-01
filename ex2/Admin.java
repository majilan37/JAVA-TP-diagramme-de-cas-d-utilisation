package TP5.ex2;

public class Admin extends User {
    public Admin(String fistName, String lastName, String email) {
        super(fistName, lastName, Role.ADMIN);
        this.email = email;
    }

}
