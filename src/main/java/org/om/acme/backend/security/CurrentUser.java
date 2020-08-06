package org.om.acme.backend.security;


import org.om.acme.backend.model.User;

@FunctionalInterface
public interface CurrentUser {
    User getUser();
}