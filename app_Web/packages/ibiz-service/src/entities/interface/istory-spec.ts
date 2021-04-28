import { IEntityBase } from 'ibiz-core';

/**
 * 需求描述
 *
 * @export
 * @interface IStorySpec
 * @extends {IEntityBase}
 */
export interface IStorySpec extends IEntityBase {
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 需求描述	
     */
    spec?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 验收标准
     */
    verify?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 需求名称
     */
    title?: any;
    /**
     * 版本号
     */
    version?: any;
    /**
     * 需求
     */
    story?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 主键
     */
    id?: any;
}
