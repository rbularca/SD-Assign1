package Model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserRepository implements IUserRepository {
	private static SessionFactory factory;
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private final int FAILED_LOGIN = -1;
	private final int NORMAL_USER = 0;
	private final int ADMINISTRATOR = 1;
	
	public UserRepository()
	{
		factory = createSessionFactory(); 
	}
	
	@Override
	public int login(String username, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query QueryResult = session.createQuery("FROM UsersEntity"
					+ " where username = :username  AND password = :password");
			QueryResult.setString("username", username);
			QueryResult.setString("password", password);
			UsersEntity user = (UsersEntity)QueryResult.uniqueResult();
			if(user != null)
			{
				if(user.getType().equals("Administrator"))
					return ADMINISTRATOR;
				else 
					return NORMAL_USER;
			}
			else return FAILED_LOGIN;
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return FAILED_LOGIN;
	}

	@Override
	public void addUser(UsersEntity user) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userID = null;
		try{
			tx = session.beginTransaction();
			userID  = (Integer) session.save(user);
			user.setId(userID);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}

	@Override
	public void editUser(int userId, UsersEntity updatedUser) {
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			UsersEntity user = (UsersEntity)session.get(UsersEntity.class, userId);
			updateData(user, updatedUser);
			session.update(user);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}

	private void updateData(UsersEntity user, UsersEntity updatedUser) {
		user.setAddress(updatedUser.getAddress());
		user.setName(updatedUser.getName());
		user.setTelephoneNumber(updatedUser.getTelephoneNumber());
		user.setPassword(updatedUser.getPassword());
		user.setUsername(updatedUser.getUsername());
	}

	@Override
	public void deleteUser(int userId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			UsersEntity user = (UsersEntity)session.get(UsersEntity.class, userId);
			session.delete(user);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	@Override
	public UsersEntity getUser(int userId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			UsersEntity user = (UsersEntity)session.get(UsersEntity.class, userId);
			tx.commit();
			return user;
	}catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     }finally {
        session.close(); 
     }
		return null;
	}
	
	@Override
	public List<UsersEntity> getAllUsers() {
		List<UsersEntity> result = new ArrayList<UsersEntity>();
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List users = session.createQuery("FROM UsersEntity").list();
			for(Iterator iterator = users.iterator();iterator.hasNext();){
				result.add((UsersEntity)iterator.next());
			}
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return result;
	}
	
	static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
}
