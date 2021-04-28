import { IEntityBase } from 'ibiz-core';

/**
 * 组
 *
 * @export
 * @interface ISysTeam
 * @extends {IEntityBase}
 */
export interface ISysTeam extends IEntityBase {
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
}
