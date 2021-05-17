package cn.ibizlab.pms.core.runtime;

import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDER1N;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.res.IPSSysSequence;
import net.ibizsys.runtime.IDynaInstRuntime;
import net.ibizsys.runtime.dataentity.DEStorageModes;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeBaseBase;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.dataentity.action.DEActions;
import net.ibizsys.runtime.dataentity.der.DERTypes;
import net.ibizsys.runtime.res.ISysSequenceRuntime;
import net.ibizsys.runtime.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class SystemDataEntityRuntime extends SystemDataEntityRuntimeBase {

    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(SystemDataEntityRuntime.class);

    @Override
    public boolean test(Serializable key, String action) {
        if(key instanceof Long) {
            if((Long) key == 0L || (Long) key == -1L) {
                return true;
            }
        }
        return super.test(key, action);
    }

    @Override
    protected void translateEntityNestedDER1NAfterProceed(Object objKey, IEntityBase arg0, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {

        /*ActionSession actionSession = ActionSessionManager.getCurrentSessionMust();

        IDataEntityRuntime minorDataEntityRuntime = this.getSystemRuntime().getDataEntityRuntime(iPSDER1N.getMinorPSDataEntity().getDynaModelFilePath());
        if(minorDataEntityRuntime.getStorageMode() == DEStorageModes.NONE) {
            //判断是否有一对多关系数据存储属性
            if(iPSDER1N.getPSOne2ManyDataDEField() == null) {
                return;
            }

            Object objValue = this.getFieldValue(arg0, iPSDER1N.getPSOne2ManyDataDEField());
            if(ObjectUtils.isEmpty(objValue)) {
                return;
            }

            IEntityBase[] minorEntities = minorDataEntityRuntime.deserializeEntities(objValue);
            if(minorEntities != null) {
                this.setNestedDERValue(arg0, iPSDER1N, minorEntities);
            }
            else {
                this.setNestedDERValue(arg0, iPSDER1N, null);
            }
            return;
        }

        String strParamKey = String.format("ONE2MANYDATA_%1$s_%2$s",iPSDER1N.getName(),actionSession.getSessionId());
        Object objActionParam = actionSession.getActionParam(strParamKey);
        IEntityBase[] minorEntities = null;
        boolean bGetOnly = true;
        if(objActionParam != null) {
            if( objActionParam != ActionSession.EMPTYPARAM) {
                minorEntities = (IEntityBase[])objActionParam;
            }
            bGetOnly = false;
        }

        ISearchContextBase iSearchContextBase =	minorDataEntityRuntime.createSearchContext();

        //设置外键属性
        IPSDEField pickupPSDEField = iPSDER1N.getPSPickupDEField();
//		IPSDERBase iPSDERBase = iPSOne2ManyDataDEField.getPSDER();
//		if(iPSDERBase instanceof IPSDER1N) {
//			IPSDER1N iPSDER1N = (IPSDER1N)iPSDERBase;
//			pickupPSDEField = iPSDER1N.getPSPickupDEField();
//		}
//		else if(iPSDERBase instanceof IPSDERCustom) {
//				IPSDERCustom iPSDERCustom = (IPSDERCustom)iPSDERBase;
//				pickupPSDEField = iPSDERCustom.getPickupPSDEField();
//			}

        if(pickupPSDEField==null) {
            throw new DataEntityRuntimeException(String.format("关系[%1$s]连接属性无效", iPSDER1N.getName()), Errors.MODELERROR, this);
        }

        minorDataEntityRuntime.setSearchCondition(iSearchContextBase, pickupPSDEField, Conditions.EQ, objKey);
        //查出原数据
        List<? extends IEntityBase> lastMinorEntityList = minorDataEntityRuntime.select(iSearchContextBase);

        if(bGetOnly) {
            if(lastMinorEntityList != null) {
                this.setNestedDERValue(arg0, iPSDER1N, lastMinorEntityList.toArray(new IEntityBase[lastMinorEntityList.size()]));
            }
            else {
                this.setNestedDERValue(arg0, iPSDER1N, null);
            }
            return;
        }

        //执行关系数据的新建、更新、删除操作
        Map<Object, IEntityBase> lastMinorEntityMap = new HashMap<Object, IEntityBase>();
        if(lastMinorEntityList != null) {
            for(IEntityBase lastEntityBase : lastMinorEntityList) {
                Object keyValue = minorDataEntityRuntime.getFieldValue(lastEntityBase, minorDataEntityRuntime.getKeyPSDEField());
                if(ObjectUtils.isEmpty(keyValue)) {
                    continue;
                }
                lastMinorEntityMap.put(keyValue, lastEntityBase);
            }
        }

        if(minorEntities != null) {
            for(IEntityBase iEntityBase : minorEntities) {
                Object keyValue = minorDataEntityRuntime.getFieldValue(iEntityBase, minorDataEntityRuntime.getKeyPSDEField());
                IEntityBase lastEntityBase = null;
                if(!ObjectUtils.isEmpty(keyValue)) {
                    lastEntityBase = lastMinorEntityMap.remove(keyValue);
                }

                if(lastEntityBase == null) {
                    //新建
                    minorDataEntityRuntime.setFieldValue(iEntityBase, pickupPSDEField, objKey);
                    minorDataEntityRuntime.executeAction(DEActions.CREATE, null, new Object[] {iEntityBase});
                }
                else {
                    //更新，先判断上一次的外键值是否一致
                    Object lastPickupValue = minorDataEntityRuntime.getFieldValue(lastEntityBase, pickupPSDEField);
                    if(this.getSystemRuntime().compareValue(objKey, lastPickupValue, pickupPSDEField.getStdDataType())!=0) {
                        log.error(String.format("关系数据[%1$s][%2$s]外键值前后不一致",minorDataEntityRuntime.getName(),keyValue));
                        throw new DataEntityRuntimeException(String.format("关系数据与当前数据的连接值前后不一致"), this);
                    }
                    minorDataEntityRuntime.setFieldValue(iEntityBase, pickupPSDEField, objKey);
                    minorDataEntityRuntime.executeAction(DEActions.UPDATE, null, new Object[] {iEntityBase});
                }
            }
        }

        //移除数据
        for(java.util.Map.Entry<Object,IEntityBase> entry : lastMinorEntityMap.entrySet()) {
            minorDataEntityRuntime.executeAction(DEActions.REMOVE, null, new Object[] {entry.getKey()});
        }

        //重写查询
        lastMinorEntityList = minorDataEntityRuntime.select(iSearchContextBase);

        if(lastMinorEntityList != null) {
            this.setNestedDERValue(arg0, iPSDER1N, lastMinorEntityList.toArray(new IEntityBase[lastMinorEntityList.size()]));
        }
        else {
            this.setNestedDERValue(arg0, iPSDER1N, null);
        }*/
    }

    /**
     *  外键值处理 默认填充0，不填充父数据
     *
     * @param entityBase
     * @param strActionName
     * @param iPSDEAction
     * @param iPSDER1N
     * @param iPSDataEntity
     * @param joinPoint
     * @throws Throwable
     */
    @Override
    protected void fillEntityFullInfo(IEntityBase entityBase, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, Object joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(entityBase, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue) || NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L){
            this.setFieldValue(entityBase, iPSDER1N.getPSPickupDEField(), 0);
            return;
        }
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
           /* if (NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L) {
                this.resetFieldValue(arg0, iPSDER1N.getPSPickupDEField());
            }*/
        }
    }

    @Override
    protected String getFieldDataSetSortExp(IPSDEField iPSDEField) throws Exception {
        String fieldExp = super.getFieldDataSetSortExp(iPSDEField);
        if(StringUtils.isBlank(fieldExp))
            return iPSDEField.getName();
        if (fieldExp.indexOf(".") > 0){
            return fieldExp.substring(fieldExp.indexOf(".") + 1);
        }
        return fieldExp;
    }

    @Override
    protected void fillEntityDefaultValues(IEntityBase arg0, String strActionName, IPSDEAction iPSDEAction, IPSDataEntity iPSDataEntity, Object actionData) throws Throwable {
        List<IPSDEField> psDEFields = iPSDataEntity.getAllPSDEFields();
        if (psDEFields != null) {
            boolean bCreateMode = "create".equals(strActionName) || iPSDEAction != null && "CREATE".equals(iPSDEAction.getActionMode());
            Iterator var8 = psDEFields.iterator();

            while(true) {
                while(true) {
                    IPSDEField iPSDEField;
                    while(true) {
                        if (!var8.hasNext()) {
                            return;
                        }

                        iPSDEField = (IPSDEField)var8.next();
                        if (this.isFillFieldDefaultValueWhenNull()) {
                            if (this.getFieldValue(arg0, iPSDEField) != null) {
                                continue;
                            }
                        } else if (this.containsFieldValue(arg0, iPSDEField)) {
                            continue;
                        }
                        break;
                    }

                    String strSequenceMode = iPSDEField.getSequenceMode();
                    Object objValue;
                    if (org.springframework.util.StringUtils.hasLength(strSequenceMode) && !"NONE".equals(strSequenceMode) && ("CREATE".equals(strSequenceMode) && bCreateMode || "GETDRAFT".equals(strSequenceMode) && !bCreateMode)) {
                        IPSSysSequence iPSSysSequence = iPSDEField.getPSSysSequence();
                        ISysSequenceRuntime iSysSequenceRuntime = this.getCurrentSystemRuntimeBase(false).getSysSequenceRuntime(iPSSysSequence);
                        objValue = iSysSequenceRuntime.get(arg0, iPSDEField, this);
                        this.setFieldValue(arg0, iPSDEField, objValue);
                    } else {
                        String strDefaultValue = iPSDEField.getDefaultValue();
                        String strDefaultValueType = iPSDEField.getDefaultValueType();
                        if (org.springframework.util.StringUtils.hasLength(strDefaultValue) || org.springframework.util.StringUtils.hasLength(strDefaultValueType)) {
                            objValue = this.calcFieldValue(arg0, iPSDEField, strDefaultValueType, strDefaultValue);
                            if(objValue.equals("#EMPTY")) {
                                this.setFieldValue(arg0, iPSDEField, "");
                            }else {
                                this.setFieldValue(arg0, iPSDEField, objValue);
                            }
                        }
                    }
                }
            }
        }
    }

}
