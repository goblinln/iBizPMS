import { IEntityBase } from 'ibiz-core';

/**
 * 组成员
 *
 * @export
 * @interface ISysTeamMember
 * @extends {IEntityBase}
 */
export interface ISysTeamMember extends IEntityBase {
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
}
