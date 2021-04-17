//package Distribution.API.base.Model;
//
//import Distribution.API.base.Controller.Config.Roles;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.Transient;
//
//@Entity
//public class User extends Core {
//
//    private String username;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private Roles role;
//    private String[] authorities;
//    private boolean enabled;
//
//    public User() {
//    }
//
//    public User(String username, String password, Roles role, String[] authorities, boolean enabled) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.authorities = authorities;
//        this.enabled = enabled;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Roles getRole() {
//        return role;
//    }
//
//    public void setRole(Roles role) {
//        this.role = role;
//    }
//
//    public String[] getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(String[] authorities) {
//        this.authorities = authorities;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//}
