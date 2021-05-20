import { EntityBase } from 'ibiz-core';
import { ITestModule } from '../interface';

/**
 * 测试模块基类
 *
 * @export
 * @abstract
 * @class TestModuleBase
 * @extends {EntityBase}
 * @implements {ITestModule}
 */
export abstract class TestModuleBase extends EntityBase implements ITestModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestModuleBase
     */
    get srfdename(): string {
        return 'IBZ_TESTMODULE';
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
     * 类型（story）
     */
    type?: any;
    /**
     * path
     */
    path?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 测试
     */
    rootname?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
