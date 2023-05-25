package biz.touchtechnologies.backendchallanege.persistence.mapper;

import biz.touchtechnologies.backendchallanege.application.User;
import biz.touchtechnologies.backendchallanege.application.dto.SaveUser;
import biz.touchtechnologies.backendchallanege.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(SaveUser saveDTO){
        if(saveDTO == null) return null;

        UserEntity user = new UserEntity();
        user.setRole(saveDTO.getRole());
        user.setPassword(saveDTO.getPassword());
        user.setPhone(saveDTO.getPhone());
        user.setUsername(saveDTO.getUsername());
        user.setEmail(saveDTO.getEmail());
        user.setName(saveDTO.getFirstname().concat(" ").concat(saveDTO.getLastname()));

        return user;
    }

    public static User toDomainDTO(UserEntity entity){
        if(entity == null) return null;

        User user = new User();
        user.setRole(entity.getRole());
        user.setPassword(entity.getPassword());
        user.setPhone(entity.getPhone());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());

        if(entity.getName() != null && entity.getName().split(" ").length == 2){
            user.setFirstname(entity.getName().split(" ")[0]);
            user.setLastname(entity.getName().split(" ")[1]);
        }

        return user;
    }

}
