package biz.touchtechnologies.backendchallanege.application.repository;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;

import java.util.Optional;

public interface AuthUserRepository {

    User save(SaveUser saveDTO);

    Optional<User> findOneByUsername(String username);

}
