package cn.ibizlab.pms.core.runtime.translator;

import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.res.SysTranslatorRuntimeBase;
import net.ibizsys.runtime.util.IEntityBase;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

public class BranchValueTranslator extends SysTranslatorRuntimeBase {

    private final static String STRVAL = "所有平台";

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
        // 暂时先全部转动态代码表--原来已有，模拟数据处理（因为涉及到选择操作）
        // 适配代码表调整数据
        if (!bIn) {
            if (ObjectUtils.isEmpty(objValue)) {
                return 0;
            }
        }
//        if (bIn) {
//            if (!ObjectUtils.isEmpty(objValue) && STRVAL.equals(String.valueOf(objValue))) {
//                return 0;
//            }
//        } else {
//            if (!ObjectUtils.isEmpty(objValue)) {
//                if (NumberUtils.isDigits(String.valueOf(objValue)) && Integer.parseInt(String.valueOf(objValue)) == 0) {
//                    return STRVAL;
//                }
//            }
//        }
        return objValue;
    }
}
