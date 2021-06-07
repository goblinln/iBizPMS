import { EntityBase } from 'ibiz-core';
import { IDept } from '../interface';

/**
 * 部门基类
 *
 * @export
 * @abstract
 * @class DeptBase
 * @extends {EntityBase}
 * @implements {IDept}
 */
export abstract class DeptBase extends EntityBase implements IDept {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DeptBase
     */
    get srfdename(): string {
        return 'ZT_DEPT';
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
     * 负责人
     */
    manager?: any;
    /**
     * 无子部门
     */
    isleaf?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * function
     */
    function?: any;
    /**
     * order
     */
    order?: any;
    /**
     * path
     */
    path?: any;
    /**
     * position
     */
    position?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 部门编号
     */
    deptsn?: any;
    /**
     * 部门名称
     */
    name?: any;
    /**
     * 上级部门
     */
    parentname?: any;
    /**
     * parent
     */
    parent?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DeptBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
