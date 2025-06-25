package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByEmailAndPswrd(String email, String pswrd);

	public UserEntity findByEmail(String email);
	

}
