package com.x.processplatform.assemble.surface.jaxrs.readcompleted;

import java.util.List;

import com.x.base.core.application.jaxrs.EqualsTerms;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.processplatform.assemble.surface.wrapout.content.WrapOutReadCompleted;

class ActionListPrev extends ActionBase {

	ActionResult<List<WrapOutReadCompleted>> execute(EffectivePerson effectivePerson, String id, Integer count)
			throws Exception {
		EqualsTerms equals = new EqualsTerms();
		equals.put("person", effectivePerson.getName());
		ActionResult<List<WrapOutReadCompleted>> result = this.standardListPrev(readCompletedOutCopier, id, count,
				"sequence", equals, null, null, null, null, null, null, true, DESC);
		return result;
	}

}
