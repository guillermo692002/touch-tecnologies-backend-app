package biz.touchtechnologies.backendchallanege.application.service.impl;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;
import biz.touchtechnologies.backendchallanege.application.dto.auth.AuthRequest;
import biz.touchtechnologies.backendchallanege.application.dto.auth.TokenJWT;
import biz.touchtechnologies.backendchallanege.application.repository.AuthUserRepository;
import biz.touchtechnologies.backendchallanege.application.service.AuthUserService;
import biz.touchtechnologies.backendchallanege.config.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, biz.touchtechnologies.backendchallanege.config.security.JwtTokenProvider jwtTokenProvider) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User save(SaveUser saveDTO) {
        saveDTO.setPassword(passwordEncoder.encode(saveDTO.getPassword()));
        return authUserRepository.save(saveDTO);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return authUserRepository.findOneByUsername(username);
    }

    @Override
    public TokenJWT login(AuthRequest authRequest) {
        Optional<User> user = authUserRepository.findOneByUsername(authRequest.getUsername());

        if(!user.isPresent()){
            return null;
        }

        if(passwordEncoder.matches(authRequest.getPassword(), user.get().getPassword() )){
            TokenJWT token = new TokenJWT();
            token.setToken(jwtTokenProvider.createToken(user.get()));
            return token;
        }
        return null;

    }

    @Override
    public TokenJWT validate(String token) {
        if(!jwtTokenProvider.validateToken(token)){
            return null;
        }

        String username = jwtTokenProvider.getUsernameFromToken(token);

        if(!authUserRepository.findOneByUsername(username).isPresent()){
            return null;
        }

        return new TokenJWT(token);
    }
}
