package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsersService() {
        return userRepository.findAll();
    }

    public User getUserService(Integer id) {
        return userRepository.findById(id).orElseGet(User::new);
    }

    public ApiResponse addUSer(UserDto userDto) {
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if(existsByEmail)
            return new ApiResponse("This email already exists", false);
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User added!", true);
    }

    public ApiResponse editUserService(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            return new ApiResponse("User not found!", false);
        boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if(existsByEmailAndIdNot)
            return new ApiResponse("This email already exists!", false);
        User user = optionalUser.get();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return new ApiResponse("User edited!",true);
    }

    public ApiResponse deleteUserService(Integer id) {
        boolean existsById = userRepository.existsById(id);
        if(!existsById)
            return new ApiResponse("User not found!", false);
        userRepository.deleteById(id);
        return new ApiResponse("User deleted!", true);
    }
}
