package cn.ibizlab.pms.core.runtime.translator;

import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.res.SysTranslatorRuntimeBase;
import net.ibizsys.runtime.util.IEntityBase;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class BuildValueTranslator extends SysTranslatorRuntimeBase {

    private final static String STRVAL = "主干";
    private final static String STRREALVAL = "trunk";

    /**
     * @param objValue                需要转换的值
     * @param bIn                     true：由页面传入时处理； false:输出到页面时处理
     * @param iEntityBase             实体
     * @param iPSDEField              属性
     * @param entityDataEntityRuntime
     * @return
     * @throws Throwable
     */
    @Override
    public Object translate(Object objValue, boolean bIn, IEntityBase iEntityBase, IPSDEField iPSDEField, IDataEntityRuntime entityDataEntityRuntime) throws Throwable {
        // 暂时先全部转动态代码表模拟数据处理（因为涉及到选择操作）
//        if (bIn) {
//            if (!ObjectUtils.isEmpty(objValue)) {
//                String strObjVal = String.valueOf(objValue);
//                if (!StringUtils.isEmpty(strObjVal)) {
//                    String[] arrObjVals = strObjVal.split(",", -1);
//                    for (int i = 0; i < arrObjVals.length; i++) {
//                        String strSingleVal = arrObjVals[i];
//                        if (STRVAL.equals(strSingleVal)) {
//                            arrObjVals[i] = STRREALVAL;
//                            break;
//                        }
//                    }
//                    return String.join(",", arrObjVals);
//                }
//            }
//        } else {
//            if (!ObjectUtils.isEmpty(objValue)) {
//                String strObjVal = String.valueOf(objValue);
//                if (!StringUtils.isEmpty(strObjVal)) {
//                    String[] arrObjVals = strObjVal.split(",", -1);
//                    for (int i = 0; i < arrObjVals.length; i++) {
//                        String strSingleVal = arrObjVals[i];
//                        if (STRREALVAL.equals(strSingleVal)) {
//                            arrObjVals[i] = STRVAL;
//                            break;
//                        }
//                    }
//                    return String.join(",", arrObjVals);
//                }
//            }
//        }
        return objValue;
    }
}
