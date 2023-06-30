package DAO;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}