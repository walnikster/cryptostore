package com.uleos.cryptotools.server;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

public class JpaResultHelper {

	private JpaResultHelper() {

	}

	public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
		List<T> results = query.getResultList();
		if (results.isEmpty())
			return null;
		else if (results.size() == 1)
			return results.get(0);
		throw new NonUniqueResultException();
	}
}
