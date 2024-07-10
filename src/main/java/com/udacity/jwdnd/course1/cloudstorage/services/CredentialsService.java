package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CredentialsService {
    List<Pair<String, Credentials>> getAllCredentials(int userid) throws Exception;

    void addCredential(Credentials credential, int userid);

    void updateCredential(Credentials credential);

    void deleteCredential(int credentialid);
}
