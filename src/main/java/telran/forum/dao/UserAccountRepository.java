package telran.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.forum.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
