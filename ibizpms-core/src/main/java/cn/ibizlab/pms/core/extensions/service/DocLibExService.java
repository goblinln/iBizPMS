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
     * [Collect:收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public DocLib collect(DocLib et) {
        return super.collect(et);
    }
    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public DocLib unCollect(DocLib et) {
        return super.unCollect(et);
    }

    @Override
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
}

