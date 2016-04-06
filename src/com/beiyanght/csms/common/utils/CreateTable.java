/**
 * 
 */
package com.beiyanght.csms.common.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 创建建表、删除表的SQL
 * 
 * @author Stephen
 * 
 */
public class CreateTable {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext-dbgenerator.xml");
		EntityManagerFactory csmsEntityManagerFactory = (EntityManagerFactory) ctx.getBean("csmsEntityManagerFactory");
		EntityManager em = csmsEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
		csmsEntityManagerFactory.close();
	}

}
