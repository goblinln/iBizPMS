import { EntityBase } from 'ibiz-core';
import { IStorySpec } from '../interface';

/**
 * 需求描述基类
 *
 * @export
 * @abstract
 * @class StorySpecBase
 * @extends {EntityBase}
 * @implements {IStorySpec}
 */
export abstract class StorySpecBase extends EntityBase implements IStorySpec {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof StorySpecBase
     */
    get srfdename(): string {
        return 'ZT_STORYSPEC';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 需求描述	
     */
    spec?: any;
    /**
     * 验收标准
     */
    verify?: any;
    /**
     * 虚拟主键
     */
    id?: any;
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
     * 由谁创建
     */
    createby?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属部门
     */
    dept?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof StorySpecBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
