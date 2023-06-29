package DAO;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ForgotPasswordDAOImpl {

	@Autowired
	private EntityManager entityManager;

	public boolean checkEmailExists(String email) {
		String query = "SELECT COUNT(e) FROM Employee e WHERE e.emplOffemail = :email";
		Long count = entityManager.createQuery(query, Long.class).setParameter("email", email).getSingleResult();
		return count > 0;
	}
}