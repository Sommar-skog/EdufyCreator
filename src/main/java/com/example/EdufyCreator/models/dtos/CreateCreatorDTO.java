package com.example.EdufyCreator.models.dtos;

//ED-332-AWS
public class CreateCreatorDTO {

    private String name;//ED-319-SA: changed from username to name. Removed sub too
    private Boolean active;

    public CreateCreatorDTO() {
    }

    public CreateCreatorDTO(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
                ", username='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
