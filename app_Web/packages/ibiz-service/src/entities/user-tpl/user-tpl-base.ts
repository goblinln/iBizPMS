import { EntityBase } from 'ibiz-core';
import { IUserTpl } from '../interface';

/**
 * 用户模板基类
 *
 * @export
 * @abstract
 * @class UserTplBase
 * @extends {EntityBase}
 * @implements {IUserTpl}
 */
export abstract class UserTplBase extends EntityBase implements IUserTpl {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof UserTplBase
     */
    get srfdename(): string {
        return 'ZT_USERTPL';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 模板标题
     */
    title?: any;
    /**
     * id
     */
    id?: any;
    /**
     * content
     */
    content?: any;
    /**
     * type
     *
     * @type {('story' | 'task' | 'bug')} story: 需求, task: 任务, bug: Bug
     */
    type?: 'story' | 'task' | 'bug';
    /**
     * account
     */
    account?: any;
    /**
     * 公开
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    ibizpublic?: '1' | '0';

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof UserTplBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
