package com.x.cms.assemble.control.jaxrs.appcategorypermission;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.cache.ApplicationCache;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.cms.assemble.control.WrapTools;
import com.x.cms.assemble.control.jaxrs.appcategorypermission.exception.AppCategoryPermissionCategoryIdEmptyException;
import com.x.cms.assemble.control.jaxrs.appcategorypermission.exception.AppCategoryPermissionProcessException;
import com.x.cms.core.entity.AppCategoryPermission;

import net.sf.ehcache.Element;

public class ExcuteListByCategory extends ExcuteBase {

	private Logger logger = LoggerFactory.getLogger( ExcuteListByCategory.class );
	
	@SuppressWarnings("unchecked")
	protected ActionResult<List<WrapOutAppCategoryPermission>> execute( HttpServletRequest request, EffectivePerson effectivePerson, String categoryId, String permission ) throws Exception {
		ActionResult<List<WrapOutAppCategoryPermission>> result = new ActionResult<>();
		List<WrapOutAppCategoryPermission> wraps = null;
		List<String> ids = null;
		List<AppCategoryPermission> appCategoryPermissionList = null;
		Boolean check = true;
		
		String cacheKey = ApplicationCache.concreteCacheKey( "category", categoryId, permission );
		Element element = cache.get(cacheKey);
		
		if ((null != element) && ( null != element.getObjectValue()) ) {
			wraps = ( List<WrapOutAppCategoryPermission> ) element.getObjectValue();
			result.setData(wraps);
		} else {
			if( check ){
				if( categoryId == null || categoryId.isEmpty() ){
					check = false;
					Exception exception = new AppCategoryPermissionCategoryIdEmptyException();
					result.error( exception );
				}
			}
			if( check ){
				try {
					ids = appCategoryPermissionServiceAdv.listPermissionByCategory( categoryId, permission );
				} catch (Exception e) {
					check = false;
					Exception exception = new AppCategoryPermissionProcessException( e, "根据分类ID查询所有对应的应用栏目分类权限配置信息列表时发生异常。categoryId:" + categoryId );
					result.error( exception );
					logger.error( e, effectivePerson, request, null);
				}
			}
			if( check ){
				if( ids != null && !ids.isEmpty() ){
					try {
						appCategoryPermissionList = appCategoryPermissionServiceAdv.list( ids );
					} catch (Exception e) {
						check = false;
						Exception exception = new AppCategoryPermissionProcessException( e, "根据指定的ID列表查询应用栏目分类管理员配置信息时发生异常。" );
						result.error( exception );
						logger.error( e, effectivePerson, request, null);
					}
				}
			}
			if( check ){
				if( appCategoryPermissionList != null && !appCategoryPermissionList.isEmpty() ){
					try {
						wraps = WrapTools.appCategoryPermission_wrapout_copier.copy( appCategoryPermissionList );
						
						Collections.sort(wraps);
						cache.put(new Element( cacheKey, wraps ));
						result.setData(wraps);
					} catch (Exception e) {
						Exception exception = new AppCategoryPermissionProcessException( e, "系统将查询出来的应用栏目分类权限信息转换为输出格式时发生异常。" );
						result.error( exception );
						logger.error( e, effectivePerson, request, null);
					}
				}
			}
		}
		
		return result;
	}
	
}