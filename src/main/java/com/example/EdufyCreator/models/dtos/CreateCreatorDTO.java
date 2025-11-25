package com.example.EdufyCreator.models.dtos;

//ED-332-AWS
public class CreateCreatorDTO {

    private String sub;
    private String username;
    private Boolean active;

    public CreateCreatorDTO() {
    }

    public CreateCreatorDTO(String sub, String username, Boolean active) {
        this.sub = sub;
        this.username = username;
        this.active = active;
    }

    public String getSub() {
        return sub;
    }
    public void setSub(String sub) {
        this.sub = sub;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CreateCreatorDTO{" +
                "sub='" + sub + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }
}
