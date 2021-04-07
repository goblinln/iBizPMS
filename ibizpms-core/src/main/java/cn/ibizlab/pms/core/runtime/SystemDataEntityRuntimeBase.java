package cn.ibizlab.pms.core.runtime;

import net.ibizsys.runtime.ISystemRuntime;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SystemDataEntityRuntime extends net.ibizsys.runtime.dataentity.DataEntityRuntimeImplBase {

    @Autowired
    ISystemRuntime system ;

    @Override
    public ISystemRuntime getSystemRuntime() {
        return system;
    }

}
