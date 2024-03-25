package com.kawshikbuet17.blog;

import com.kawshikbuet17.blog.config.AppConstants;
import com.kawshikbuet17.blog.entities.Role;
import com.kawshikbuet17.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringbootBlogAppApiApplication implements CommandLineRunner {
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(("Hash of `password`: " + passwordEncoder().encode("password")));

		try{
			Role adminRole = new Role();
			adminRole.setId(AppConstants.ADMIN_USER);
			adminRole.setName("ADMIN_USER");

			Role normalRole = new Role();
			normalRole.setId(AppConstants.NORMAL_USER);
			normalRole.setName("NORMAL_USER");

			List<Role> roles = List.of(adminRole, normalRole);
			List<Role> savedRoles = this.roleRepo.saveAll(roles);

			savedRoles.forEach(r->{
				System.out.println("Inserted in DB: " + r.getId()+"-"+r.getName());
			});
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
