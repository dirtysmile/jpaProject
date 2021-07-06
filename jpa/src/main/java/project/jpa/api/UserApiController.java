package project.jpa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Api(tags={"1. Dashboard"})
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid User user){
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    /**
     * 사용자 등록
     *
     * spring rest doc ( 스와거 문서 )
     * java doc
     * @param  request <br/>
     *          String persionalId 사용자 ID <br/>
     *          String password 사용자 비밀번호 <br/>
     *          String name 사용자 이름 <br/>
     * @version 4.6.1
     * @exception NullPointerException
     * @see 체체체
     * @return User 테이블의 id 값.
     */
    @ApiOperation(value="일부 대시보드 조회", notes="대시보드 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!!"),
            @ApiResponse(code = 500, message = "Internal Server Error!!"),
            @ApiResponse(code = 404, message = "Not Found!!")
    })
    @PostMapping("/api/v2/users")
    public CreateUserResponse saveUserV2(@RequestBody @Valid CreateUserRequest request){
        User user = new User(request.getPersonalId(),
                request.getPassword(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getBirthday(),
                request.getGender());

        Long id = userService.join(user);

        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserRequest {
        @NotEmpty
        private String personalId;
        private String password;
        private String name;
        private String phone;
        private String email;
        private LocalDate birthday;
        private Gender gender;

    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }
}
