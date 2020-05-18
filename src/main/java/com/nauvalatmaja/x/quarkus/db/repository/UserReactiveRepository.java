package com.nauvalatmaja.x.quarkus.db.repository;

import java.util.UUID;

import com.nauvalatmaja.x.quarkus.db.model.User;

import io.smallrye.mutiny.Uni;

public interface UserReactiveRepository {

	Uni<Void> deleteAll();

	Uni<UUID> persist(User user);

}
