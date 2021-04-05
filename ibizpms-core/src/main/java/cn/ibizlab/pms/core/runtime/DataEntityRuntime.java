package cn.ibizlab.pms.core.runtime;

import net.ibizsys.runtime.ISystem;
import net.ibizsys.runtime.dataentity.DataEntityImplBase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataEntityRuntime extends DataEntityImplBase {

    @Autowired
    ISystem system ;

    @Override
    public ISystem getSystem() {
        return system;
    }


}