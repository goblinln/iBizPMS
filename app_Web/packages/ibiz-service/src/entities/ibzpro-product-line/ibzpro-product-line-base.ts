import { EntityBase } from 'ibiz-core';
import { IIBZProProductLine } from '../interface';

/**
 * 产品线基类
 *
 * @export
 * @abstract
 * @class IBZProProductLineBase
 * @extends {EntityBase}
 * @implements {IIBZProProductLine}
 */
export abstract class IBZProProductLineBase extends EntityBase implements IIBZProProductLine {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZProProductLineBase
     */
    get srfdename(): string {
        return 'IBZPRO_PRODUCTLINE';
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
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 模块名称
     */
    name?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZProProductLineBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
