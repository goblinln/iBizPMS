import { LogUtil } from '../log-util/log-util';
import { CondType } from './cond-type';
import { PSModelGroupCondBase } from './ps-model-group-cond-base';
import { PSModelSingleCondBase } from './ps-model-single-cond-base';

/**
 * 模型条件引擎辅助对象
 *
 * @export
 * @abstract
 * @class PSModelCondEngineBase
 */
export abstract class PSModelCondEngineBase {
    /**
     * 根分组条件
     *
     * @private
     * @type {(PSModelGroupCondBase | null)}
     * @memberof PSModelCondEngineBase
     */
    private psModelGroupCondBase: PSModelGroupCondBase | null = null;

    /**
     * 解析条件
     *
     * @param {any[]} obj
     * @memberof PSModelCondEngineBase
     */
    parse(obj: any[]): void {
        if (obj instanceof Array) {
            const psModelGroupCondBase: PSModelGroupCondBase = this.createPSModelGroupCond();
            psModelGroupCondBase.parse(obj);
            this.psModelGroupCondBase = psModelGroupCondBase;
        }
    }

    /**
     * 测试项
     *
     * @protected
     * @param {string} strCondOp
     * @param {*} objValue
     * @param {*} objCondValue
     * @return {*}  {boolean}
     * @memberof PSModelCondEngineBase
     */
    protected testSingleCond(strCondOp: string, objValue: any, objCondValue: any): boolean {
        try {
            if (CondType.CONDOP_ISNULL === strCondOp) {
                return objValue == null;
            }

            if (CondType.CONDOP_ISNOTNULL === strCondOp) {
                return objValue != null;
            }

            if (
                CondType.CONDOP_EQ === strCondOp ||
                CondType.CONDOP_ABSEQ === strCondOp ||
                CondType.CONDOP_GT === strCondOp ||
                CondType.CONDOP_GTANDEQ === strCondOp ||
                CondType.CONDOP_LT === strCondOp ||
                CondType.CONDOP_LTANDEQ === strCondOp ||
                CondType.CONDOP_NOTEQ === strCondOp
            ) {
                // 大小比较
                const nRet = objValue == objCondValue ? 0 : objValue > objCondValue ? 1 : -1;
                if (CondType.CONDOP_EQ === strCondOp || CondType.CONDOP_ABSEQ === strCondOp) {
                    return nRet === 0;
                }
                if (CondType.CONDOP_GT === strCondOp) {
                    return nRet > 0;
                }
                if (CondType.CONDOP_GTANDEQ === strCondOp) {
                    return nRet >= 0;
                }
                if (CondType.CONDOP_LT === strCondOp) {
                    return nRet < 0;
                }
                if (CondType.CONDOP_LTANDEQ === strCondOp) {
                    return nRet <= 0;
                }
                if (CondType.CONDOP_NOTEQ === strCondOp) {
                    return nRet != 0;
                }
            }

            if (CondType.CONDOP_LIKE === strCondOp) {
                if (objValue != null && objCondValue != null) {
                    if (objValue instanceof String && objCondValue instanceof String) {
                        return objValue.toString().toUpperCase().indexOf(objCondValue.toString().toUpperCase()) != -1;
                    }
                }
                return false;
            }

            if (CondType.CONDOP_LEFTLIKE === strCondOp) {
                if (objValue != null && objCondValue != null) {
                    if (objValue instanceof String && objCondValue instanceof String) {
                        return objValue.toString().toUpperCase().indexOf(objCondValue.toString().toUpperCase()) == 0;
                    }
                }
                return false;
            }

            // throw new Exception(String.format("无法识别的条件操作符[%1$s]",strCondOp));
        } catch (err) {
            LogUtil.log(err);
            return false;
        }
        return false;
    }

    /**
     * 创建分组
     *
     * @protected
     * @abstract
     * @return {*}  {PSModelGroupCondBase}
     * @memberof PSModelCondEngineBase
     */
    protected abstract createPSModelGroupCond(): PSModelGroupCondBase;

    /**
     * 创建逻辑项
     *
     * @protected
     * @abstract
     * @return {*}  {PSModelSingleCondBase}
     * @memberof PSModelCondEngineBase
     */
    protected abstract createPSModelSingleCond(): PSModelSingleCondBase;

    /**
     * 获取根分组条件
     *
     * @return {*}  {PSModelGroupCondBase}
     * @memberof PSModelCondEngineBase
     */
    getPSModelGroupCondBase(): PSModelGroupCondBase {
        return this.psModelGroupCondBase!;
    }
}
