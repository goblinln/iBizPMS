package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IBZProSysTpl;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.ISystem;
import net.ibizsys.runtime.dataentity.DataEntityImplBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("IBZProSysTplRuntime")
public class IBZProSysTplRuntime extends DataEntityImplBase {

    @Autowired
    ISystem system ;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IBZProSysTpl();
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
    public ISystem getSystem() {
        return system;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IBZProSysTpl domain = (IBZProSysTpl)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IBZProSysTpl domain = (IBZProSysTpl)o;
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
        return new IBZProSysTpl();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

}
