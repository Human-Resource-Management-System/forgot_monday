package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Employee;
import models.EntityForgotPassword;

@Repository
public class ForgotPasswordDAOImpl {
	private final Logger logger = LoggerFactory.getLogger(ForgotPasswordDAOImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public boolean checkEmailExists(String email) {
		logger.info("checkEmailExists() method called with email: {}", email);

		String query = "SELECT COUNT(e) FROM Employee e WHERE e.emplOffemail = :email";
		Long count = entityManager.createQuery(query, Long.class).setParameter("email", email).getSingleResult();
		logger.info("Email exists check completed. Result");

		return count > 0;
	}

	/*
	 * public EntityForgotPassword findByEmail(String email) { return entityManager.find(EntityForgotPassword.class,
	 * email); }
	 */
	@Transactional
	public boolean findEmail(String email) {
		logger.info("findEmail() method called with email: {}", email);

		System.out.println(email + "email at dao");

		String query = "SELECT COUNT(e) FROM EntityForgotPassword e WHERE e.mail = :email";
		Long count = entityManager.createQuery(query, Long.class).setParameter("email", email).getSingleResult();
		logger.info("Searching for email in EntityForgotPassword table");

		return count > 0;
	}

	@Transactional
	public void save(EntityForgotPassword otpEntity) {
		logger.info("save() method called to save OTP entity: {}", otpEntity);

		entityManager.persist(otpEntity);
		logger.info("OTP entity saved successfully");

	}

	@Transactional
	public void update(EntityForgotPassword otpEntity) {
		logger.info("update() method called to update OTP entity: {}", otpEntity);

		entityManager.merge(otpEntity);
		logger.info("OTP entity updated successfully");

	}

	@Transactional
	public String validateOtp(String email) {
		logger.info("validateOtp() method called with email: {}", email);

		String query = "SELECT otp FROM EntityForgotPassword WHERE mail = :email";
		TypedQuery<String> typedQuery = entityManager.createQuery(query, String.class);
		typedQuery.setParameter("email", email);

		try {
			logger.info("OTP validation result");

			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			logger.info("No OTP found for the given email");

			return "-1"; // Return -1 if no OTP is found for the given email
		}
	}

	@Transactional
	public void updatePassword(Employee user) {
		logger.info("updatePassword() method called with user: {}", user);

		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.emplOffemail = :email",
				Employee.class);
		query.setParameter("email", user.getEmplOffemail());
		List<Employee> employees = query.getResultList();
		if (!employees.isEmpty()) {
			Employee employee = (employees.get(0));
			employee.setPassword(user.getPassword());
			entityManager.merge(employee);
			logger.info("Employee password updated successfully");

		} else {
			logger.error("Employee with email {} not found. IllegalArgumentException thrown.", user.getEmplOffemail());

			// Handle the case when no employee with the given email exists
			throw new IllegalArgumentException("Employee with email " + user.getEmplOffemail() + " not found.");
		}
	}
}
