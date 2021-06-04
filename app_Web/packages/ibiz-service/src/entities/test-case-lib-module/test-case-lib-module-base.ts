import { EntityBase } from 'ibiz-core';
import { ITestCaseLibModule } from '../interface';

/**
 * 用例库模块基类
 *
 * @export
 * @abstract
 * @class TestCaseLibModuleBase
 * @extends {EntityBase}
 * @implements {ITestCaseLibModule}
 */
export abstract class TestCaseLibModuleBase extends EntityBase implements ITestCaseLibModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestCaseLibModuleBase
     */
    get srfdename(): string {
        return 'IBZ_LIBMODULE';
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
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * id
     */
    id?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 类型（story）
     */
    type?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * path
     */
    path?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
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
     * @memberof TestCaseLibModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
