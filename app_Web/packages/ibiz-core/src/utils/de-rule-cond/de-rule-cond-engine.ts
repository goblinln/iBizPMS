import { IContext, IEntityBase } from '../../interface';
import { DERuleGroupCond } from './de-rule-group-cond';

/**
 * 值规则执行引擎
 *
 * @export
 * @class DERuleCondEngine
 */
export class DERuleCondEngine {
    /**
     * 默认规则组
     *
     * @type {DERuleGroupCond}
     * @memberof DERuleCondEngine
     */
    root!: DERuleGroupCond;

    /**
     * Creates an instance of DERuleCondEngine.
     * @param {any[]} rules
     * @memberof DERuleCondEngine
     */
    constructor(rules: any[]) {
        this.parse(rules);
    }

    /**
     * 解析值规则
     *
     * @private
     * @param {any[]} rules
     * @memberof DERuleCondEngine
     */
    private parse(rules: any[]): void {
        this.root = new DERuleGroupCond({
            name: '默认组',
            condOp: 'AND',
            getPSDEFVRConditions: rules,
        });
    }

    /**
     * 值规则检测
     *
     * @param {IContext} context
     * @param {IEntityBase} entity
     * @return {*}  {boolean}
     * @memberof DERuleCondEngine
     */
    test(context: IContext, entity: IEntityBase): boolean {
        if (this.root) {
            this.root.test(context, entity);
        }
        return true;
    }
}
