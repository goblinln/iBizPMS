import { EntityBase } from 'ibiz-core';
import { ITestCaseLib } from '../interface';

/**
 * 用例库基类
 *
 * @export
 * @abstract
 * @class TestCaseLibBase
 * @extends {EntityBase}
 * @implements {ITestCaseLib}
 */
export abstract class TestCaseLibBase extends EntityBase implements ITestCaseLib {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestCaseLibBase
     */
    get srfdename(): string {
        return 'IBZ_LIB';
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
     * 描述
     */
    desc?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 创建时间
     */
    addeddate?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 最后编辑时间
     */
    lastediteddate?: any;
    /**
     * 由谁创建
     */
    addedby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 类型
     *
     * @type {('library' | 'private' | 'public')} library: 用例库, private: 私有, public: 公开
     */
    type?: 'library' | 'private' | 'public';
    /**
     * 最后编辑人
     */
    lasteditedby?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestCaseLibBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
