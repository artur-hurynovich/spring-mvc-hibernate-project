package com.hurynovich.prog_lang_tests.validation.common_dao;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("commonDao")
public class CommonDAOHibernateImpl implements ICommonDAO {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public boolean valueExists(String tableName, String fieldName, String value) {
		Session session = null;
		String query = "SELECT COUNT(*) FROM " + tableName 
			+ " WHERE " + fieldName + " = '" + value + "'";
		try {
			session = sessionFactory.openSession();
			BigInteger count = (BigInteger) session.createSQLQuery(query).getSingleResult();
			return count.intValue() != 0;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
