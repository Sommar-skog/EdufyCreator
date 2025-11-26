package com.example.EdufyCreator.models.dtos;

//ED-332-AWS
public class CreateCreatorDTO {

    private String username;//ED-319-SA: Removed sub
    private Boolean active;

    public CreateCreatorDTO() {
    }

    public CreateCreatorDTO(String username, Boolean active) {
        this.username = username;
        this.active = active;
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
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }
}
