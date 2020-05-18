package com.nauvalatmaja.x.quarkus.db.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.nauvalatmaja.x.quarkus.db.model.User;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

@ApplicationScoped
public class UserReactivePostgresRepository implements UserReactiveRepository {

	private static final String INSERT_USERS_QUERY = "INSERT INTO users (id, username) VALUES ($1, $2) RETURNING (id)";
	
	@Inject
	PgPool client;
	
	@Override
	public Uni<Void> deleteAll() {
		return client.preparedQuery("DELETE from users").onItem().apply(rowset -> null);
	}

	@Override
	public Uni<UUID> persist(User user) {
		return client.preparedQuery(INSERT_USERS_QUERY, Tuple.of(user.getId(), user.getUsername()))
				.onItem()
				.apply(rowSet -> rowSet.iterator().next().getUUID("id"));
	}

}
