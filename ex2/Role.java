package TP5.ex2;

public enum Role {
    CLIENT("client"),
    AGENT("agent"),
    ADMIN("admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
