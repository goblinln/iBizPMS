import { EntityBase } from 'ibiz-core';
import { ITestReult } from '../interface';

/**
 * 测试结果基类
 *
 * @export
 * @abstract
 * @class TestReultBase
 * @extends {EntityBase}
 * @implements {ITestReult}
 */
export abstract class TestReultBase extends EntityBase implements ITestReult {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestReultBase
     */
    get srfdename(): string {
        return 'ZT_TESTRESULT';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 最后执行人
     */
    lastrunner?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 步骤结果
     */
    stepresults?: any;
    /**
     * 测试结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    caseresult?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 结果文件
     */
    xml?: any;
    /**
     * 属性
     */
    task?: any;
    /**
     * 测试结果编号
     */
    testresultsn?: any;
    /**
     * 持续时间
     */
    duration?: any;
    /**
     * 测试时间
     */
    date?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 相关需求
     */
    story?: any;
    /**
     * 用例名称
     */
    title?: any;
    /**
     * 所属模块
     */
    modulename?: any;
    /**
     * 所属模块
     */
    module?: any;
    /**
     * 前置条件
     */
    precondition?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 构建任务
     */
    job?: any;
    /**
     * 用例
     */
    ibizcase?: any;
    /**
     * 测试执行
     */
    run?: any;
    /**
     * 代码编译
     */
    compile?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestReultBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
