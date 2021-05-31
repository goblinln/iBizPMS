import { IEntityBase } from 'ibiz-core';

/**
 * 用例库用例步骤
 *
 * @export
 * @interface IIbzLibCaseStepTmp
 * @extends {IEntityBase}
 */
export interface IIbzLibCaseStepTmp extends IEntityBase {
    /**
     * 实际情况
     */
    reals?: any;
    /**
     * 预期
     */
    expect?: any;
    /**
     * 步骤
     */
    desc?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 类型
     *
     * @type {('step' | 'group' | 'item')} step: 步骤, group: 分组, item: 分组步骤
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 版本
     */
    version?: any;
    /**
     * 编号
     */
    parent?: any;
    /**
     * 用例编号
     */
    ibizcase?: any;
}
