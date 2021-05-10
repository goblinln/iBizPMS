import { IEntityBase } from 'ibiz-core';

/**
 * 公司
 *
 * @export
 * @interface ICompany
 * @extends {IEntityBase}
 */
export interface ICompany extends IEntityBase {
    /**
     * admins
     */
    admins?: any;
    /**
     * 传真
     */
    fax?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 官网
     */
    website?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 邮政编码
     */
    zipcode?: any;
    /**
     * 通讯地址
     */
    address?: any;
    /**
     * 内网
     */
    backyard?: any;
    /**
     * 公司名称
     */
    name?: any;
    /**
     * 匿名登陆
     *
     * @type {('1' | '0')} 1: 允许, 0: 不允许
     */
    guest?: '1' | '0';
    /**
     * 联系电话
     */
    phone?: any;
    /**
     * 公司编号
     */
    companysn?: any;
}
