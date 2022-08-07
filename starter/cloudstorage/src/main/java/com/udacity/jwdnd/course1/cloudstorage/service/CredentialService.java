package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public CredentialForm[] getCredentialList(Integer userId) {

        Credential[] credentials = credentialMapper.getCredentialList(userId);
        CredentialForm[] credentialForms = new CredentialForm[credentials.length];

        for(int i = 0; i < credentials.length; i++) {
            Credential credential = credentials[i];

            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());

            credentialForms[i] = new CredentialForm(credential.getCredentialId().toString(), credential.getUrl(), credential.getUsername(), decryptedPassword);
        }

        return credentialForms;
    }

    public int addCredential(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public int deleteCredential(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public int updateCredential(Integer credentialId, String url, String username, String password, String key) {
        return credentialMapper.update(credentialId, url, username, password, key);
    }

}
