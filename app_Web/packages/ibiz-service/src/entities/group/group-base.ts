import { EntityBase } from 'ibiz-core';
import { IGroup } from '../interface';

/**
 * 群组基类
 *
 * @export
 * @abstract
 * @class GroupBase
 * @extends {EntityBase}
 * @implements {IGroup}
 */
export abstract class GroupBase extends EntityBase implements IGroup {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof GroupBase
     */
    get srfdename(): string {
        return 'ZT_GROUP';
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
     * 群组编号
     */
    groupsn?: any;
    /**
     * acl
     */
    acl?: any;
    /**
     * 分组描述
     */
    desc?: any;
    /**
     * ID
     */
    id?: any;
    /**
     * 分组名称
     */
    name?: any;
    /**
     * role
     */
    role?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof GroupBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
