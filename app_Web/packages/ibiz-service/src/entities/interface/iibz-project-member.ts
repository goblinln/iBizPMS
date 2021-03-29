import { IEntityBase } from 'ibiz-core';

/**
 * 项目相关成员
 *
 * @export
 * @interface IIbzProjectMember
 * @extends {IEntityBase}
 */
export interface IIbzProjectMember extends IEntityBase {
    /**
     * 团队成员（二）
     */
    secondmember?: any;
    /**
     * 发布负责人
     */
    rd?: any;
    /**
     * 测试负责人
     */
    qd?: any;
    /**
     * 全部成员
     */
    teamembers?: any;
    /**
     * 团队成员（三）
     */
    thirdmember?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 团队成员（一）
     */
    fristmember?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 团队成员（四）
     */
    fourthmember?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 团队成员（五）
     */
    fifthmember?: any;
    /**
     * 团队成员（六）
     */
    sixthmember?: any;
    /**
     * 项目名称
     */
    name?: any;
}
