package com.seanroshan.superduperdrivecloudstorage.backend.dao;

import com.seanroshan.superduperdrivecloudstorage.backend.mapper.UserMapper;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contains DAO methods for User Service
 *
 * @author Sean Roshan
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@Component
public class UserDAO extends TransactionHandlerDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserMapper userMapper;

    /**
     * Gets user by userId
     *
     * @param userId - userId
     * @return {@link User}
     */
    public User getUserByUserId(int userId) {
        LOGGER.traceEntry(Integer.toString(userId));
        User user = userMapper.getUserByUserId(userId);
        LOGGER.traceExit(user);
        return user;
    }

    /**
     * Is User Exists
     *
     * @param userName - userName
     * @return {@link Boolean}
     */
    public boolean isUserExists(String userName) {
        LOGGER.traceEntry(userName);
        boolean userExists = userMapper.isUserExists(userName);
        LOGGER.traceExit(userExists);
        return userExists;
    }

    /**
     * Get User
     *
     * @param userName - userName
     * @return {@link User}
     */
    public User getUser(String userName) {
        LOGGER.traceEntry(userName);
        User user = userMapper.getUser(userName);
        LOGGER.traceExit(user);
        return user;
    }


    /**
     * Register new User
     *
     * @param user - user
     * @return {@link Boolean}
     */
    public boolean registerUser(User user) {
        LOGGER.traceEntry(user.toString());
        boolean isRegistered = userMapper.insertNewUser(user) > 0;
        LOGGER.traceExit(isRegistered);
        return isRegistered;
    }

}
