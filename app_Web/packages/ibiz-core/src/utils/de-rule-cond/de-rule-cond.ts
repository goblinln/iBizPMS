import { IContext, IEntityBase } from '../../interface';

/**
 * 值规则项基础接口
 *
 * @export
 * @interface ICondBase
 */
export interface ICondBase {
    /**
     * 值规则类型
     *       {('条件组' | '空值判断'  | '数据集范围'  | '数值范围'    | '正则式' | '字符长度'      | '常规条件' | '值清单'      | '查询计数'    | '值递归检查'      | '系统值规则' )}
     * @type {('GROUP' | 'NULLRULE' | 'VALUERANGE' | 'VALUERANGE2' | 'REGEX' | 'STRINGLENGTH' | 'SIMPLE'  | 'VALUERANGE3' | 'QUERYCOUNT' | 'VALUERECURSION' | 'SYSVALUERULE')}
     * @memberof ICondBase
     */
    condType: | 'GROUP'
    | 'NULLRULE'
    | 'VALUERANGE'
    | 'VALUERANGE2'
    | 'REGEX'
    | 'STRINGLENGTH'
    | 'SIMPLE'
    | 'VALUERANGE3'
    | 'QUERYCOUNT'
    | 'VALUERECURSION'
    | 'SYSVALUERULE';
    /**
     * 错误信息
     *
     * @type {string}
     * @memberof ICondBase
     */
    ruleInfo: string;
    /**
     * 条件模式
     *
     * @type {string}
     * @memberof ICondBase
     */
    condOp: string;
    /**
     * 结果是否取反
     *
     * @default false
     * @type {boolean}
     * @memberof ICondBase
     */
    notMode?: boolean;
    /**
     * 条件值
     *
     * @type {string}
     * @memberof ICondBase
     */
    condvalue?: string;
}
/**
 * 实体值规则基类
 *
 * @export
 * @class DeRuleCond
 */
export class DeRuleCond {
    /**
     * 规则
     *
     * @protected
     * @type {*}
     * @memberof DeRuleCond
     */
    protected rule: any;
    /**
     * 值规则信息
     *
     * @readonly
     * @type {string}
     * @memberof DeRuleCond
     */
    get ruleInfo(): string {
        return this.rule.ruleInfo;
    }
    /**
     * Creates an instance of DeRuleCond.
     * @param {*} rule
     * @memberof DeRuleCond
     */
    constructor(rule: any) {
        this.rule = rule;
        this.parse();
    }

    /**
     * 编译值规则
     *
     * @protected
     * @memberof DeRuleCond
     */
    protected parse(): void {}

    /**
     * 测试值规则
     *
     * @param {IContext} _context
     * @param {IEntityBase} _entity
     * @return {*}  {boolean}
     * @memberof DeRuleCond
     */
    test(_context: IContext, _entity: IEntityBase): boolean {
        return true;
    }
}
