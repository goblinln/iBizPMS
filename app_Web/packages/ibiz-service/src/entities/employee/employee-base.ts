import { EntityBase } from 'ibiz-core';
import { IEmployee } from '../interface';

/**
 * 人员基类
 *
 * @export
 * @abstract
 * @class EmployeeBase
 * @extends {EntityBase}
 * @implements {IEmployee}
 */
export abstract class EmployeeBase extends EntityBase implements IEmployee {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof EmployeeBase
     */
    get srfdename(): string {
        return 'IBZEMP';
    }
    get srfkey() {
        return this.userid;
    }
    set srfkey(val: any) {
        this.userid = val;
    }
    get srfmajortext() {
        return this.personname;
    }
    set srfmajortext(val: any) {
        this.personname = val;
    }
    /**
     * 用户标识
     */
    userid?: any;
    /**
     * 用户全局名
     */
    username?: any;
    /**
     * 姓名
     */
    personname?: any;
    /**
     * 用户工号
     */
    usercode?: any;
    /**
     * 登录名
     */
    loginname?: any;
    /**
     * 密码
     */
    password?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 主部门
     */
    mdeptid?: any;
    /**
     * 主部门代码
     */
    mdeptcode?: any;
    /**
     * 主部门名称
     */
    mdeptname?: any;
    /**
     * 业务编码
     */
    bcode?: any;
    /**
     * 单位
     */
    orgid?: any;
    /**
     * 单位代码
     */
    orgcode?: any;
    /**
     * 单位名称
     */
    orgname?: any;
    /**
     * 昵称别名
     */
    nickname?: any;
    /**
     * 性别
     */
    sex?: any;
    /**
     * 联系方式
     */
    phone?: any;
    /**
     * 邮件
     */
    email?: any;
    /**
     * 社交账号
     */
    avatar?: any;
    /**
     * 地址
     */
    addr?: any;
    /**
     * 照片
     */
    usericon?: any;
    /**
     * ip地址
     */
    ipaddr?: any;
    /**
     * 语言
     */
    lang?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 保留
     */
    reserver?: any;
    /**
     * 排序
     */
    showorder?: any;
    /**
     * 逻辑有效
     */
    enable?: any;
    /**
     * 创建时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立人
     */
    createman?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof EmployeeBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.userid = data.userid || data.srfkey;
        this.personname = data.personname || data.srfmajortext;
    }
}
