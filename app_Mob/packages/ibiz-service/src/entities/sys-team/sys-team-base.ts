import { EntityBase } from 'ibiz-core';
import { ISysTeam } from '../interface';

/**
 * 组基类
 *
 * @export
 * @abstract
 * @class SysTeamBase
 * @extends {EntityBase}
 * @implements {ISysTeam}
 */
export abstract class SysTeamBase extends EntityBase implements ISysTeam {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysTeamBase
     */
    get srfdename(): string {
        return 'SYS_TEAM';
    }
    get srfkey() {
        return this.teamid;
    }
    set srfkey(val: any) {
        this.teamid = val;
    }
    get srfmajortext() {
        return this.teamname;
    }
    set srfmajortext(val: any) {
        this.teamname = val;
    }
    /**
     * 组标识
     */
    teamid?: any;
    /**
     * 组名称
     */
    teamname?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysTeamBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.teamid = data.teamid || data.srfkey;
        this.teamname = data.teamname || data.srfmajortext;
    }
}
