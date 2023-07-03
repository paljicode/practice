package com.met.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.met.model.Employee;

@Repository
@Transactional
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}

	public Employee findByUsernameAndPassword(String id, String password) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("password", password));
		return (Employee) criteria.uniqueResult();
	}

	public Employee findById(String id) {
	    Session session = sessionFactory.getCurrentSession();
	    return (Employee) session.get(Employee.class,id);
	}


	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query createQuery = session.createQuery("from com.met.model.Employee");

			employees = createQuery.list();

		} finally {
			if (session != null)
				session.close();
		}
		return employees;
	}

}
