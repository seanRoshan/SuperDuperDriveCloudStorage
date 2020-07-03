package com.seanroshan.superduperdrivecloudstorage.backend.dao;

import com.seanroshan.superduperdrivecloudstorage.backend.mapper.CredentialMapper;
import com.seanroshan.superduperdrivecloudstorage.model.Credential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Contains DAO methods for Credential Service
 *
 * @author Sean Roshan
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class CredentialDAO extends TransactionHandlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CredentialDAO.class);
    private final CredentialMapper credentialMapper;

    public CredentialDAO(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    /**
     * Gets credentials by userId
     *
     * @param userId - userId
     * @return {@link List<Credential>}
     */
    public List<Credential> getCredentialsByUserId(int userId) {
        LOGGER.traceEntry(Integer.toString(userId));
        List<Credential> credentials = credentialMapper.getCredentialsByUserId(userId);
        LOGGER.traceExit(credentials);
        return credentials;
    }


    /**
     * Gets credential
     *
     * @param credentialId - credentialId
     * @param userId       - userId
     * @return {@link Credential}
     */
    public Credential getCredential(int userId, int credentialId) {
        LOGGER.traceEntry(Integer.toString(userId), Integer.toString(credentialId));
        Credential credential = credentialMapper.getCredential(userId, credentialId);
        LOGGER.traceExit(credential);
        return credential;
    }


    /**
     * Insert a new credential
     *
     * @param credential - credential
     * @return {@link Boolean}
     */
    public boolean addNewCredential(Credential credential) {
        LOGGER.traceEntry(credential.toString());
        boolean isAdded = credentialMapper.insertNewCredential(credential) > 0;
        LOGGER.traceExit(isAdded);
        return isAdded;
    }


    /**
     * Delete credential
     *
     * @param credentialId - credentialId
     * @param userId       - userId
     * @return {@link Boolean}
     */
    public boolean deleteCredential(int userId, int credentialId) {
        LOGGER.traceEntry(Integer.toString(userId), Integer.toString(credentialId));
        boolean isDeleted = credentialMapper.deleteCredential(userId, credentialId) > 0;
        LOGGER.traceExit(isDeleted);
        return isDeleted;
    }


    /**
     * Update credential
     *
     * @param credential - credential
     * @return {@link Boolean}
     */
    public boolean updateCredential(Credential credential) {
        LOGGER.traceEntry(credential.toString());
        boolean isUpdated = credentialMapper.updateCredential(credential) > 0;
        LOGGER.traceExit(isUpdated);
        return isUpdated;
    }


}
