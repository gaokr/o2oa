package com.x.attendance.assemble.control.jaxrs.attendanceschedulesetting;

import com.x.base.core.exception.PromptException;

class AttendanceScheduleIdEmptyException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	AttendanceScheduleIdEmptyException() {
		super("查询操作传入的参数Id为空，无法进行查询操作.");
	}
}
