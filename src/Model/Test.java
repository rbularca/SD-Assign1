package Model;
import Model.*;


import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Test {
	private static SessionFactory factory;


	public static void main(String[] args)
	{
		try{
			factory = UserRepository.createSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Test test = new Test();
		
		UsersEntity newUser = new UsersEntity("New User", "NewUser", "newpassword", "address", "DoB", "phoneNr", "Normal", null);
		UsersEntity modifiedUser = new UsersEntity("Newer User", "NewerUser", "newpassword1", "newaddress", "DoB", "phoneNr", "Normal", null);
		
		//test.deleteUser(3);
		//test.modifyUser(3, modifiedUser);
		//test.AddUser(newUser);
		//test.Login();
		//test.listUsers();
	}

	private void listUsers(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List users = session.createQuery("FROM UsersEntity").list();
			for(Iterator iterator = users.iterator();iterator.hasNext();){
				UsersEntity user = (UsersEntity) iterator.next();
				System.out.print("Name: " + user.getName());
				System.out.print(" Type: " + user.getType());
				System.out.println(" Address:" + user.getAddress());
			}
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	private void Login(){
		IUserRepository repo = new UserRepository();
		System.out.println(repo.login("Woof", "bla"));
		System.out.println(repo.login("Raul", "asdf"));
		System.out.println(repo.login("User", "password"));
	}
	
	private void AddUser(UsersEntity newUser)
	{
		IUserRepository repo = new UserRepository();
		repo.addUser(newUser);
	}
	
	private void modifyUser(int userId, UsersEntity modifiedUser) {
		IUserRepository repo = new UserRepository();
		repo.editUser(userId, modifiedUser);
	}

	private void deleteUser(int userId) {
		IUserRepository repo = new UserRepository();
		repo.deleteUser(userId);
	}


}

