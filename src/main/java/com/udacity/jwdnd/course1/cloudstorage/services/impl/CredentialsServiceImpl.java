package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.repository.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialsMapper credentialsMapper;

    private final EncryptionServiceImpl encryptionService;

    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public Credentials decryptPassword(Credentials credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    @Override
    public List<Pair<String, Credentials>> getAllCredentials(int userid) throws Exception {
        List<Credentials> credentials = credentialsMapper.findByUserId(userid);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials.stream().map(credential ->
           Pair.of(credential.getPassword(), this.decryptPassword(credential))
        ).collect(Collectors.toList());
    }

    @Override
    public void addCredential(Credentials credential, int userid) {
        credentialsMapper.insertCredentials(encryptPassword(credential), userid);
    }

    @Override
    public void updateCredential(Credentials credential) {
        credentialsMapper.updateCredentials(encryptPassword(credential));
    }

    @Override
    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredentials(credentialid);
    }
}
