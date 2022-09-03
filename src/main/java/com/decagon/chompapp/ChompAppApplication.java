package com.decagon.chompapp;

import com.decagon.chompapp.enums.Gender;
import com.decagon.chompapp.exceptions.ResourceNotFoundException;
import com.decagon.chompapp.models.Role;
import com.decagon.chompapp.models.User;
import com.decagon.chompapp.repositories.RoleRepository;
import com.decagon.chompapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@SpringBootApplication
public class ChompAppApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ChompAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception{

        Optional<Role> roles = roleRepository.findByName("ROLE_ADMIN");

        if(roles.isEmpty()){
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_PREMIUM");
            roleRepository.save(userRole);
        }

        Optional<User> newUser = userRepository.findByEmail("admin@gmail.com");

        if(newUser.isEmpty()){
            Set<Role> userAdminRoles = new HashSet<>();
            Role userAdminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(()-> new ResourceNotFoundException("ROLE_ADMIN not found"));
            userAdminRoles.add(userAdminRole);
            User user = new User();
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setUsername("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword("1234");
            user.setGender(Gender.MALE);
            user.setIsEnabled(true);
            user.setRoles(Collections.singleton(userAdminRole));
            userRepository.save(user);
        }

    }

//    @Override
//    public ResponseEntity<String> adminUser(String... args) throws Exception{
//
//    }
}


