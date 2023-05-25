package biz.touchtechnologies.backendchallanege.web.auth;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.ResponseDto;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;
import biz.touchtechnologies.backendchallanege.application.dto.auth.AuthRequest;
import biz.touchtechnologies.backendchallanege.application.dto.auth.TokenJWT;
import biz.touchtechnologies.backendchallanege.application.exception.BadRequestException;
import biz.touchtechnologies.backendchallanege.application.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody AuthRequest authRequest){

        TokenJWT token = authUserService.login(authRequest);

        return Optional.ofNullable(token)
                .map(t -> new ResponseDto(token))
                .map(rsp -> ResponseEntity.ok(rsp))
                .orElseThrow(() -> new BadRequestException("Username or password is invalid. Try again!"));

    }

    @PostMapping("/validate")
    public ResponseEntity<ResponseDto> validate(@RequestParam String token){
        TokenJWT tokenDTO = authUserService.validate(token);

        return Optional.ofNullable(tokenDTO)
                .map(t -> new ResponseDto(token))
                .map(rsp -> ResponseEntity.ok(rsp))
                .orElseThrow(() -> new BadRequestException("Token is invalid. Try again!"));

    }

    @PostMapping("/create-user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody SaveUser saveDTO){

        User userDTO = authUserService.save(saveDTO);

        return Optional.ofNullable(userDTO)
                .map(user -> new ResponseDto(user))
                .map(rsp -> ResponseEntity.status(HttpStatus.CREATED.value()).body(rsp))
                .orElseThrow(() -> new RuntimeException("User can't be saved right now! try again later!"));

    }

}
