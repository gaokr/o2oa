/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.processplatform.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.processplatform.core.entity.element.Condition.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sat May 06 19:36:06 CST 2017")
public class Condition_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Condition,String> afterArriveScript;
    public static volatile SingularAttribute<Condition,String> afterArriveScriptText;
    public static volatile SingularAttribute<Condition,String> afterExecuteScript;
    public static volatile SingularAttribute<Condition,String> afterExecuteScriptText;
    public static volatile SingularAttribute<Condition,String> afterInquireScript;
    public static volatile SingularAttribute<Condition,String> afterInquireScriptText;
    public static volatile SingularAttribute<Condition,String> alias;
    public static volatile SingularAttribute<Condition,Boolean> allowReroute;
    public static volatile SingularAttribute<Condition,Boolean> allowRerouteTo;
    public static volatile SingularAttribute<Condition,String> beforeArriveScript;
    public static volatile SingularAttribute<Condition,String> beforeArriveScriptText;
    public static volatile SingularAttribute<Condition,String> beforeExecuteScript;
    public static volatile SingularAttribute<Condition,String> beforeExecuteScriptText;
    public static volatile SingularAttribute<Condition,String> beforeInquireScript;
    public static volatile SingularAttribute<Condition,String> beforeInquireScriptText;
    public static volatile SingularAttribute<Condition,Date> createTime;
    public static volatile SingularAttribute<Condition,String> description;
    public static volatile SingularAttribute<Condition,String> extension;
    public static volatile SingularAttribute<Condition,String> form;
    public static volatile SingularAttribute<Condition,String> id;
    public static volatile SingularAttribute<Condition,String> name;
    public static volatile SingularAttribute<Condition,String> position;
    public static volatile SingularAttribute<Condition,String> process;
    public static volatile ListAttribute<Condition,String> readDataPathList;
    public static volatile ListAttribute<Condition,String> readDepartmentList;
    public static volatile SingularAttribute<Condition,String> readDuty;
    public static volatile ListAttribute<Condition,String> readIdentityList;
    public static volatile SingularAttribute<Condition,String> readScript;
    public static volatile SingularAttribute<Condition,String> readScriptText;
    public static volatile ListAttribute<Condition,String> reviewDataPathList;
    public static volatile ListAttribute<Condition,String> reviewDepartmentList;
    public static volatile SingularAttribute<Condition,String> reviewDuty;
    public static volatile ListAttribute<Condition,String> reviewIdentityList;
    public static volatile SingularAttribute<Condition,String> reviewScript;
    public static volatile SingularAttribute<Condition,String> reviewScriptText;
    public static volatile ListAttribute<Condition,String> routeList;
    public static volatile SingularAttribute<Condition,String> script;
    public static volatile SingularAttribute<Condition,String> sequence;
    public static volatile SingularAttribute<Condition,Date> updateTime;
}
