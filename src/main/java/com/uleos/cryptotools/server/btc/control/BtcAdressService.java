package com.uleos.cryptotools.server.btc.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.uleos.cryptotools.server.JpaResultHelper;
import com.uleos.cryptotools.server.btc.entity.BtcAdress;
import com.uleos.cryptotools.server.btc.entity.BtcAdress_;

@Stateless
public class BtcAdressService {

	@PersistenceContext(unitName = "pu")
	EntityManager entityManager;

	public BtcAdress create(BtcAdress adress) {
		entityManager.persist(adress);
		return adress;
	}

	public List<BtcAdress> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BtcAdress> cq = cb.createQuery(BtcAdress.class);
		Root<BtcAdress> root = cq.from(BtcAdress.class);
		cq.select(root);
		cq.orderBy(cb.asc(root.get(BtcAdress_.adress)));
		TypedQuery<BtcAdress> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	public BtcAdress merge(BtcAdress adress) {
		return entityManager.merge(adress);
	}

	public void remove(BtcAdress adress) {
		if (!entityManager.contains(adress)) {
			adress = entityManager.merge(adress);
		}
		entityManager.remove(adress);
	}

	public BtcAdress findById(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BtcAdress> cq = cb.createQuery(BtcAdress.class);
		Root<BtcAdress> root = cq.from(BtcAdress.class);
		cq.select(root).where(cb.equal(root.get(BtcAdress_.id), id));
		TypedQuery<BtcAdress> q = entityManager.createQuery(cq);
		return JpaResultHelper.getSingleResultOrNull(q);
	}

	public void save(List<BtcAdress> adresses) {
		for (BtcAdress btcAdress : adresses) {
			if (btcAdress.getId() == null) {
				entityManager.persist(btcAdress);
			} else {
				entityManager.merge(btcAdress);
			}
			if (btcAdress.isMarkedToDelete()) {
				remove(btcAdress);
			}
		}
	}

}
