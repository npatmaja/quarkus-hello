package com.nauvalatmaja.x.quarkus.db.repository;

import javax.enterprise.context.ApplicationScoped;

import com.nauvalatmaja.x.quarkus.db.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, String> {

}
