package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import DAO.EmpDAO;
import DAO.ForgotPasswordDAOImpl;
import models.Admin;
import models.Employee;
import models.EntityForgotPassword;

@Component
public class EmployeeLoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoginService.class);

	private EmpDAO empdao;
	private ForgotPasswordDAOImpl forgotPassword;

	@Autowired
	EmployeeLoginService(ForgotPasswordDAOImpl forgotPassword, EmpDAO empdao) {
		this.forgotPassword = forgotPassword;
		this.empdao = empdao;
	}

	@Transactional
	public Employee getByEmail(String email) {
		LOGGER.info("getByEmail() method called with email: {}", email);

		return empdao.getDetailsByEmail(email);
	}

	@Transactional
	public boolean authenticateUser(String email, String password) {
		LOGGER.info("authenticateUser() method called with email: {}", email);

		Employee user = empdao.getDetailsByEmail(email);

		if (user != null && user.getPassword().equals(hashPassword(password))) {
			LOGGER.info("User authentication successful");

			return true;
		}
		LOGGER.info("User authentication failed");

		return false;
	}

	@Transactional
	public boolean authenticateUser_admin(String email, String password) {
		LOGGER.info("authenticateUser_admin() method called with email: {}", email);

		Employee user = empdao.getDetailsByEmail(email);

		int userid = user.getEmplId();

		Admin admin = empdao.getAdminDetailsById(userid);

		if (admin == null)
			return false;

		if (user != null && user.getPassword().equals(hashPassword(password))) {
			LOGGER.info("Admin authentication successful");

			return true;
		}
		LOGGER.info("Admin authentication failed");

		return false;
	}

	public Employee getEmployee(String email) {
		LOGGER.info("getEmployee() method called with email: {}", email);

		Employee user = empdao.getDetailsByEmail(email);
		return user;
	}

	public String hashPassword(String password) {
		LOGGER.info("hashPassword() method called");

		try {

			return HashGenerator.hashPassword(password);
		} catch (Exception e) {
			LOGGER.error("Exception occurred while hashing password: {}", e.getMessage());

			e.printStackTrace();
			// Handle the exception appropriately
		}

		return null;
	}

	@Transactional
	public void saveOrUpdate(EntityForgotPassword otpEntity) {
		LOGGER.info("saveOrUpdate() method called with otpEntity: {}", otpEntity);

		String email = otpEntity.getMail();
		System.out.println(otpEntity + "in service ");

		boolean emailExists = forgotPassword.findEmail(email);
		LOGGER.info("Checking if email exists: {}", emailExists);

		System.out.println(emailExists);
		if (emailExists) {
			// Email does not exist, return an error response return
			forgotPassword.update(otpEntity);
			LOGGER.info("Updated existing otpEntity");

		}

		else {

			forgotPassword.save(otpEntity);
			LOGGER.info("Saved new otpEntity");

		}
	}
}