package project.jpa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import project.jpa.entity.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Schema(description = "사용자")
public class UpdateUserDto {

    private Long id;

    @NotEmpty
    @Schema(description = "유저 ID")
    private String personalId;


    @NotEmpty
    @Schema(description = "비밀번호", example = "password")
    private String password;

    @NotEmpty
    @Schema(description = "이름", example = "맨텍")
    private String name;

    @Schema(description = "전화번호", example = "01099991111")
    private String phone;

    @Email
    @Schema(description = "이메일", nullable = false, example = "abc@navee.com")
    private String email;

    @Schema(description = "생년월일", example = "1989-02-27")
    private LocalDate birthday;

    @Schema(description = "성별", defaultValue = "male")
    private Gender gender;
}
