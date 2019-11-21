package telran.forum.service;

import java.util.Set;

import telran.forum.dto.UserEditDto;
import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;

public interface UserAccountService {
	
	UserProfileDto register(UserRegisterDto userRegisterDto);
	
	UserProfileDto login(String token);
	
	UserProfileDto editUser(String token, UserEditDto userEditDto);
	
	UserProfileDto removeUser(String token);
	
	void changePassword(String token, String password);
	
	Set<String> addRole(String login, String role, String token);
	
	Set<String> removeRole(String login, String role, String token);

}
