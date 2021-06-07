import { EntityBase } from 'ibiz-core';
import { ITestReport } from '../interface';

/**
 * 测试报告基类
 *
 * @export
 * @abstract
 * @class TestReportBase
 * @extends {EntityBase}
 * @implements {ITestReport}
 */
export abstract class TestReportBase extends EntityBase implements ITestReport {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestReportBase
     */
    get srfdename(): string {
        return 'ZT_TESTREPORT';
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
     * 项目报告产品数
     */
    productcnt?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 概况
     */
    overviews?: any;
    /**
     * 所属对象
     */
    objectid?: any;
    /**
     * 用例
     */
    cases?: any;
    /**
     * 参与人员
     */
    members?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 测试的Bug
     */
    bugs?: any;
    /**
     * 由谁创建
     */
    createdby?: any;
    /**
     * 测试的需求
     */
    stories?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 测试报告编号
     */
    testreportsn?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 测试单
     */
    tasks?: any;
    /**
     * 标题
     */
    title?: any;
    /**
     * 开始时间
     */
    begin?: any;
    /**
     * 对象类型
     *
     * @type {('testtask' | 'project')} testtask: 测试单, project: 项目
     */
    objecttype?: 'testtask' | 'project';
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 结束时间
     */
    end?: any;
    /**
     * 版本信息
     */
    builds?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 创建时间
     */
    createddate?: any;
    /**
     * 总结
     */
    report?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 负责人
     */
    owner?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 所属产品
     */
    productname?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 所属项目
     */
    project?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestReportBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
