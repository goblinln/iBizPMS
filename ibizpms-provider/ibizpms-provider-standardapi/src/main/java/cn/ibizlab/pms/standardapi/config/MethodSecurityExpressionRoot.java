package cn.ibizlab.pms.standardapi.config;

import  cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import  cn.ibizlab.pms.core.runtime.SystemRuntime;
import  cn.ibizlab.pms.util.security.SpringContextHolder;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;

public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public MethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean quickTest(String strDE, String action) {
        try {
            SystemRuntime systemRuntime = SpringContextHolder.getBean(SystemRuntime.class);
            SystemDataEntityRuntime dataEntityRuntime = (SystemDataEntityRuntime) systemRuntime.getDataEntityRuntime(strDE);
            if (dataEntityRuntime == null)
                return false;
            return dataEntityRuntime.quickTest(action);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean test(String strDE, Serializable key, String action) {
        try {
            SystemRuntime systemRuntime = SpringContextHolder.getBean(SystemRuntime.class);
            SystemDataEntityRuntime dataEntityRuntime = (SystemDataEntityRuntime) systemRuntime.getDataEntityRuntime(strDE);
            if (dataEntityRuntime == null)
                return false;
            return dataEntityRuntime.test(key, action);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean test(String strDE, List<Serializable> keys, String action) {
        try {
            SystemRuntime systemRuntime = SpringContextHolder.getBean(SystemRuntime.class);
            SystemDataEntityRuntime dataEntityRuntime = (SystemDataEntityRuntime) systemRuntime.getDataEntityRuntime(strDE);
            if (dataEntityRuntime == null)
                return false;
            return dataEntityRuntime.test(keys, action);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean test(String strDE, String strPDE, Serializable Pkey, String Paction, String action) {
        try {
            SystemRuntime systemRuntime = SpringContextHolder.getBean(SystemRuntime.class);
            SystemDataEntityRuntime dataEntityRuntime = (SystemDataEntityRuntime) systemRuntime.getDataEntityRuntime(strDE);
            if (dataEntityRuntime == null)
                return false;
            return dataEntityRuntime.test(strPDE, Pkey, Paction, action);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean test(String strDE, String strPDE, Serializable Pkey, String Paction, Serializable key, String action) {
        try {
            SystemRuntime systemRuntime = SpringContextHolder.getBean(SystemRuntime.class);
            SystemDataEntityRuntime dataEntityRuntime = (SystemDataEntityRuntime) systemRuntime.getDataEntityRuntime(strDE);
            if (dataEntityRuntime == null)
                return false;
            return dataEntityRuntime.test(strPDE, Pkey, Paction, key, action);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}
