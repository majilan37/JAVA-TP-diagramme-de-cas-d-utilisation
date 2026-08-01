package TP5.ex2;

public class User {
    protected final int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected final Role role;

    protected String phoneNumber;

    private static int count;

    public User(String firstName, String lastName, Role role) {
        id = ++count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(String firstName, String lastName, Role role, String phoneNumber, String email) {
        this(firstName, lastName, role);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return (firstName + " " + lastName).trim();
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
