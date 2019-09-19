package coda.global.hospital.services;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coda.global.hospital.constants.ErrorConstants;
import coda.global.hospital.dao.UserDao;
import coda.global.hospital.exception.InvalidPasswordException;
import coda.global.hospital.exception.UserNotFoundException;
import coda.global.hospital.model.User;

public class AuthenticationService {
	UserDao authentication = null;
	User user = null;
	Logger LOGGER;
	ResourceBundle errorConstant;

	public AuthenticationService() {
		LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
		errorConstant = ResourceBundle.getBundle("ErrorMessages");
	}

	public User validate(String username, String password) {
		try {
			UserDao authentication = new UserDao();
			user = authentication.getUser(username);
			if (user == null)
				throw new UserNotFoundException();
			else if (!(user.getPassword().equals(password)))
				throw new InvalidPasswordException();
			else
				return user;
		} catch (UserNotFoundException e) {

			LOGGER.error(errorConstant.getString(ErrorConstants.HM_ERROR_009));

		}catch (InvalidPasswordException e) {

			LOGGER.error(errorConstant.getString(ErrorConstants.HM_ERROR_010));

		}
		return null;
	}
}
