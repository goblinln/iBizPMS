import { isEmpty } from 'ramda';
import { LogUtil } from '../log-util/log-util';
import { SearchFilter } from '../search-filter/search-filter';
import { CondType } from './cond-type';
import { PSModelCondBase } from './ps-model-cond-base';
import { PSModelCondEngineBase } from './ps-model-cond-engine-base';
import { PSDEDQGroupCond } from './ps-model-group-cond';
import { PSModelGroupCondBase } from './ps-model-group-cond-base';
import { PSDEDQSingleCond } from './ps-model-single-cond';
import { PSModelSingleCondBase } from './ps-model-single-cond-base';

/**
 * 实体查询条件引擎
 * @author lionlau
 *
 */
export class PSDEDQCondEngine extends PSModelCondEngineBase {
    /**
     * 数据上下文
     *
     * @static
     * @memberof PSDEDQCondEngine
     */
    static readonly PARAMTYPE_DATACONTEXT = 'DATACONTEXT';

    /**
     * 网页请求上下文
     *
     * @static
     * @memberof PSDEDQCondEngine
     */
    static readonly PARAMTYPE_WEBCONTEXT = 'WEBCONTEXT';

    /**
     * 测试
     *
     * @param {*} data 检测的数据
     * @param {SearchFilter} filter 过滤条件
     * @return {*}  {boolean}
     * @memberof PSDEDQCondEngine
     */
    public test(data: any, filter: SearchFilter): boolean {
        return this.testCond(this.getPSModelGroupCondBase(), data, filter);
    }

    /**
     * 查询条件判断
     *
     * @protected
     * @param {PSModelCondBase} cond
     * @param {*} data
     * @param {SearchFilter} filter
     * @return {*}  {boolean}
     * @memberof PSDEDQCondEngine
     */
    protected testCond(cond: PSModelCondBase, data: any, filter: SearchFilter): boolean {
        // 分组条件
        if (cond instanceof PSModelGroupCondBase) {
            // 获取子条件
            const list = cond.getChildPSModelCondBases();
            if (list == null || isEmpty(list)) {
                return !cond.isNotMode();
            }
            // 是否为「AND」
            const bAnd = CondType.GROUPOP_AND === cond.getCondOp();
            let bRet = bAnd;
            for (let i = 0; i < list.length; i++) {
                const childCond = list[i];
                if (this.testCond(childCond, data, filter)) {
                    if (!bAnd) {
                        bRet = true;
                        break;
                    }
                } else {
                    if (bAnd) {
                        bRet = false;
                        break;
                    }
                }
            }
            if (cond.isNotMode()) {
                return !bRet;
            }
            return bRet;
        }
        // 逻辑项
        if (cond instanceof PSModelSingleCondBase) {
            if (isEmpty(cond.getParam())) {
                LogUtil.warn('没有指定属性名称');
            }
            const objValue = data[cond.getParam().toLowerCase()];
            let objCondValue = null;
            const valType = cond.getValueType();
            const val = cond.getValue();
            if (valType != null && !isEmpty(valType)) {
                if (isEmpty(val)) {
                    LogUtil.warn('没有指定上下文参数名称');
                }
                // 网页请求上下文
                if (PSDEDQCondEngine.PARAMTYPE_WEBCONTEXT === valType) {
                    objCondValue = filter.data[val.toLowerCase()];
                }
                //值类型为数据上下文
                else if (PSDEDQCondEngine.PARAMTYPE_DATACONTEXT === valType) {
                    // 从过滤条件中获取条件值
                    objCondValue = filter.getValue(val.toLowerCase());
                }
            } else {
                objCondValue = cond.getValue();
            }
            //进行值判断
            return this.testSingleCond(cond.getCondOp(), objValue, objCondValue);
        }
        LogUtil.warn(`无法识别的条件对象[${cond}]`);
        return false;
    }

    protected createPSModelSingleCond(): PSModelSingleCondBase {
        return new PSDEDQSingleCond();
    }

    protected createPSModelGroupCond(): PSModelGroupCondBase {
        return new PSDEDQGroupCond();
    }
}
