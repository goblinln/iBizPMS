import { EntityBase } from 'ibiz-core';
import { IProjectProduct } from '../interface';

/**
 * 项目产品基类
 *
 * @export
 * @abstract
 * @class ProjectProductBase
 * @extends {EntityBase}
 * @implements {IProjectProduct}
 */
export abstract class ProjectProductBase extends EntityBase implements IProjectProduct {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectProductBase
     */
    get srfdename(): string {
        return 'ZT_PROJECTPRODUCT';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.productname;
    }
    set srfmajortext(val: any) {
        this.productname = val;
    }
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
     * 虚拟主键
     */
    id?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 项目
     */
    projectname?: any;
    /**
     * 计划名称
     */
    planname?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 产品计划
     */
    plan?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 项目
     */
    project?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectProductBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.productname = data.productname || data.srfmajortext;
    }
}
