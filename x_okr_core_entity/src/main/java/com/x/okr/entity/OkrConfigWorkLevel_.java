/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.okr.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.okr.entity.OkrConfigWorkLevel.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sat May 06 19:35:29 CST 2017")
public class OkrConfigWorkLevel_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<OkrConfigWorkLevel,Date> createTime;
    public static volatile SingularAttribute<OkrConfigWorkLevel,String> description;
    public static volatile SingularAttribute<OkrConfigWorkLevel,String> id;
    public static volatile SingularAttribute<OkrConfigWorkLevel,Integer> orderNumber;
    public static volatile SingularAttribute<OkrConfigWorkLevel,String> sequence;
    public static volatile SingularAttribute<OkrConfigWorkLevel,Date> updateTime;
    public static volatile SingularAttribute<OkrConfigWorkLevel,String> workLevelName;
}
