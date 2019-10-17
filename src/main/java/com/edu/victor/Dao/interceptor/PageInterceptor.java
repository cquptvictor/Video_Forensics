package com.edu.victor.Dao.interceptor;

import com.edu.victor.domain.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Intercepts(value = {@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor {

    public Object intercept(Invocation arg0) throws Throwable {
        //获取到当前被拦截的对象
        StatementHandler statementHandler = (StatementHandler)arg0.getTarget();
        //方便得到target的元数据
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
        //mappedStatement包装了一条sql的所有信息
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        //sql语句的id
        String id = mappedStatement.getId();
        //只改变要ByPage的sql
        if(id.endsWith("ByPage") || id.endsWith("ByPageForApp")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            //为了获取总条数
            String countSql = "select count(*) from (" + sql + ")t";
            Connection conn = (Connection)arg0.getArgs()[0];
            PreparedStatement statement = conn.prepareStatement(countSql);
            //获取到parameterHandler
            ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            //设置参数
            parameterHandler.setParameters(statement);
            ResultSet rs = statement.executeQuery();
            Page page = (Page)boundSql.getParameterObject();
            if(id.endsWith("ByPage") && rs.next()){
                page.setTotalNumber(rs.getInt(1));
            }
            if(page.getCurrentPage() > page.getTotalPage())
            {
                page.setCurrentPage(page.getTotalPage());
            }
            if(page.getCurrentPage() <= 0){
                page.setCurrentPage(1);
            }
            /**只有前端页面才要求设置hasNext*/
            if(id.endsWith("ByPageForApp") && page.getCurrentPage() == page.getTotalPage()){
                page.setHasNext(0);
            }else{
                page.setHasNext(1);
            }
            String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNum() + "," + page.getPageNum();
            //更新sql
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        //放行
        return arg0.proceed();
    }
    /**
     * 包装成为代理对象*/
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }
    /**
     * 获得在注册Interceptor时设置的参数*/
    public void setProperties(Properties arg0) {

    }
    public static void main(String[] args){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
       System.out.println(simpleDateFormat.format(date.getTime()));
       System.out.println(date.getTime());
    }
}
