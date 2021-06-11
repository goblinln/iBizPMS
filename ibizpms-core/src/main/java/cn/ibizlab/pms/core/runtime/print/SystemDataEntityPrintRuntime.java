package cn.ibizlab.pms.core.runtime.print;

import net.ibizsys.runtime.dataentity.print.DEPrintRuntime;
import net.ibizsys.runtime.util.IEntityBase;

import java.io.OutputStream;

public class SystemDataEntityPrintRuntime extends DEPrintRuntime {

    @Override
    protected void onOutput(OutputStream outputStream, IEntityBase[] list, String strType) throws Exception {

    }
}
