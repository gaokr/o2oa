package com.x.okr.assemble.control.jaxrs.okrcenterworkinfo.exception;

import com.x.base.core.exception.PromptException;

public class DeployableWorkQueryException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	public DeployableWorkQueryException( Throwable e, String centerId, String person ) {
		super("系统根据条件查询用户需要进行部署的工作信息列表时发生异常。CenterId:" + centerId + ", Person:" + person, e );
	}
}
