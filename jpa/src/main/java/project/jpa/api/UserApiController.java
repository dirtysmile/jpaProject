package project.jpa.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 API")

public class UserApiController {

    private final UserService userService;




    @GetMapping("/api/v1/users/{id}")
    public UserDto2 SearchUserV1(@PathVariable("id") Long id){
        User user = userService.findUser(id);
        UserDto2 result = new UserDto2(user);
        return result;
    }

    @Data
    @AllArgsConstructor
    static class UserDto2{
        Long id;
        String personalId;
        String name;
        LocalDate birthday;

        UserDto2(User user){
            this.id = user.getId();
            this.personalId = user.getPersonalId();
            this.name = user.getName();
            this.birthday = user.getBirthday();
        }
    }

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
    @Operation(summary = "사용자 추가", description = "사용자를 추가 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공") ,
            @ApiResponse(responseCode = "500",
                    description = "1.중복된 사용자 입니다. \t\n 2.비밀 번호를 확인해 주십시오.",
                    content = @Content
            ) ,
    })
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

    @GetMapping("/api/v3/users/list")
    public Page<User> searchUserPagingV1(UserSearchCondition condition, Pageable pageable){

        System.out.println("hi");
        return userService.findUserPaging(condition,pageable);

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


//    @Schema(description = "사용자")
    @Data
    static class CreateUserRequest {
//        @Pattern(regexp = "[ (?i)^(?=.*[a-z])[a-z0-9]{8,20}$ ]")
//        @Schema(description = "유저 ID")
        private String personalId;

//        @Schema(description = "비밀번호")
        private String password;

//        @Schema(description = "이름")
        private String name;

//        @Schema(description = "전화번호")
        private String phone;

//        @Email
//        @Schema(description = "이메일", nullable = false, example = "abc@navee.com")
        private String email;

//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        @Schema(description = "생년월일", example = "yyyy-MM-dd")
        private LocalDate birthday;

//        @Schema(description = "성별", defaultValue = "male")
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
