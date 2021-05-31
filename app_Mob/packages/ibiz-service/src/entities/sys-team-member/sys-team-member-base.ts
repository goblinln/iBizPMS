import { EntityBase } from 'ibiz-core';
import { ISysTeamMember } from '../interface';

/**
 * 组成员基类
 *
 * @export
 * @abstract
 * @class SysTeamMemberBase
 * @extends {EntityBase}
 * @implements {ISysTeamMember}
 */
export abstract class SysTeamMemberBase extends EntityBase implements ISysTeamMember {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysTeamMemberBase
     */
    get srfdename(): string {
        return 'SYS_TEAMMEMBER';
    }
    get srfkey() {
        return this.teammemberid;
    }
    set srfkey(val: any) {
        this.teammemberid = val;
    }
    get srfmajortext() {
        return this.teamid;
    }
    set srfmajortext(val: any) {
        this.teamid = val;
    }
    /**
     * 组成员标识
     */
    teammemberid?: any;
    /**
     * 组名称
     */
    teamname?: any;
    /**
     * 岗位
     */
    postname?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 头像
     */
    usericon?: any;
    /**
     * 姓名
     */
    personname?: any;
    /**
     * 账号
     */
    username?: any;
    /**
     * 岗位标识
     */
    postid?: any;
    /**
     * 组标识
     */
    teamid?: any;
    /**
     * 用户标识
     */
    userid?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysTeamMemberBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.teammemberid = data.teammemberid || data.srfkey;
        this.teamid = data.teamid || data.srfmajortext;
    }
}
