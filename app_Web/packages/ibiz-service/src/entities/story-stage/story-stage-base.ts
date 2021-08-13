import { EntityBase } from 'ibiz-core';
import { IStoryStage } from '../interface';

/**
 * 需求阶段基类
 *
 * @export
 * @abstract
 * @class StoryStageBase
 * @extends {EntityBase}
 * @implements {IStoryStage}
 */
export abstract class StoryStageBase extends EntityBase implements IStoryStage {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof StoryStageBase
     */
    get srfdename(): string {
        return 'ZT_STORYSTAGE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.story;
    }
    set srfmajortext(val: any) {
        this.story = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof StoryStageBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.story = data.story || data.srfmajortext;
    }
}
