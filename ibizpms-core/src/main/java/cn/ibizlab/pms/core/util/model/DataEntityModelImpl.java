package cn.ibizlab.pms.core.util.model;

import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.filter.ScopeUtils;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.UAADEAuthority;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DataEntityModelImpl implements IDataEntityModel {

    protected String entity;
    protected String pkey;
    protected String orgDRField;
    protected String deptDRField;
    protected String deptDCField;

    @PostConstruct
    public void init(){
        DataEntityModelGlobalHelper.setDataEntityModel(this.getEntity(),this);
    }

    @Override
    public boolean test(String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return true;
        List<GrantedAuthority> authorities = curUser.getAuthorities().stream()
                .filter(f -> f instanceof UAADEAuthority && ((UAADEAuthority) f).getEntity().equals(this.getEntity()))
                .collect(Collectors.toList());

        if (authorities.size() == 0)
            return false;
        if (action.equals("READ"))
            return true;

        // has de role action
        for (GrantedAuthority authority : authorities) {
            UAADEAuthority uaadeAuthority = (UAADEAuthority) authority;
            if (uaadeAuthority.getDeAction().stream().anyMatch(u -> u.equals(action)))
                return true;
        }

        return false;
    }

    @Override
    public boolean test(Serializable key, String action) {
        return false;
    }

    @Override
    public boolean test(List<Serializable> keys, String action) {
        return false;
    }

    @Override
    public boolean test(String PDEName, Serializable PKey, String action) {
        if (StringUtils.isBlank(PDEName))
            return test(action);
        //action转换父action
        if(!action.equals("READ")){
            action = "UPDATE" ;
        }
        return DataEntityModelGlobalHelper.getDataEntityModel(PDEName).test(PKey, action);
    }

    @Override
    public boolean test(String PDEName, Serializable PKey, Serializable key, String action) {
        if (StringUtils.isBlank(PDEName))
            return test(key, action);
        //action转换父action
        if(!action.equals("READ")){
            action = "UPDATE" ;
        }
        return DataEntityModelGlobalHelper.getDataEntityModel(PDEName).test(PKey, action);
    }

    @Override
    public void addAuthorityConditions(QueryWrapperContext context, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return;
        List<GrantedAuthority> authorities = curUser.getAuthorities().stream()
                .filter(f -> f instanceof UAADEAuthority && ((UAADEAuthority) f).getEntity().equals(this.getEntity()))
                .collect(Collectors.toList());
        boolean hasCondition = false;
        for (GrantedAuthority authority : authorities) {
            UAADEAuthority uaadeAuthority = (UAADEAuthority) authority;
            //未设置权限 忽略
            if ((uaadeAuthority.getEnableorgdr() == null || uaadeAuthority.getEnableorgdr() == 0 || (uaadeAuthority.getEnableorgdr() == 1 && (uaadeAuthority.getOrgdr() == null || uaadeAuthority.getOrgdr() == 0)))
                    && (uaadeAuthority.getEnabledeptdr() != null || uaadeAuthority.getEnabledeptdr() == 0 || (uaadeAuthority.getEnabledeptdr() == 1 && (uaadeAuthority.getDeptdr() == null || uaadeAuthority.getDeptdr() == 0)))
                    && (StringUtils.isBlank(uaadeAuthority.getBscope()))
            ) {

            } else {
                hasCondition = true;
                break;
            }
        }
        Consumer<QueryWrapper> authorityConditions = authorityCondition -> {
            for (GrantedAuthority authority : authorities) {
                UAADEAuthority uaadeAuthority = (UAADEAuthority) authority;
                //未设置权限 忽略
                if ((uaadeAuthority.getEnableorgdr() == null || uaadeAuthority.getEnableorgdr() == 0 || (uaadeAuthority.getEnableorgdr() == 1 && (uaadeAuthority.getOrgdr() == null || uaadeAuthority.getOrgdr() == 0)))
                        && (uaadeAuthority.getEnabledeptdr() != null || uaadeAuthority.getEnabledeptdr() == 0 || (uaadeAuthority.getEnabledeptdr() == 1 && (uaadeAuthority.getDeptdr() == null || uaadeAuthority.getDeptdr() == 0)))
                        && (StringUtils.isBlank(uaadeAuthority.getBscope()))
                ) {
                    continue;
                }
                Consumer<QueryWrapper> dataCondition = genDataCondition(uaadeAuthority);
                if (dataCondition != null)
                    authorityCondition.or(dataCondition);
            }
        };
        if (hasCondition)
            context.getSelectCond().and(authorityConditions);
    }

    protected Consumer<QueryWrapper> genDataCondition(UAADEAuthority uaadeAuthority) {
        if (StringUtils.isNotBlank(uaadeAuthority.getBscope())) {
            Consumer<QueryWrapper> dataConditions = dataCondition -> {
                dataCondition.nested(ScopeUtils.parse(uaadeAuthority.getBscope()));
                dataCondition.and(genOrgDeptCondition(uaadeAuthority));
            };
            return dataConditions;
        } else {
            return genOrgDeptCondition(uaadeAuthority);
        }

    }

    protected Consumer<QueryWrapper> genOrgDeptCondition(UAADEAuthority uaadeAuthority) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        Map<String, Set<String>> userInfo = curUser.getOrgInfo();
        Set<String> orgParent = userInfo.get("parentorg");
        Set<String> orgChild = userInfo.get("suborg");
        Set<String> orgDeptParent = userInfo.get("parentdept");
        Set<String> orgDeptChild = userInfo.get("subdept");
        Consumer<QueryWrapper> orgConditions = orgCondition -> {
            //组织范围
            if (uaadeAuthority.getEnableorgdr() != null && uaadeAuthority.getEnableorgdr() == 1
                    && uaadeAuthority.getOrgdr() != null && uaadeAuthority.getOrgdr() > 0) {
                //当前机构
                if ((IDataEntityModel.CUR & uaadeAuthority.getOrgdr()) > 0) {
                    Consumer<QueryWrapper> org = orgQw -> {
                        orgQw.eq(orgDRField, curUser.getOrgid());
                    };
                    orgCondition.or(org);
                }
                //上级机构
                if ((IDataEntityModel.PARENT & uaadeAuthority.getOrgdr()) > 0) {
                    if (orgParent.size() > 0) {
                        Consumer<QueryWrapper> org = orgQw -> {
                            orgQw.in(orgDRField, orgParent);
                        };
                        orgCondition.or(org);
                    }
                }
                //下级机构
                if ((IDataEntityModel.SUB & uaadeAuthority.getOrgdr()) > 0) {
                    if (orgChild.size() > 0) {
                        Consumer<QueryWrapper> org = orgQw -> {
                            orgQw.in(orgDRField, orgChild);
                        };
                        orgCondition.or(org);
                    }
                }
                //无值
                if ((IDataEntityModel.NULL & uaadeAuthority.getOrgdr()) > 0) {
                    Consumer<QueryWrapper> org = orgQw -> {
                        orgQw.isNull(orgDRField);
                    };
                    orgCondition.or(org);
                }
            }

            //部门范围
            if (uaadeAuthority.getEnabledeptdr() != null && uaadeAuthority.getEnabledeptdr() == 1
                    && uaadeAuthority.getDeptdr() != null && uaadeAuthority.getDeptdr() > 0) {
                //当前
                if ((IDataEntityModel.CUR & uaadeAuthority.getDeptdr()) > 0) {
                    Consumer<QueryWrapper> dept = deptQw -> {
                        deptQw.eq(deptDRField, curUser.getOrgid());
                    };
                    orgCondition.or(dept);
                }
                //上级
                if ((IDataEntityModel.PARENT & uaadeAuthority.getDeptdr()) > 0) {
                    if (orgDeptParent.size() > 0) {
                        Consumer<QueryWrapper> dept = deptQw -> {
                            deptQw.in(deptDRField, orgDeptParent);
                        };
                        orgCondition.or(dept);
                    }
                }
                //下级
                if ((IDataEntityModel.SUB & uaadeAuthority.getDeptdr()) > 0) {
                    if (orgDeptChild.size() > 0) {
                        Consumer<QueryWrapper> dept = deptQw -> {
                            deptQw.in(deptDRField, orgDeptChild);
                        };
                        orgCondition.or(dept);
                    }
                }
                //无值
                if ((IDataEntityModel.NULL & uaadeAuthority.getDeptdr()) > 0) {
                    Consumer<QueryWrapper> dept = deptQw -> {
                        deptQw.isNull(deptDRField);
                    };
                    orgCondition.or(dept);
                }
            }
        };
        return orgConditions;
    }

    public String getOrgDRField() {
        return orgDRField;
    }

    public String getDeptDRField() {
        return deptDRField;
    }

    public String getDeptDCField() {
        return deptDCField;
    }

    @Override
    public String getEntity() {
        return entity;
    }
}

