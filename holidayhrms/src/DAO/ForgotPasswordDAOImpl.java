package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Employee;
import models.EntityForgotPassword;

@Repository
public class ForgotPasswordDAOImpl {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public boolean checkEmailExists(String email) {
		String query = "SELECT COUNT(e) FROM Employee e WHERE e.emplOffemail = :email";
		Long count = entityManager.createQuery(query, Long.class).setParameter("email", email).getSingleResult();
		return count > 0;
	}

	/*
	 * public EntityForgotPassword findByEmail(String email) { return entityManager.find(EntityForgotPassword.class,
	 * email); }
	 */
	@Transactional
	public boolean findEmail(String email) {
		System.out.println(email + "email at dao");

		String query = "SELECT COUNT(e) FROM EntityForgotPassword e WHERE e.mail = :email";
		Long count = entityManager.createQuery(query, Long.class).setParameter("email", email).getSingleResult();
		return count > 0;
	}

	@Transactional
	public void save(EntityForgotPassword otpEntity) {
		entityManager.persist(otpEntity);
	}

	@Transactional
	public void update(EntityForgotPassword otpEntity) {
		entityManager.merge(otpEntity);
	}

	@Transactional
	public String validateOtp(String email) {
		String query = "SELECT otp FROM EntityForgotPassword WHERE mail = :email";
		TypedQuery<String> typedQuery = entityManager.createQuery(query, String.class);
		typedQuery.setParameter("email", email);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return "-1"; // Return -1 if no OTP is found for the given email
		}
	}

	@Transactional
	public void updatePassword(Employee user) {
		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.emplOffemail = :email",
				Employee.class);
		query.setParameter("email", user.getEmplOffemail());
		List<Employee> employees = query.getResultList();
		if (!employees.isEmpty()) {
			Employee employee = (employees.get(0));
			employee.setPassword(user.getPassword());
			entityManager.merge(employee);
		} else {
			// Handle the case when no employee with the given email exists
			throw new IllegalArgumentException("Employee with email " + user.getEmplOffemail() + " not found.");
		}
	}
}
