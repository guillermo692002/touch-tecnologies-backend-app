package biz.touchtechnologies.backendchallanege.persistence.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private long id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String phone;

    private String role;

    public UserEntity() {
    }

    public UserEntity(long id, String email, String username, String password, String name, String phone, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity getUser = (UserEntity) o;

        if (getId() != getUser.getId()) return false;
        if (getEmail() != null ? !getEmail().equals(getUser.getEmail()) : getUser.getEmail() != null) return false;
        if (getUsername() != null ? !getUsername().equals(getUser.getUsername()) : getUser.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(getUser.getPassword()) : getUser.getPassword() != null)
            return false;
        if (getName() != null ? !getName().equals(getUser.getName()) : getUser.getName() != null) return false;
        if (getPhone() != null ? !getPhone().equals(getUser.getPhone()) : getUser.getPhone() != null) return false;
        return getRole() != null ? getRole().equals(getUser.getRole()) : getUser.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
