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
    // SuiteCase 实体未设置主文本属性
    /**
     * 虚拟主键
     */
    id?: any;
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
        // SuiteCase 实体未设置主文本属性
    }
}
