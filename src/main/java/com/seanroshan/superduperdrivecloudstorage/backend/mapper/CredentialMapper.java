package com.seanroshan.superduperdrivecloudstorage.backend.mapper;


import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.Credential;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Contains Mapper methods for Credential Service
 *
 * @author Sean Roshan
 */
@Mapper
public interface CredentialMapper {


    /**
     * Gets credentials by userId
     *
     * @param userId - userId
     * @return {@link List <Credential>}
     */
    @Select("SELECT * FROM CREDENTIALS WHERE USER_ID = #{userId}")
    List<Credential> getCredentialsByUserId(@Param(ParameterConstants.USER_ID) int userId);


    /**
     * Gets credential
     *
     * @param credentialId - credentialId
     * @param userId       - userId
     * @return {@link Note}
     */
    @Select("SELECT * FROM CREDENTIALS WHERE "
            + " USER_ID = #{userId} "
            + " AND CREDENTIAL_ID = #{credentialId} "
            + " LIMIT 1")
    Credential getCredential(@Param(ParameterConstants.USER_ID) int userId,
                             @Param(ParameterConstants.CREDENTIAL_ID) int credentialId);


    /**
     * Insert a new credential
     *
     * @param credential - credential
     * @return {@link Integer}
     */
    @Insert("INSERT INTO CREDENTIALS ( URL, USER_NAME, KEY, PASSWORD, USER_ID ) "
            + " VALUES ( #{url}, #{userName}, #{key}, #{password}, #{userId} )")
    @Options(useGeneratedKeys = true, keyColumn = "CREDENTIAL_ID", keyProperty = "credentialId")
    int insertNewCredential(Credential credential);


    /**
     * Delete credential
     *
     * @param credentialId - credentialId
     * @param userId       - userId
     * @return {@link Integer}
     */
    @Delete("DELETE FROM CREDENTIALS WHERE "
            + " USER_ID = #{userId} "
            + " AND CREDENTIAL_ID = #{credentialId} ")
    int deleteCredential(@Param(ParameterConstants.USER_ID) int userId,
                         @Param(ParameterConstants.CREDENTIAL_ID) int credentialId);


    /**
     * Update credential
     *
     * @param credential - credential
     * @return {@link Integer}
     */
    @Update("UPDATE CREDENTIALS SET "
            + " URL = #{url}, "
            + " USER_NAME = #{userName}, "
            + " KEY = #{key}, "
            + " PASSWORD = #{password} "
            + " WHERE USER_ID = #{userId} AND CREDENTIAL_ID = #{credentialId} ")
    int updateCredential(Credential credential);


}
