package pl.arcadeit.forex.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.arcadeit.forex.domain.User;

@Mapper
public interface UserDTO {
    
    UserModel userToUserModel(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "walletNumber", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userModelToUser(UserModel userModel);
}
