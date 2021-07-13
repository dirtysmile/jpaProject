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
import project.jpa.dto.CreateUserDto;
import project.jpa.dto.UpdateUserDto;
import project.jpa.dto.UserRequestDto;
import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "user", description = "사용자 API")

public class UserApiController {

    private final UserService userService;


    @PostMapping("/users")
    @Operation(summary = "사용자 추가", description = "사용자를 추가 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공") ,
            @ApiResponse(responseCode = "400", description = "비어 있는 값이 있습니다.") ,
            @ApiResponse(responseCode = "500",
                    description = "[110-90001] 중복된 사용자 입니다. \t\n [110-90002] 비밀 번호를 확인해 주십시오.",
                    content = @Content
            ) ,
    })
    public Long saveUser(@RequestBody @Valid CreateUserDto request){
        User user = new User(request.getPersonalId(),
                request.getPassword(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getBirthday(),
                request.getGender());

        Long id = userService.join(user);

        return id;
    }

    @GetMapping("/users")
    @Operation(summary = "사용자 전체 조회", description = "전체 사용자를 조합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    public Result findUsers(){
        List<User> users = userService.findUsers();
        List<UserRequestDto> collect = users.stream()
                .map(user -> new UserRequestDto(user))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "사용자 한명 조회", description = "특정 사용자를 조회합니다.")
    public UserRequestDto findUserById(@PathVariable("id") Long id){
        User user = userService.findUser(id);
        UserRequestDto result = new UserRequestDto(user);
        return result;
    }

    @PutMapping("/users")
    @Operation(summary = "사용자 업데이트", description = "사용자 정보 변경")
    public User UpdateUser(@RequestBody UpdateUserDto request){
        User user = new User(request.getId(),
                request.getPersonalId(),
                request.getPassword(),
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getBirthday(),
                request.getGender());

        User updateUser = userService.updateUser(user);
        return updateUser;
    }


// ========================


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



    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }
}
