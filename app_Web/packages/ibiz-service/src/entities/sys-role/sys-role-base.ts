import { EntityBase } from 'ibiz-core';
import { ISysRole } from '../interface';

/**
 * 系统角色基类
 *
 * @export
 * @abstract
 * @class SysRoleBase
 * @extends {EntityBase}
 * @implements {ISysRole}
 */
export abstract class SysRoleBase extends EntityBase implements ISysRole {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysRoleBase
     */
    get srfdename(): string {
        return 'SYS_ROLE';
    }
    get srfkey() {
        return this.roleid;
    }
    set srfkey(val: any) {
        this.roleid = val;
    }
    get srfmajortext() {
        return this.rolename;
    }
    set srfmajortext(val: any) {
        this.rolename = val;
    }
    /**
     * 角色标识
     */
    roleid?: any;
    /**
     * 角色名称
     */
    rolename?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 父角色标识
     */
    proleid?: any;
    /**
     * 父角色名称
     */
    prolename?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysRoleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.roleid = data.roleid || data.srfkey;
        this.rolename = data.rolename || data.srfmajortext;
    }
}
