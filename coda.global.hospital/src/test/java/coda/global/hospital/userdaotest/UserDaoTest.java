package coda.global.hospital.userdaotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import coda.global.hospital.constants.UserTestConstants;
import coda.global.hospital.dao.UserDao;
import coda.global.hospital.model.User;

public class UserDaoTest {
	Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Test
	public void getUserTestValidUsername() {
		try {
			UserDao userDao = new UserDao();
			User user = userDao.getUser(UserTestConstants.validUsername);
			assertEquals(user.getFirstName(), UserTestConstants.validfirstname);
		} catch (Exception error) {
			logger.error(error.getMessage());
		}
	}
	@Test
	public void getUserTestInvalidUsername() {
		try {
			UserDao userDao = new UserDao();
			User user = userDao.getUser(UserTestConstants.invalidUsername);
			assertEquals(user, null);
		} catch (Exception error) {
			logger.error(error.getMessage());
		}
	}

}
