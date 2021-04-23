package cn.ibizlab.pms.core.runtime;

import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDER1N;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.runtime.IDynaInstRuntime;
import net.ibizsys.runtime.dataentity.der.DERTypes;
import net.ibizsys.runtime.util.IEntityBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.ObjectUtils;

import java.util.List;

public abstract class SystemDataEntityRuntime extends SystemDataEntityRuntimeBase {

    @Override
    protected void fillEntityFullInfo(IEntityBase entityBase, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, Object joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(entityBase, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue) || NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L)
            return;
        super.fillEntityFullInfo(entityBase, strActionName, iPSDEAction, iPSDER1N, iPSDataEntity, joinPoint);
    }

    @Override
    protected void translateEntityAfterProceed(Object arg0, Object objRet, String strActionName, IPSDEAction iPSDEAction, IPSDataEntity iPSDataEntity, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        IEntityBase iEntityBase = null;
        //更替参数
        if (objRet != null && objRet instanceof IEntityBase) {
            iEntityBase = (IEntityBase) objRet;
        } else if (arg0 instanceof IEntityBase) {
            iEntityBase = (IEntityBase) arg0;
        }

        if (iEntityBase != null) {
            resetEntityInvalidFKey(iEntityBase, strActionName, iPSDEAction, iPSDataEntity, joinPoint);
        }

        super.translateEntityAfterProceed(arg0, objRet, strActionName, iPSDEAction, iPSDataEntity, iDynaInstRuntime, joinPoint);
    }

    /**
     * 重置无效外键值
     *
     * @param iPSDEAction
     * @param arg0
     * @param joinPoint
     * @throws Exception
     */
    protected void resetEntityInvalidFKey(IEntityBase arg0, String strActionName, IPSDEAction iPSDEAction, IPSDataEntity iPSDataEntity, Object joinPoint) throws Throwable {
        //查询实体的从关系
        java.util.List<IPSDERBase> psDERBases = iPSDataEntity.getMinorPSDERs();
        if (psDERBases == null) {
            return;
        }

        //填充继承实体的关系信息
        for (IPSDERBase iPSDERBase : psDERBases) {
            if (DERTypes.DER1N.equals(iPSDERBase.getDERType())) {
                IPSDER1N iPSDER1N = (IPSDER1N) iPSDERBase;
                this.resetEntityInvalidFKey(arg0, strActionName, iPSDEAction, iPSDER1N, iPSDataEntity, joinPoint);
            }
        }
    }

    protected void resetEntityInvalidFKey(IEntityBase arg0, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, Object joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(arg0, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue)) {
            //无值，执行置空
        } else {
            if (NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L) {
                this.resetFieldValue(arg0, iPSDER1N.getPSPickupDEField());
            }
        }
    }

    protected String getFieldDataSetSortExp(IPSDEField iPSDEField) throws Exception {
        String fieldExp = super.getFieldDataSetSortExp(iPSDEField);
        if(StringUtils.isBlank(fieldExp))
            return iPSDEField.getName();
        if (fieldExp.indexOf(".") > 0){
            return fieldExp.substring(fieldExp.indexOf(".") + 1);
        }
        return fieldExp;
    }
}
