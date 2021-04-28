import { EntityBase } from 'ibiz-core';
import { ITestSuite } from '../interface';

/**
 * 测试套件基类
 *
 * @export
 * @abstract
 * @class TestSuiteBase
 * @extends {EntityBase}
 * @implements {ITestSuite}
 */
export abstract class TestSuiteBase extends EntityBase implements ITestSuite {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestSuiteBase
     */
    get srfdename(): string {
        return 'ZT_TESTSUITE';
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
     * 归属部门名
     */
    deptname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 创建时间
     */
    addeddate?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 最后编辑人
     */
    lasteditedby?: any;
    /**
     * 类型
     *
     * @type {('library' | 'private' | 'public')} library: 用例库, private: 私有, public: 公开
     */
    type?: 'library' | 'private' | 'public';
    /**
     * 最后编辑时间
     */
    lastediteddate?: any;
    /**
     * 由谁创建
     */
    addedby?: any;
    /**
     * 用例数
     */
    casecnt?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 所属产品
     */
    product?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestSuiteBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
