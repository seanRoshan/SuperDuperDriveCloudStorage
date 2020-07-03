package com.seanroshan.superduperdrivecloudstorage.services.api;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.backend.dao.UserDAO;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.utils.HashService;
import com.seanroshan.superduperdrivecloudstorage.utils.ValidationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final HashService hashService;

    public UserService(HashService hashService) {
        this.hashService = hashService;
    }

    @Autowired
    UserDAO userDAO;


    /**
     * Is User Exists
     *
     * @param userName - userName
     * @return {@link Integer}
     */
    public boolean isUserExists(String userName) {
        LOGGER.traceEntry(userName);

        boolean userExists = userDAO.isUserExists(userName);

        LOGGER.traceExit(userExists);
        return userExists;
    }


    /**
     * Get User
     *
     * @param userName - userName
     * @return {@link Integer}
     */
    public User getUser(String userName) {
        LOGGER.traceEntry(userName);

        User user = userDAO.getUser(userName);

        LOGGER.traceExit(user);
        return user;
    }

    /**
     * Register new User
     *
     * @param user - user
     * @return {@link Integer}
     */
    public boolean registerUser(User user) {
        LOGGER.traceEntry(user.toString());

        //1. Hash password
        String encodedSalt = HashService.getEncodedSalt();
        String hashedPassword = this.hashService.getHashedValue(user.getPassword(), encodedSalt);

        //2. Update user data
        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);

        boolean isRegistered = userDAO.registerUser(user);
        LOGGER.traceExit(isRegistered);
        return isRegistered;
    }


    /**
     * Verify User
     *
     * @param user - user
     * @return {@link String}
     */
    public BusinessError verifyUser(User user) {

        if (user == null) {
            return BusinessError.REQUIRED_FIELDS;
        }

        if (!ValidationUtils.validateString(user.getFirstName(), true, 1, 20)) {
            return BusinessError.INVALID_FIRST_NAME;
        }

        if (!ValidationUtils.validateString(user.getLastName(), true, 1, 20)) {
            return BusinessError.INVALID_LAST_NAME;
        }

        if (!ValidationUtils.validateString(user.getUserName(), true, 1, 20)) {
            return BusinessError.INVALID_USER_NAME;
        }

        if (!ValidationUtils.validateString(user.getPassword(), true, 8, 20)) {
            return BusinessError.INVALID_PASSWORD;
        }

        if (isUserExists(user.getUserName())) {
            return BusinessError.USER_NAME_EXISTS;
        }

        return null;
    }

}
