package com.x.okr.assemble.control.jaxrs.okrworkbaseinfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.base.core.utils.SortTools;
import com.x.okr.assemble.control.OkrUserCache;
import com.x.okr.assemble.control.jaxrs.okrcenterworkinfo.WrapOutOkrCenterWorkInfo;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.CenterWorkNotExistsException;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.GetOkrUserCacheException;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.UserNoLoginException;
import com.x.okr.entity.OkrCenterWorkInfo;
import com.x.okr.entity.OkrWorkBaseInfo;

public class ExcuteListUsersWorkByCenterId extends ExcuteBase {

	private Logger logger = LoggerFactory.getLogger( ExcuteListUsersWorkByCenterId.class );
	
	protected ActionResult<WrapOutOkrCenterWorkInfo> execute( HttpServletRequest request,EffectivePerson effectivePerson, String id ) throws Exception {
		ActionResult<WrapOutOkrCenterWorkInfo> result = new ActionResult<WrapOutOkrCenterWorkInfo>();
		List<WrapOutOkrWorkBaseInfo> wrapsWorkBaseInfoList_for_center = new ArrayList<WrapOutOkrWorkBaseInfo>();
		List<WrapOutOkrWorkBaseInfo> all_wrapWorkBaseInfoList = null;
		List<OkrWorkBaseInfo> all_workBaseInfoList = null;
		WrapOutOkrCenterWorkInfo wrapOutOkrCenterWorkInfo = null;
		OkrCenterWorkInfo okrCenterWorkInfo  = null;
		List<String> query_statuses = new ArrayList<String>();
		Boolean hasNoneSubmitReport = false;
		OkrUserCache  okrUserCache  = null;
		Boolean check = true;	
		if( check ){
			try {
				okrUserCache = okrUserInfoService.getOkrUserCacheWithPersonName( effectivePerson.getName() );
			} catch ( Exception e ) {
				check = false;
				Exception exception = new GetOkrUserCacheException( e, effectivePerson.getName()  );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}
		}		
		if( check && ( okrUserCache == null || okrUserCache.getLoginIdentityName() == null ) ){
			check = false;
			Exception exception = new UserNoLoginException( effectivePerson.getName()  );
			result.error( exception );
			//logger.error( e, effectivePerson, request, null);
		}
		if( check ){
			try{		
				query_statuses.add( "正常" );	
				
				//查询中心工作信息是否存在
				okrCenterWorkInfo = okrCenterWorkInfoService.get( id );
				if( okrCenterWorkInfo != null ){
					wrapOutOkrCenterWorkInfo = okrCenterWorkInfo_wrapout_copier.copy( okrCenterWorkInfo );
					//获取到该中心工作下所有的工作信息
					all_workBaseInfoList = okrWorkBaseInfoService.listWorkInCenter( id, query_statuses );
					if( all_workBaseInfoList != null ){
						all_wrapWorkBaseInfoList = wrapout_copier.copy( all_workBaseInfoList );
						if( all_wrapWorkBaseInfoList != null ){
							for( WrapOutOkrWorkBaseInfo wrap_work : all_wrapWorkBaseInfoList){
								//判断工作是否有未提交的工作汇报
								hasNoneSubmitReport = false;
								hasNoneSubmitReport = okrWorkBaseInfoService.hasNoneSubmitReport( 
										wrap_work.getId(), "草稿", "草稿", null
								);
								wrap_work.setHasNoneSubmitReport( hasNoneSubmitReport );
							}
							for( WrapOutOkrWorkBaseInfo wrap_work : all_wrapWorkBaseInfoList){
								if( wrap_work.getParentWorkId() == null || wrap_work.getParentWorkId().isEmpty() ){
									wrap_work = composeSubWork( all_wrapWorkBaseInfoList, wrap_work );
									wrapsWorkBaseInfoList_for_center.add( wrap_work );
								}
							}
						}
					}
				}else{
					Exception exception = new CenterWorkNotExistsException( id  );
					result.error( exception );
					//logger.error( e, effectivePerson, request, null);
				}
			}catch( Exception e ){
				logger.warn( "system filter okrWorkBaseInfo got an exception." );
				logger.error(e);
				result.error( e );
			}
			if( wrapsWorkBaseInfoList_for_center != null && !wrapsWorkBaseInfoList_for_center.isEmpty() ){
				try {
					SortTools.asc( wrapsWorkBaseInfoList_for_center, "completeDateLimit" );
				} catch (Exception e) {
					logger.warn( "system sort work list got an exception." );
					logger.error(e);
					result.error( e );
				}
			}
			if( wrapOutOkrCenterWorkInfo != null ){
				wrapOutOkrCenterWorkInfo.setWorks( wrapsWorkBaseInfoList_for_center );
				result.setData( wrapOutOkrCenterWorkInfo );
			}
		}
		return result;
	}
	
}