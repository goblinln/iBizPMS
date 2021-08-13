import { IEntityBase } from 'ibiz-core';

/**
 * 需求阶段
 *
 * @export
 * @interface IStoryStage
 * @extends {IEntityBase}
 */
export interface IStoryStage extends IEntityBase {
    /**
     * 主键
     */
    id?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 设置阶段者
     */
    stagedby?: any;
    /**
     * 所处阶段
     */
    stage?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 需求
     */
    story?: any;
    /**
     * 平台/分支
     */
    branch?: any;
}
