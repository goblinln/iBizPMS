import { EntityBase } from 'ibiz-core';
import { ITestCaseLibCaseStepNested } from '../interface';

/**
 * 用例库用例步骤基类
 *
 * @export
 * @abstract
 * @class TestCaseLibCaseStepNestedBase
 * @extends {EntityBase}
 * @implements {ITestCaseLibCaseStepNested}
 */
export abstract class TestCaseLibCaseStepNestedBase extends EntityBase implements ITestCaseLibCaseStepNested {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestCaseLibCaseStepNestedBase
     */
    get srfdename(): string {
        return 'IBZ_LIBCASESTEPS';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.expect;
    }
    set srfmajortext(val: any) {
        this.expect = val;
    }
    /**
     * 实际情况
     */
    reals?: any;
    /**
     * 预期
     */
    expect?: any;
    /**
     * 步骤
     */
    desc?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 类型
     *
     * @type {('step' | 'group' | 'item')} step: 步骤, group: 分组, item: 分组步骤
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 版本
     */
    version?: any;
    /**
     * 编号
     */
    parent?: any;
    /**
     * 用例编号
     */
    ibizcase?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestCaseLibCaseStepNestedBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.expect = data.expect || data.srfmajortext;
    }
}
