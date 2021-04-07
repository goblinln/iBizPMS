package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Block;
import cn.ibizlab.pms.core.zentao.service.IBlockService;
import cn.ibizlab.pms.core.zentao.filter.BlockSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("BlockRuntime")
public class BlockRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IBlockService blockService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Block();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Block.json";
    }

    @Override
    public String getName() {
        return "ZT_BLOCK";
    }

    @Override
    public Object createEntity() {
        return new Block();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BlockServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
