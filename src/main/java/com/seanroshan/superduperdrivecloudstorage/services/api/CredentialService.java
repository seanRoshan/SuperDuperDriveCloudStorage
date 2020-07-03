package com.seanroshan.superduperdrivecloudstorage.services.api;

import com.seanroshan.superduperdrivecloudstorage.backend.dao.CredentialDAO;
import com.seanroshan.superduperdrivecloudstorage.model.Credential;
import com.seanroshan.superduperdrivecloudstorage.services.utils.EncryptionService;
import com.seanroshan.superduperdrivecloudstorage.services.utils.HashService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialDAO credentialDAO;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialDAO credentialDAO, EncryptionService encryptionService) {
        this.credentialDAO = credentialDAO;
        this.encryptionService = encryptionService;
    }

    public Credential getCredential(int userId, int credentialId) {
        return credentialDAO.getCredential(userId, credentialId);
    }

    public List<Credential> listCredentials(int userId) {
        List<Credential> credentials = credentialDAO.getCredentialsByUserId(userId);
        for (Credential credential : credentials) {
            credential.setUnEncryptedPassword(this.encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentials;
    }

    public boolean addNewCredential(Credential credential) {
        encryptPassword(credential);
        return credentialDAO.addNewCredential(credential);
    }

    public boolean deleteCredential(int userId, int credentialId) {
        return credentialDAO.deleteCredential(userId, credentialId);
    }

    public boolean updateCredential(Credential credential) {
        encryptPassword(credential);
        return credentialDAO.updateCredential(credential);
    }

    private void encryptPassword(Credential credential) {
        String key = HashService.getEncodedSalt();
        String hashedPassword = this.encryptionService.encryptValue(credential.getUnEncryptedPassword(), key);
        credential.setKey(key);
        credential.setPassword(hashedPassword);
    }


}
