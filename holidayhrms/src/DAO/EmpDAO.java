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

}