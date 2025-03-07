package com.example.userservice.service.user;

import com.example.userservice.data.entity.Country;
import com.example.userservice.data.entity.User;
import com.example.userservice.data.repository.CountryRepository;
import com.example.userservice.data.repository.UserRepository;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.web.model.UserReqModel;
import com.example.userservice.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public User findById(Long id) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserSimpleRespModel> findByName(String name) {
        return userRepository.findAllByName(name)
                .stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserSimpleRespModel> findByCountry(String term) {
        return userRepository.findAllByCountryTerm(term)
                .stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserSimpleRespModel createUser(UserReqModel user) {
        Country country = countryRepository.findByCode(user.countryCode()).orElse(null);

        if (country == null) {
            throw new IllegalArgumentException("Country with code " + user.countryCode() + " does not exist");
        }

        User buildedUser = User.builder()
                .name(user.name())
                .country(country)
                .build();

        userRepository.save(buildedUser);

        return UserSimpleRespModel.builder().name(user.name()).countryName(country.getName()).build();

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
