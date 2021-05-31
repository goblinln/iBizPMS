package cn.ibizlab.pms.util.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryContextHelper {

    private static final Pattern pt;
    private static final String ctx = "srf.(datacontext|sessioncontext|webcontext).(\\w+)";
    private static final String nvaluectx="(.*?)srf(datacontext|sessioncontext|webcontext)(.*?)\"ignoreempty\":true(.*?)";

    static {
        pt = Pattern.compile(ctx);
    }

    public static void main(String[] args) {
        String sql ="( t21.`UPDATEMAN` =  ${srfdatacontext('name','{\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')} )";
        sql ="( t21.`UPDATEMAN` =  ${srfdatacontext('ORGID','{\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')}  AND  t21.`UPDATEMAN` LIKE  CONCAT('%',${srfdatacontext('name','{\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')},'%') )";
        System.out.println("上下文参数转换："+contextParamConvert(sql));
        //是否存无值忽略条件
        sql="( t21.`UPDATEMAN` =  ${srfdatacontext('name','{\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')}  AND  <#assign _value=srfdatacontext('name','{\"ignoreempty\":true,\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')><#if _value?length gt 0>t21.`UPDATEMAN` LIKE  CONCAT('%',${_value},'%')<#else>1=1</#if> )";
        System.out.println("是否存在无值忽略条件:" + checkIgnoreNullValueCond(sql));
        //无值忽略条件转换：
        sql="( t21.`UPDATEMAN` =  ${srfdatacontext('name','{\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')}  AND  <#assign _value=srfdatacontext('name','{\"ignoreempty\":true,\"defname\":\"BSENTITY5NAME\",\"dename\":\"HUMAN\"}')><#if _value?length gt 0>t21.`UPDATEMAN` LIKE  CONCAT('%',${_value},'%')<#else>1=1</#if> )";
        System.out.println("无值忽略条件转换："+checkNullContextParamConvert(sql));
    }

    public static String contextParamConvert(String sql){
        //补充上下文的IN查询(in通过$获取参数,其余为#)： IN (${srfdatacontext('cityid','{"defname":"CITYID","dename":"CITY"}')}) -->IN ( ${srf.srfdatacontext.cityid} )
        sql = sql.replaceAll("(IN|in) \\(\\$\\{srf(datacontext|sessioncontext|webcontext)\\('(\\w+)','(.*?)'\\)}\\)", "$1 (\\$\\{srf.$2.$3})");
        // 平台配置格式替换${srfdatacontext('cityid','{"defname":"CITYID","dename":"CITY"}')}  --> #{srf.srfdatacontext.cityid}
        sql = sql.replaceAll("\\$\\{srf(datacontext|sessioncontext|webcontext)\\('(\\w+)','(.*?)'\\)}", "#\\{srf.$1.$2}");
        // 用户配置格式替换${srfdatacontext('cityid')}  --> #{srf.srfdatacontext.cityid}
        sql = sql.replaceAll("\\$\\{srf(datacontext|sessioncontext|webcontext)\\('(\\w+)'\\)}", "#\\{srf.$1.$2}");
        // 将上下文参数转小写 #{srf.srfdatacontext.SRFORGID} --> #{srf.srfdatacontext.srforgid}
        Matcher mt = pt.matcher(sql);
        while(mt.find()){
            sql = sql.replaceAll(mt.group(),mt.group().toLowerCase());
        }
        return sql;
    }

    /**
     * 判断是否存在无值忽略条件的条件
     * @param sql
     * @return
     */
    public static boolean checkIgnoreNullValueCond(String sql){
        if(sql.matches(nvaluectx)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 无值忽略条件sql转换
     * @param sql
     * @return
     */
    public static String checkNullContextParamConvert(String sql){
        //获取数据查询中的条件，并对条件进行转义 <![CDATA[ age <='20' ]]>
        sql = sql.replaceAll("(\\s+[\\w|\\.|\\`]+\\s*?)(<>|<=|<|>=|>)(\\s*[\\w|\\'|$\\{}()'\",:\\\\]+\\s+?)"," <![CDATA[ $1$2$3 ]]> ");
        //补充上下文的IN查询(in通过$获取参数,其余为#)
        sql = sql.replaceAll("<#assign _value=srf(datacontext|sessioncontext|webcontext)\\('(\\w+)','(.*?)'\\)><#if _value\\?length gt 0>(.*?)\\(\\$\\{_value}\\)<#else>1=1</#if>","<choose><when test=\"srf.$1.$2 != null\"> <![CDATA[ $4  \\$\\{srf.$1.$2}  ]]> </when><otherwise>1=1</otherwise></choose>");
        //获取模型中的空值条件，在条件外层补充mybatis判断条件 <choose><when test="srf.datacontext.name != null"> <![CDATA[ t1.`NAME` <= #{srf.datacontext.name}  ]]> </when><otherwise>1=1</otherwise></choose>
        sql = sql.replaceAll("<#assign _value=srf(datacontext|sessioncontext|webcontext)\\('(\\w+)','(.*?)'\\)><#if _value\\?length gt 0>(.*?)\\$\\{_value}(.*?)?<#else>1=1</#if>","<choose><when test=\"srf.$1.$2 != null\"> <![CDATA[ $4#\\{srf.$1.$2}$5  ]]> </when><otherwise>1=1</otherwise></choose>");
        return sql;
    }

}
