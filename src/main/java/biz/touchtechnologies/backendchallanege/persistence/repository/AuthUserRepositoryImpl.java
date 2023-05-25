package biz.touchtechnologies.backendchallanege.persistence.repository;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;
import biz.touchtechnologies.backendchallanege.application.repository.AuthUserRepository;
import biz.touchtechnologies.backendchallanege.persistence.entity.UserEntity;
import biz.touchtechnologies.backendchallanege.persistence.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class AuthUserRepositoryImpl implements AuthUserRepository {

    private static final List<UserEntity> USERS = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    private static long ID_COUNTER = 0;

    public AuthUserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void initUserList(){

        UserEntity user1 = new UserEntity();
        user1.setId(++ID_COUNTER);
        user1.setEmail("email.user@gmail.com");
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("user1pass"));
        user1.setName("Luis");
        user1.setPhone("+503 76750229");
        user1.setRole("ROLE_USER");

        UserEntity admin1 = new UserEntity();
        admin1.setId(++ID_COUNTER);
        admin1.setEmail("email.admin@gmail.com");
        admin1.setUsername("admin1");
        admin1.setPassword(passwordEncoder.encode("admin1pass"));
        admin1.setName("Guillermo");
        admin1.setPhone("+503 76886013");
        admin1.setRole("ROLE_ADMIN");

        USERS.add(user1);
        USERS.add(admin1);

    }

    @Override
    public User save(SaveUser saveDTO) {

        UserEntity user = UserMapper.toEntity(saveDTO);
        user.setId(++ID_COUNTER);
        USERS.add(user);

        return UserMapper.toDomainDTO(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        if(username == null) return Optional.empty();

        return USERS.stream().filter(user -> user.getUsername().toLowerCase(Locale.ROOT).equals(username.toLowerCase()))
                .findFirst()
                .map(UserMapper::toDomainDTO);

    }

}
