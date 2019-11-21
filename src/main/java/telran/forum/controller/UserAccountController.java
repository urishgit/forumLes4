package telran.forum.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.forum.dto.UserEditDto;
import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;
import telran.forum.service.UserAccountService;

@RestController
@RequestMapping("/account")
public class UserAccountController {
	@Autowired
	UserAccountService userAccountService;
	
	@PostMapping("/user")
	public UserProfileDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return userAccountService.register(userRegisterDto);
	}
	
	@PostMapping("/login")
	public UserProfileDto login(@RequestHeader("Authorization") String token, Principal principal) {
		System.out.println(principal.getName());
		return userAccountService.login(token);
	}
	
	@DeleteMapping("/user")
	public UserProfileDto removeUser(@RequestHeader("Authorization") String token) {
		return userAccountService.removeUser(token);
	}
	
	@PutMapping("/user")
	public UserProfileDto editUser(@RequestBody UserEditDto userEditDto, @RequestHeader("Authorization") String token) {
		return userAccountService.editUser(token, userEditDto);
	}
	
	@PostMapping("/user/{login}/role/{role}")
	public Set<String> addRole(@PathVariable String login, @PathVariable String role, @RequestHeader("Authorization") String token){
		return userAccountService.addRole(login, role, token);
	}
	
	@DeleteMapping("/user/{login}/role/{role}")
	public Set<String> removeRole(@PathVariable String login, @PathVariable String role, @RequestHeader("Authorization") String token){
		return userAccountService.removeRole(login, role, token);
	}
	
	@PutMapping("/user/password")
	public void changePassword(@RequestHeader("Authorization") String token, @RequestHeader("X-Password") String password) {
		userAccountService.changePassword(token, password);
	}
	


}
