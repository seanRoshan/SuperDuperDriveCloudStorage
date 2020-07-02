package com.seanroshan.superduperdrivecloudstorage.backend.mapper;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.MapperParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import org.apache.ibatis.annotations.*;


/**
 * Contains Mapper methods for User Service
 *
 * @author Sean Roshan
 */
@Mapper
public interface UserMapper {


    /**
     * Gets user by userId
     *
     * @param userId - userId
     * @return {@link User}
     */
    @Select("SELECT * FROM USERS WHERE USER_ID = #{userId}")
    User getUserByUserId(@Param(MapperParameterConstants.USER_ID) int userId);

    /**
     * Is User Exists
     *
     * @param userName - userName
     * @return {@link Boolean}
     */
    @Select("SELECT EXISTS ( "
            + " SELECT USER_ID FROM USERS WHERE USER_NAME = #{userName} "
            + " )")
    boolean isUserExists(@Param(MapperParameterConstants.USER_NAME) String userName);


    /**
     * Get User
     *
     * @param userName - userName
     * @return {@link User}
     */
    @Select("SELECT * FROM USERS WHERE USER_NAME = #{userName}")
    User getUser(@Param(MapperParameterConstants.USER_NAME) String userName);


    /**
     * Insert new User
     *
     * @param user - user
     * @return {@link Integer}
     */
    @Insert("INSERT INTO USERS (FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD , SALT) "
            + " VALUES (#{firstName}, #{lastName}, #{userName}, #{password}, #{salt}) ")
    @Options(useGeneratedKeys = true, keyColumn = "USER_ID", keyProperty = "userId")
    int insertNewUser(User user);


}
