package project.jpa.dto;

import lombok.Data;

@Data
public class UserSearchCondition {
    private String name;
    private Integer ageGoe;
    private Integer ageLoe;
}
