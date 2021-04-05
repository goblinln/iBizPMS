package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;

@Component("IbizproProductMonthlyRuntime")
public class IbizproProductMonthlyRuntime extends DataEntityRuntime {

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbizproProductMonthly();
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbizproProductMonthly domain = (IbizproProductMonthly)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbizproProductMonthly domain = (IbizproProductMonthly)o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        return false;
    }

    @Override
    public Object createEntity() {
        return new IbizproProductMonthly();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

}
