import { IContext, IEntityBase } from '../../interface';
import { notNilEmpty } from 'qx-util';
import { DeRuleCond, ICondBase } from './de-rule-cond';
import { DERuleSingleCond, ISingleCond } from './de-rule-single-cond';

/**
 * 规则组
 *
 * @export
 * @interface IGroupCond
 * @extends {ICondBase}
 */
export interface IGroupCond extends ICondBase {
    /**
     * 名称
     *
     * @type {string}
     * @memberof IGroupCond
     */
    name: string;
    /**
     * 分组匹配模式
     *
     * @default AND
     * @type {('AND' | 'OR')}
     * @memberof IGroupCond
     */
    condOp: 'AND' | 'OR';
    /**
     * 获取子条件
     *
     * @type {((IGroupCond | ISingleCond)[])}
     * @memberof IGroupCond
     */
    getPSDEFVRConditions: (IGroupCond | ISingleCond)[];
}

/**
 * 规则组
 *
 * @export
 * @class DERuleGroupCond
 * @extends {DeRuleCond}
 */
export class DERuleGroupCond extends DeRuleCond {
    /**
     * 规则
     *
     * @protected
     * @type {IGroupCond}
     * @memberof DERuleGroupCond
     */
    protected rule!: IGroupCond;

    /**
     * 子值规则
     *
     * @protected
     * @type {((DERuleGroupCond | DERuleSingleCond)[])}
     * @memberof DERuleGroupCond
     */
    protected childRules!: (DERuleGroupCond | DERuleSingleCond)[];

    /**
     * 编译值规则
     *
     * @protected
     * @memberof DeRuleCond
     */
    protected parse(): void {
        this.childRules = [];
        const children = this.rule.getPSDEFVRConditions;
        if (notNilEmpty(children)) {
            children.forEach(item => {
                if (item.condType === 'GROUP') {
                    this.childRules.push(new DERuleGroupCond(item));
                } else {
                    this.childRules.push(new DERuleSingleCond(item));
                }
            });
        }
    }

    /**
     * 值规则检测
     *
     * @param {IContext} context
     * @param {IEntityBase} entity
     * @return {*}  {boolean}
     * @memberof DeRuleCond
     */
    test(context: IContext, entity: IEntityBase): boolean {
        try {
            if (this.childRules.length > 0) {
                let judge = true;
                if (this.rule.condOp === 'AND') {
                    for (let i = 0; i < this.childRules.length; i++) {
                        const item = this.childRules[i];
                        judge = item.test(context, entity);
                        if (judge === false) {
                            throw new Error(item.ruleInfo);
                        }
                    }
                } else {
                    for (let i = 0; i < this.childRules.length; i++) {
                        const item = this.childRules[i];
                        judge = item.test(context, entity);
                        if (judge === false) {
                            break;
                        }
                    }
                }
                if (this.rule.notMode === true) {
                    judge = !judge;
                }
                if (judge === false) {
                    throw new Error(this.rule.ruleInfo);
                }
            }
        } catch (err) {
            if (notNilEmpty(err.message)) {
                throw new Error(err.message);
            }
            throw new Error(this.ruleInfo);
        }
        return true;
    }
}
