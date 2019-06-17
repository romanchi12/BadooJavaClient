package org.romanchi.instagram.api;

import org.romanchi.instagram.model.Credentials;
import org.romanchi.instagram.model.Return;
import org.springframework.stereotype.Component;

@Component
public interface Authenticator {
    Return<?> authenticate(Credentials credentials);
}
