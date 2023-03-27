package com.pplbo.fortic5.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority(T(com.pplbo.fortic5.model.user.Role).CUSTOMER)")
public @interface HasCustomerRole {
}