package com.hurynovich.prog_lang_tests.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;

@Repository("testDao")
public class TestDAOHibernateImpl implements ITestDAO {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// Create methods
	@Override
	public void addLang(Lang lang) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(lang);
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public void addTest(Test test) {
		Session session = null;
		try {
			test.getQuestions().forEach(question -> {
				question.setTest(test);
				question.getAnswers().forEach(answer -> answer.setQuestion(question));
			});
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(test);
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Read methods
	@Override
	public List<Lang> getAllLangs() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query<Lang> query = session.createQuery("FROM Lang", Lang.class);
			List<Lang> langs = query.getResultList();
			if (langs.isEmpty()) {
				return null;
			} else {
				return langs;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Test getTestById(int testId) {
		EntityGraph<?> graph = entityManager.getEntityGraph("completeTestGraph");
		
		Map<String, Object> hints = new HashMap<>();
	    hints.put("javax.persistence.loadgraph", graph);

	    return entityManager.find(Test.class, testId, hints);
	}
	
	@Override
	public List<Test> getTests(int langId, TestDifficulty difficulty) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query<Test> query = 
				session.createQuery("FROM Test WHERE langId = :li AND difficulty = :d", 
				Test.class);
			query.setParameter("li", langId);
			query.setParameter("d", difficulty);
			List<Test> tests = query.getResultList();
			if (tests.isEmpty()) {
				return null;
			} else {
				return tests;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
