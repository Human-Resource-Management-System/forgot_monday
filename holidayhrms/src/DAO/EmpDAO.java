package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import models.Admin;
import models.Employee;

@Component
public class EmpDAO {

	@PersistenceContext
	private EntityManager em;

	public void persist(Employee emp) {
		em.persist(emp);
	}

	public List<Employee> findAll() {
		return em.createQuery("SELECT e FROM Employee e").getResultList();
	}

	@Transactional
	public Employee findUpdatableEmployee(int emp_id) {
		return em.find(Employee.class, emp_id);
	}

	@Transactional
	public Employee getDetailsByEmail(String emailId) {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.emplOffemail = :emailId",
				Employee.class);
		query.setParameter("emailId", emailId);
		System.out.println(emailId);
		return query.getSingleResult();
	}

	@Transactional
	public void updateEmployeeAddress(int empId, String newAddress) {
		Employee employee = em.find(Employee.class, empId);
		if (employee != null) {
			employee.setEmplAddress(newAddress);
			em.merge(employee);
		}
	}

	@Transactional
	public Admin getAdminDetailsById(int id) {
		TypedQuery<Admin> query = em.createQuery("SELECT e FROM Admin e WHERE e.ausr_empl_id = :Id", Admin.class);
		query.setParameter("Id", id);
		try {
			Admin a = query.getSingleResult();
			return a;
		} catch (NoResultException e) {
			return null;
		}
	}
	/*
	 * public boolean checkEmailExists(String email) { String query =
	 * "SELECT COUNT(e) FROM Employee e WHERE e.emplOffemail = :email"; Long count = em.createQuery(query,
	 * Long.class).setParameter("email", email).getSingleResult(); return count > 0; }
	 * 
	 * 
	 * public EntityForgotPassword findByEmail(String email) { return entityManager.find(EntityForgotPassword.class,
	 * email); }
	 * 
	 * 
	 * public boolean findEmail(String email) { System.out.println(email + "email at dao");
	 * 
	 * String query = "SELECT COUNT(e) FROM EntityForgotPassword e WHERE e.email = :email"; Long count =
	 * em.createQuery(query, Long.class).setParameter("email", email).getSingleResult(); return count > 0; }
	 * 
	 * public void save(EntityForgotPassword otpEntity) { em.persist(otpEntity); }
	 * 
	 * public void update(EntityForgotPassword otpEntity) { em.merge(otpEntity); }
	 */

}