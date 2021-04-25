import { IEntityBase } from 'ibiz-core';

/**
 * 用例步骤
 *
 * @export
 * @interface ICaseStep
 * @extends {IEntityBase}
 */
export interface ICaseStep extends IEntityBase {
    /**
     * 用例步骤编号
     */
    casestepid?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 实际情况
     */
    reals?: any;
    /**
     * 测试结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    steps?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 用例步骤类型
     *
     * @type {('step' | 'group' | 'item')} step: 步骤, group: 分组, item: 分组步骤
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 步骤
     */
    desc?: any;
    /**
     * 预期
     */
    expect?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 执行编号
     */
    runid?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 用例
     */
    ibizcase?: any;
    /**
     * 分组用例步骤的组编号
     */
    parent?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
}
