package com.x.processplatform.assemble.designer.element.factory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.x.processplatform.assemble.designer.AbstractFactory;
import com.x.processplatform.assemble.designer.Business;
import com.x.processplatform.core.entity.element.Manual;
import com.x.processplatform.core.entity.element.Manual_;

public class ManualFactory extends AbstractFactory {

	public ManualFactory(Business business) throws Exception {
		super(business);
	}

	public List<String> listWithProcess(String id) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Manual.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Manual> root = cq.from(Manual.class);
		Predicate p = cb.equal(root.get(Manual_.process), id);
		cq.select(root.get(Manual_.id)).where(p);
		return em.createQuery(cq).getResultList();
	}

	/** 查找使用表单的manual */
	public List<String> listWithForm(String formId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Manual.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Manual> root = cq.from(Manual.class);
		Predicate p = cb.equal(root.get(Manual_.form), formId);
		cq.select(root.get(Manual_.id)).where(p);
		return em.createQuery(cq).getResultList();
	}

}