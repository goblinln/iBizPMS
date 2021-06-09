package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.impl.DocLibServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[文档库] 自定义服务对象
 */
@Slf4j
@Primary
@Service("DocLibExService")
public class DocLibExService extends DocLibServiceImpl {

    @Autowired
    IActionService iActionService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * 自定义行为[Collect]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocLib collect(DocLib et) {
        DocLib docLib = this.get(et.getId());
        String collector = docLib.getCollector();
        if ("".equals(collector) || "/".equals(collector)) {
            collector += ",";
        }
        collector += AuthenticationUser.getAuthenticationUser().getUsername() + ",";
        et.setCollector(collector);
        this.updateById(et);

        return super.collect(et);
    }

    /**
     * 自定义行为[UnCollect]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocLib unCollect(DocLib et) {
        DocLib docLib = this.get(et.getId());
        String collector = docLib.getCollector();
        collector = collector.replaceFirst(AuthenticationUser.getAuthenticationUser().getUsername() + ",", "");
        if (",".equals(collector)) {
            collector = "";
        }
        et.setCollector(collector);
        this.updateById(et);

        return super.unCollect(et);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(DocLib et) {
        if (StaticDict.Doclib__type.PRODUCT.getValue().equals(et.getType())) {
            et.setProject(0L);
        }
        if (StaticDict.Doclib__type.PROJECT.getValue().equals(et.getType())) {
            et.setProduct(0L);
        }
        if (StaticDict.Doclib__acl.CUSTOM.getValue().equals(et.getAcl())) {
            et.setUsers(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        boolean flag =  super.create(et);
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.DOCLIB.getValue(),null,StaticDict.Action__type.CREATED.getValue(),
                "","",null,iActionService);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(DocLib et) {
        long libId =  et.getId();
        DocLib old = new DocLib();
        CachedBeanCopier.copy(this.get(et.getId()),old);
        if (StaticDict.Doclib__acl.CUSTOM.getValue().equals(et.getAcl())){
            String libCreatedBy = iActionService.getOne(new QueryWrapper<Action>().eq(PARAM_OBJECT_TYPE, StaticDict.Action__object_type.DOCLIB.getValue()).eq(PARAM_OBJECT_ID,libId).eq(FIELD_ACTION, StaticDict.Action__type.CREATED.getValue())).getActor();
            et.setUsers(libCreatedBy != null ? libCreatedBy : AuthenticationUser.getAuthenticationUser().getUsername());
        }
        boolean flag =  super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.DOCLIB.getValue(),null,StaticDict.Action__type.EDITED.getValue(),
                    "","",null,iActionService);
        }
        return flag;
    }

    @Override
    public DocLib sysGet(Long key) {
        if (key == 0){
            return null;
        }
        DocLib docLib = super.sysGet(key);
        if(docLib.getCollector() != null && docLib.getCollector().contains(AuthenticationUser.getAuthenticationUser().getUsername())) {
            docLib.setIsfavourites("1");
        }else {
            docLib.setIsfavourites("0");
        }

        return docLib;
    }
}

