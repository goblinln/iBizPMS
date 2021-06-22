import { EntityBase } from 'ibiz-core';
import { IProductLine } from '../interface';

/**
 * 产品线（废弃）基类
 *
 * @export
 * @abstract
 * @class ProductLineBase
 * @extends {EntityBase}
 * @implements {IProductLine}
 */
export abstract class ProductLineBase extends EntityBase implements IProductLine {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductLineBase
     */
    get srfdename(): string {
        return 'IBZ_PRODUCTLINE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * id
     */
    id?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 产品线名称
     */
    name?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductLineBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
