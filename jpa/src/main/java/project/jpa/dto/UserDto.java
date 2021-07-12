package project.jpa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private int age;


    @QueryProjection
    public UserDto(Long userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }
}
