package com.x.cms.assemble.control.jaxrs.queryviewdesign;

import java.util.Date;

import com.x.base.core.cache.ApplicationCache;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.entity.annotation.CheckPersistType;
import com.x.base.core.exception.ExceptionWhen;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.http.WrapOutId;
import com.x.cms.core.entity.element.QueryView;


class ActionUpdate extends ActionBase {
	ActionResult<WrapOutId> execute(EffectivePerson effectivePerson, String id, WrapInQueryView wrapIn)
			throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<WrapOutId> result = new ActionResult<>();
			//Business business = new Business(emc);
			QueryView queryView = emc.find(id, QueryView.class, ExceptionWhen.not_found);
			//Application application = emc.find(queryView.getApplication(), Application.class, ExceptionWhen.not_found);
			//business.applicationEditAvailable(effectivePerson, application, ExceptionWhen.not_allow);
			emc.beginTransaction(QueryView.class);
			updateCopier.copy(wrapIn, queryView);
			queryView.setLastUpdatePerson(effectivePerson.getName());
			queryView.setLastUpdateTime(new Date());
			this.transQuery(queryView);
			emc.check(queryView, CheckPersistType.all);
			emc.commit();
			ApplicationCache.notify(QueryView.class);
			WrapOutId wrap = new WrapOutId(queryView.getId());
			result.setData(wrap);
			return result;
		}
	}
}
