package biz.touchtechnologies.backendchallanege.application.service;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;
import biz.touchtechnologies.backendchallanege.application.dto.auth.AuthRequest;
import biz.touchtechnologies.backendchallanege.application.dto.auth.TokenJWT;

import java.util.Optional;

public interface AuthUserService {

    User save(SaveUser saveDTO);

    Optional<User> findOneByUsername(String username);

    TokenJWT login(AuthRequest authRequest);

    TokenJWT validate(String token);

}
