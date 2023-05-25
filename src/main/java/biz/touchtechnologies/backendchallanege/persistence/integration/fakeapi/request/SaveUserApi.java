package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request;

import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.util.Name;

public class SaveUserApi {
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;

    public SaveUserApi() {
    }

    public SaveUserApi(String email, String username, String password, Name name, String phone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveUserApi saveUser = (SaveUserApi) o;

        if (getEmail() != null ? !getEmail().equals(saveUser.getEmail()) : saveUser.getEmail() != null) return false;
        if (getUsername() != null ? !getUsername().equals(saveUser.getUsername()) : saveUser.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(saveUser.getPassword()) : saveUser.getPassword() != null)
            return false;
        if (getName() != null ? !getName().equals(saveUser.getName()) : saveUser.getName() != null) return false;
        return getPhone() != null ? getPhone().equals(saveUser.getPhone()) : saveUser.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SaveUserApi{" +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", phone='" + phone + '\'' +
                '}';
    }
}
