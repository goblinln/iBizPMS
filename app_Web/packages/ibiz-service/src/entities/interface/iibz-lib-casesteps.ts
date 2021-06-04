import { IEntityBase } from 'ibiz-core';

/**
 * 用例库用例步骤
 *
 * @export
 * @interface IIbzLibCasesteps
 * @extends {IEntityBase}
 */
export interface IIbzLibCasesteps extends IEntityBase {
    /**
     * 预期
     */
    expect?: any;
    /**
     * 类型
     *
     * @type {('step' | 'group' | 'item')} step: 步骤, group: 分组, item: 分组步骤
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 附件
     */
    files?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 步骤
     */
    desc?: any;
    /**
     * 实际情况
     */
    reals?: any;
}
