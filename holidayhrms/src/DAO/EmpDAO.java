package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import models.Admin;
import models.Employee;

@Component
public class EmpDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpDAO.class);

	@PersistenceContext
	private EntityManager em;

	public void persist(Employee emp) {
		LOGGER.info("persist() method called with employee: {}", emp);

		em.persist(emp);
	}

	public List<Employee> findAll() {
		LOGGER.info("findAll() method called");

		return em.createQuery("SELECT e FROM Employee e").getResultList();
	}

	@Transactional
	public Employee findUpdatableEmployee(int emp_id) {
		LOGGER.info("findUpdatableEmployee() method called with emp_id: {}", emp_id);
		LOGGER.info("findUpdatableEmployee() method processing ");

		return em.find(Employee.class, emp_id);
	}

	@Transactional
	public Employee getDetailsByEmail(String emailId) {
		LOGGER.info("getDetailsByEmail() method called with emailId: {}", emailId);

		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.emplOffemail = :emailId",
				Employee.class);
		query.setParameter("emailId", emailId);
		LOGGER.info("Searching for employee with email: {}", emailId);

		System.out.println(emailId);
		return query.getSingleResult();
	}

	@Transactional
	public void updateEmployeeAddress(int empId, String newAddress) {
		LOGGER.info("updateEmployeeAddress() method called with empId: {} and newAddress: {}", empId, newAddress);

		Employee employee = em.find(Employee.class, empId);
		if (employee != null) {
			LOGGER.info("Updating employee address to: {}", newAddress);

			employee.setEmplAddress(newAddress);
			em.merge(employee);
			LOGGER.info("Employee address updated successfully");

		}
	}

	@Transactional
	public Admin getAdminDetailsById(int id) {
		LOGGER.info("getAdminDetailsById() method called with id: {}", id);

		TypedQuery<Admin> query = em.createQuery("SELECT e FROM Admin e WHERE e.ausr_empl_id = :Id", Admin.class);
		query.setParameter("Id", id);
		try {
			Admin a = query.getSingleResult();
			LOGGER.info("Admin found: {}", a);

			return a;
		} catch (NoResultException e) {
			LOGGER.info("No admin found with id: {}", id);

			return null;
		}
	}

}