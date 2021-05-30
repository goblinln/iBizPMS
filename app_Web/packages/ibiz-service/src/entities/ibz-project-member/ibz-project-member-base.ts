import { EntityBase } from 'ibiz-core';
import { IIbzProjectMember } from '../interface';

/**
 * 项目相关成员基类
 *
 * @export
 * @abstract
 * @class IbzProjectMemberBase
 * @extends {EntityBase}
 * @implements {IIbzProjectMember}
 */
export abstract class IbzProjectMemberBase extends EntityBase implements IIbzProjectMember {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzProjectMemberBase
     */
    get srfdename(): string {
        return 'IBZ_PROJECTMEMBER';
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
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 访问控制
     *
     * @type {('open' | 'private' | 'custom')} open: 默认设置(有项目视图权限，即可访问), private: 私有项目(只有项目团队成员才能访问), custom: 自定义白名单(团队成员和白名单的成员可以访问)
     */
    acl?: 'open' | 'private' | 'custom';
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 由谁取消
     */
    canceledby?: any;
    /**
     * 由谁更新
     */
    updateby?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzProjectMemberBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
