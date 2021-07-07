package project.jpa.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;


    @GetMapping("/api/v1/users")
    public List<User> userV1(){
        return userService.findUsers();
    }

    @GetMapping("/api/v2/users")
    public Result userV2(){
        List<User> users = userService.findUsers();
        List<UserDto> collect = users.stream()
                .map(m -> new UserDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

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
