/**
 * 
 */
package com.beiyanght.csms.common.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cspframework.core.jpa.ThreadLocalManager;


/**
 * EM注入切面程序
 * 
 * @author ender
 * 
 */
public class CsmsEntityManagerAspect {
	private EntityManager entityManager;

	/**
	 * Service方法只想前的EM注入
	 */
	public void initEm() {
		if (getEntityManager() != null) {
			ThreadLocalManager.associateWithThread(getEntityManager());
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "wake")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
