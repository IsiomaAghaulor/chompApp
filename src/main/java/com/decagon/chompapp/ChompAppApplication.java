package com.decagon.chompapp;

import com.decagon.chompapp.models.Role;
import com.decagon.chompapp.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ChompAppApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ChompAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception{
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_PREMIUM");
        roleRepository.save(userRole);
    }
}


