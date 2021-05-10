import { EntityBase } from 'ibiz-core';
import { ICompany } from '../interface';

/**
 * 公司基类
 *
 * @export
 * @abstract
 * @class CompanyBase
 * @extends {EntityBase}
 * @implements {ICompany}
 */
export abstract class CompanyBase extends EntityBase implements ICompany {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof CompanyBase
     */
    get srfdename(): string {
        return 'ZT_COMPANY';
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof CompanyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
