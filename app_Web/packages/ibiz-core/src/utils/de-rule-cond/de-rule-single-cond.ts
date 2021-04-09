import { IContext, IEntityBase } from '../../interface';
import { CondType } from '../de-dq-cond/cond-type';
import { LogUtil } from '../log-util/log-util';
import { DeRuleCond, ICondBase } from './de-rule-cond';

/**
 * 规则项
 *
 * @export
 * @interface ISingleCond
 * @extends {ICondBase}
 */
export interface ISingleCond extends ICondBase {
    /**
     * 是否启用最小值验证
     *
     * @type {boolean}
     * @memberof ISingleCond
     */
    includeMinValue?: boolean;
    /**
     * 是否启用最大值验证
     *
     * @type {boolean}
     * @memberof ISingleCond
     */
    includeMaxValue?: boolean;
    /**
     * 最大值
     *
     * @type {number}
     * @memberof ISingleCond
     */
    maxValue?: number;
    /**
     * 最小值
     *
     * @type {number}
     * @memberof ISingleCond
     */
    minValue?: number;
    /**
     * 实体属性
     *
     * @type {string}
     * @memberof ISingleCond
     */
    dEFName: string;
    /**
     * 正则式
     *
     * @type {string}
     * @memberof ISingleCond
     */
    regExCode?: string;
    /**
     * 分隔符
     *
     * @type {string}
     * @memberof ISingleCond
     */
    param?: string;
    /**
     * 值类型
     *
     * @type {('ENTITYFIELD' | 'CURTIME')} 实体属性 | 当前时间
     * @memberof ISingleCond
     */
    paramType?: 'ENTITYFIELD' | 'CURTIME';
    /**
     * 常规值规则取值
     *
     * @type {string}
     * @memberof ISingleCond
     */
    paramValue?: string;
}

/**
 * 规则项
 *
 * @export
 * @class DERuleSingleCond
 * @extends {DeRuleCond}
 */
export class DERuleSingleCond extends DeRuleCond {
    /**
     * 规则
     *
     * @protected
     * @type {IGroupCond}
     * @memberof DERuleSingleCond
     */
    protected rule!: ISingleCond;
    /**
     * 测试值，实时计算
     *
     * @protected
     * @type {*}
     * @memberof DERuleSingleCond
     */
    protected value: any;

    /**
     * 值规则检测
     *
     * @param {IContext} context
     * @param {IEntityBase} entity
     * @return {*}  {boolean}
     * @memberof DeRuleCond
     */
    test(context: IContext, entity: IEntityBase): boolean {
        this.calcValue(context, entity);
        const ruleType = this.rule.condType;
        switch (ruleType) {
            case 'SIMPLE':
                return this.simpleTest(context, entity);
            case 'REGEX':
                return this.regexTest(context, entity);
            case 'VALUERANGE2':
                return this.valueRange2Test(context, entity);
            case 'VALUERANGE3':
                return this.valueRange3Test(context, entity);
            case 'STRINGLENGTH':
                return this.stringLengthTest(context, entity);
        }
        return true;
    }

    /**
     * 计算值
     *
     * @protected
     * @param {IContext} _context
     * @param {IEntityBase} entity
     * @memberof DERuleSingleCond
     */
    protected calcValue(_context: IContext, entity: IEntityBase): void {
        this.value = entity[this.rule.dEFName?.toLowerCase()];
    }

    /**
     * 常规值规则
     *
     * @protected
     * @param {IContext} context
     * @param {IEntityBase} entity
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected simpleTest(_context: IContext, _entity: IEntityBase): boolean {
        try {
            const strCondOp = this.rule.condOp;
            let objValue: any;
            let objCondValue: any;
            if (this.rule.paramType === 'CURTIME') {
                objValue = new Date(this.value).getTime();
                objCondValue = new Date().getTime();
            } else {
                objCondValue = this.rule.paramValue;
                objValue = this.value;
            }
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
            LogUtil.error(`无法识别的条件操作符[${strCondOp}]`);
        } catch (err) {
            LogUtil.log(err);
            return false;
        }
        return true;
    }

    /**
     * 正则测试
     *
     * @protected
     * @param {IContext} _context
     * @param {IEntityBase} _entity
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected regexTest(_context: IContext, _entity: IEntityBase): boolean {
        if (this.value) {
            return this.notMode(new RegExp(this.rule.regExCode!).test(this.value));
        }
        LogUtil.warn(`[${this.rule.dEFName}]属性无值，不做正则测试!`);
        return true;
    }

    /**
     * 数值范围
     *
     * @protected
     * @param {IContext} _context
     * @param {IEntityBase} _entity
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected valueRange2Test(_context: IContext, _entity: IEntityBase): boolean {
        try {
            if (this.value != null) {
                let judge = true;
                const v = parseFloat(this.value);
                if (this.rule.maxValue != null) {
                    if (this.rule.includeMaxValue) {
                        judge = v <= this.rule.maxValue;
                    } else {
                        judge = v < this.rule.maxValue;
                    }
                }
                if (judge === true && this.rule.minValue != null) {
                    if (this.rule.includeMinValue) {
                        judge = v >= this.rule.minValue;
                    } else {
                        judge = v > this.rule.minValue;
                    }
                }
                return this.notMode(judge);
            }
        } catch (err) {
            LogUtil.error(err);
            return false;
        }
        return false;
    }

    /**
     * 值清单
     *
     * @protected
     * @param {IContext} _context
     * @param {IEntityBase} _entity
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected valueRange3Test(_context: IContext, _entity: IEntityBase): boolean {
        if (this.rule.condvalue && this.value) {
            const splitStr = this.rule.param || ';';
            const values: string[] = this.rule.condvalue.split(splitStr);
            for (let i = 0; i < values.length; i++) {
                const val = values[i];
                if (val == this.value) {
                    return this.notMode(true);
                }
            }
            return this.notMode(false);
        }
        return true;
    }

    /**
     * 字符串长度
     *
     * @protected
     * @param {IContext} _context
     * @param {IEntityBase} _entity
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected stringLengthTest(_context: IContext, _entity: IEntityBase): boolean {
        if (this.value != null) {
            let judge = true;
            const v = this.value.length;
            if (this.rule.maxValue != null) {
                if (this.rule.includeMaxValue) {
                    judge = v <= this.rule.maxValue;
                } else {
                    judge = v < this.rule.maxValue;
                }
            }
            if (judge === true && this.rule.minValue != null) {
                if (this.rule.includeMinValue) {
                    judge = v >= this.rule.minValue;
                } else {
                    judge = v > this.rule.minValue;
                }
            }
            return this.notMode(judge);
        }
        return true;
    }

    /**
     * 取反处理
     *
     * @protected
     * @param {boolean} bol
     * @return {*}  {boolean}
     * @memberof DERuleSingleCond
     */
    protected notMode(bol: boolean): boolean {
        if (this.rule.notMode === true) {
            return !bol;
        }
        return bol;
    }
}
