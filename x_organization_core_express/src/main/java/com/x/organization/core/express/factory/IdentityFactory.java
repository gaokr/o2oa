package com.x.organization.core.express.factory;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.x.base.core.exception.RunningException;
import com.x.base.core.http.WrapInStringList;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.base.core.project.AbstractThisApplication;
import com.x.base.core.project.x_organization_assemble_express;
import com.x.base.core.utils.ListTools;
import com.x.organization.core.express.wrap.WrapIdentity;

public class IdentityFactory {

	private static Logger logger = LoggerFactory.getLogger(IdentityFactory.class);

	private static Type wrapIdentityCollectionType = new TypeToken<ArrayList<WrapIdentity>>() {
	}.getType();

	public String check(String name) throws Exception {
		WrapIdentity wrap = this.getWithName(name);
		if (null != wrap) {
			return wrap.getName();
		}
		return null;
	}

	public List<String> check(List<String> names) throws Exception {
		List<String> list = new ArrayList<>();
		if (ListTools.isEmpty(names)) {
			return list;
		}
		try {
			WrapInStringList wrap = new WrapInStringList();
			for (String str : names) {
				if (StringUtils.isNotEmpty(str)) {
					wrap.getValueList().add(str);
				}
			}
			List<WrapIdentity> wraps = AbstractThisApplication.applications.postQuery(
					x_organization_assemble_express.class, "identity/list", wrap, wrapIdentityCollectionType);
			for (WrapIdentity o : wraps) {
				list.add(o.getName());
			}
		} catch (Exception e) {
			throw new Exception("check identity{names:" + names + "} error.", e);
		}
		return list;
	}

	public WrapIdentity getWithName(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/" + URLEncoder.encode(name, "UTF-8"), WrapIdentity.class);
		} catch (Exception e) {
			throw new Exception("getWithName identity{name:" + name + "} error.", e);
		}
	}

	public List<WrapIdentity> listWithPerson(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/person/" + URLEncoder.encode(name, "UTF-8"), wrapIdentityCollectionType);
		} catch (Exception e) {
			RunningException re = new RunningException(e, "listWithPerson person: {} error.", name);
			logger.error(re);
			throw re;
		}
	}

	public List<String> ListNameWithPerson(String name) throws Exception {
		List<WrapIdentity> os = this.listWithPerson(name);
		List<String> list = ListTools.extractProperty(os, "name", String.class, true, true);
		return list;
	}

	public List<WrapIdentity> listWithDepartmentSubDirect(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/department/" + URLEncoder.encode(name, "UTF-8") + "/sub/direct",
					wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listWithDepartmentSubDirect person{name:" + name + "} error.", e);
		}
	}

	public List<String> listWithDepartmentSubNested(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/department/" + URLEncoder.encode(name, "UTF-8") + "/sub/nested",
					wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listWithDepartmentSubNested person{name:" + name + "} error.", e);
		}
	}

	public List<String> listWithCompanySubDirect(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/company/" + URLEncoder.encode(name, "UTF-8") + "/sub/direct",
					wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listWithCompanySubDirect person{name:" + name + "} error.", e);
		}
	}

	public List<String> listWithCompanySubNested(String name) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/company/" + URLEncoder.encode(name, "UTF-8") + "/sub/nested",
					wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listWithCompanySubNested person{name:" + name + "} error.", e);
		}
	}

	public List<String> listLikeWithCompanySubNestedWithDepartmentSubNested(String key) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/company/sub/nested/department/sub/nested/like/" + URLEncoder.encode(key, "UTF-8"),
					wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listLikeWithCompanySubNestedWithDepartmentSubNested person{name:" + key + "} error.",
					e);
		}
	}

	public List<WrapIdentity> listPinyinInitial(String key) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/pinyininitial/" + URLEncoder.encode(key, "UTF-8"), wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listPinyinInitial key:" + key + " error.", e);
		}
	}

	public List<WrapIdentity> listLikePinyin(String key) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/like/pinyin/" + URLEncoder.encode(key, "UTF-8"), wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listLikePinyin key:" + key + " error.", e);
		}
	}

	public List<WrapIdentity> listLike(String key) throws Exception {
		try {
			return AbstractThisApplication.applications.getQuery(x_organization_assemble_express.class,
					"identity/list/like/" + URLEncoder.encode(key, "UTF-8"), wrapIdentityCollectionType);
		} catch (Exception e) {
			throw new Exception("listLike key:" + key + " error.", e);
		}
	}

}
