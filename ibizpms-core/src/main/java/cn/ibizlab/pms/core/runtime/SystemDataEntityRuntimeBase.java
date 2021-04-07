package cn.ibizlab.pms.core.runtime;

import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.util.IEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SystemDataEntityRuntimeBase extends net.ibizsys.runtime.dataentity.DataEntityRuntimeImplBase {

    @Autowired
    ISystemRuntime system ;

    @Override
    public ISystemRuntime getSystemRuntime() {
        return system;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) o;
        if(entity == null)
            return null ;
        return entity.get(ipsdeField.getCodeName());
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IEntity entity = (IEntity) o;
        if(entity == null)
            return  ;
        entity.set(ipsdeField.getCodeName(),o1);
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) o;
        if(entity == null)
            return  false;
        return entity.contains(ipsdeField.getCodeName().toLowerCase());
    }

    @Override
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) o;
        if(entity == null)
            return  ;
        entity.reset(ipsdeField.getCodeName().toLowerCase());
    }

}
