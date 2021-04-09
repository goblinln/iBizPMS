import { IEntityBase } from 'ibiz-core';

/**
 * 测试报告
 *
 * @export
 * @interface ITestReport
 * @extends {IEntityBase}
 */
export interface ITestReport extends IEntityBase {
    /**
     * 项目报告产品数
     */
    productcnt?: any;
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
     * 结束时间
     */
    end?: any;
    /**
     * 版本信息
     */
    builds?: any;
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
}
