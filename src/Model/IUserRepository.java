package Model;

import java.util.List;

public interface IUserRepository {

	 public int login(String username, String password);
	 public void addUser(UsersEntity user);
	 public void editUser(int userId, UsersEntity updatedUser);
	 public void deleteUser(int userId);
	 public UsersEntity getUser(int userId);
	 public List<UsersEntity> getAllUsers();
}
