import { EntityBase } from 'ibiz-core';
import { IEmpLoyeeload } from '../interface';

/**
 * 员工负载表基类
 *
 * @export
 * @abstract
 * @class EmpLoyeeloadBase
 * @extends {EntityBase}
 * @implements {IEmpLoyeeload}
 */
export abstract class EmpLoyeeloadBase extends EntityBase implements IEmpLoyeeload {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof EmpLoyeeloadBase
     */
    get srfdename(): string {
        return 'IBZ_EMPLOYEELOAD';
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
     * 任务名
     */
    name?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
    /**
     * 部门
     */
    dept?: any;
    /**
     * 主键
     */
    id?: any;
    /**
     * 工作日天数
     */
    workday?: any;
    /**
     * 总任务数
     */
    totaltaskcnt?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 剩余工时
     */
    left?: any;
    /**
     * 是否指派
     */
    assign?: any;
    /**
     * 属性
     */
    begin?: any;
    /**
     * 总工时
     */
    totalleft?: any;
    /**
     * 工作负载
     */
    workload?: any;
    /**
     * 结束
     */
    end?: any;
    /**
     * 项目
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof EmpLoyeeloadBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
