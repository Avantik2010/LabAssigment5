package com.studentmanagment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studentmanagment.entity.student;
@Repository
public class StudentServiceImpl1 implements StudentService {

	private SessionFactory sessionfactory;
	private Session session;

	@Autowired
	public StudentServiceImpl1(SessionFactory sessionfactory) {
		super();
		this.sessionfactory = sessionfactory;
		try {
			session = sessionfactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionfactory.openSession();

		}
	}

	
	@Override
	@Transactional
	public List<student> findAll() {

		// TODO Auto-generated method stub

		Transaction transaction = session.beginTransaction();
		List<student> students = session.createQuery("from student").list();
		transaction.commit();

		return students;
	}

	

	@Override
	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		student student1 = session.get(student.class, theId);
		session.delete(student1);
		transaction.commit();

	}

	


	@Override
	@Transactional
	public student findById(int theId) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		student student1 = session.get(student.class, theId);
		//session.get(Student.class, session);
		transaction.commit();
		return student1;
	}


	@Override
	@Transactional
	public void save(student theStudent) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(theStudent);
		transaction.commit();

	}
}
