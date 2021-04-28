import { EntityBase } from 'ibiz-core';
import { ISuiteCase } from '../interface';

/**
 * 套件用例基类
 *
 * @export
 * @abstract
 * @class SuiteCaseBase
 * @extends {EntityBase}
 * @implements {ISuiteCase}
 */
export abstract class SuiteCaseBase extends EntityBase implements ISuiteCase {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SuiteCaseBase
     */
    get srfdename(): string {
        return 'ZT_SUITECASE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.ibizcase;
    }
    set srfmajortext(val: any) {
        this.ibizcase = val;
    }
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 主键
     */
    id?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 测试套件
     */
    suite?: any;
    /**
     * 用例
     */
    ibizcase?: any;
    /**
     * 所属产品
     */
    product?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SuiteCaseBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.ibizcase = data.ibizcase || data.srfmajortext;
    }
}
