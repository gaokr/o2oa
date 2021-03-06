package com.x.processplatform.assemble.surface.jaxrs.work;

import java.util.List;

import com.x.base.core.application.jaxrs.EqualsTerms;
import com.x.base.core.application.jaxrs.InTerms;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.role.RoleDefinition;
import com.x.base.core.utils.ListTools;
import com.x.processplatform.assemble.surface.Business;
import com.x.processplatform.assemble.surface.Control;
import com.x.processplatform.assemble.surface.wrapout.content.WrapOutWork;
import com.x.processplatform.core.entity.content.Work;
import com.x.processplatform.core.entity.element.Application;

class ManageListPrev extends ActionBase {

	ActionResult<List<WrapOutWork>> execute(EffectivePerson effectivePerson, String id, Integer count,
			String applicationFlag) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<List<WrapOutWork>> result = new ActionResult<>();
			Business business = new Business(emc);
			Application application = business.application().pick(applicationFlag);
			if (null == application) {
				throw new ApplicationNotExistedException(applicationFlag);
			}
			if (effectivePerson.isManager()
					|| business.organization().role().hasAny(RoleDefinition.PersonManager, RoleDefinition.Manager)
					|| effectivePerson.isUser(application.getControllerList())) {
				EqualsTerms equalsTerms = new EqualsTerms();
				equalsTerms.put("application", application.getId());
				result = this.standardListPrev(workOutCopier, id, count, "sequence", equalsTerms, null, null, null,
						null, null, null, true, DESC);
			} else {
				List<String> ids = business.process().listControlableProcess(effectivePerson, application);
				if (ListTools.isNotEmpty(ids)) {
					InTerms inTerms = new InTerms();
					inTerms.put("process", ids);
					result = this.standardListPrev(workOutCopier, id, count, "sequence", null, null, null, inTerms,
							null, null, null, true, DESC);
				}
			}
			/* 添加权限 */
			if (null != result.getData()) {
				for (WrapOutWork wrap : result.getData()) {
					Work o = emc.find(wrap.getId(), Work.class);
					Control control = business.getControlOfWorkList(effectivePerson, o);
					wrap.setControl(control);
				}
			}
			return result;
		}
	}
}
