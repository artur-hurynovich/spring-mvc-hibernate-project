package com.hurynovich.prog_lang_tests.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hurynovich.prog_lang_tests.entity.User;

@Repository("userDao")
public class UserDAOHibernateImpl implements IUserDAO {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	// Create methods
	@Override
	public void addUser(User user) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Read methods
	@Override
	public User getUserByEmail(String email) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query<User> query = session.createQuery("FROM User WHERE email = :e", User.class);
			query.setParameter("e", email);
			List<User> users = query.getResultList();
			if (users.isEmpty()) {
				return null;
			} else {
				return users.get(0);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
