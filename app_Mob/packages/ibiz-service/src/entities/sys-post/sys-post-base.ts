import { EntityBase } from 'ibiz-core';
import { ISysPost } from '../interface';

/**
 * 岗位基类
 *
 * @export
 * @abstract
 * @class SysPostBase
 * @extends {EntityBase}
 * @implements {ISysPost}
 */
export abstract class SysPostBase extends EntityBase implements ISysPost {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysPostBase
     */
    get srfdename(): string {
        return 'SYS_POST';
    }
    get srfkey() {
        return this.postid;
    }
    set srfkey(val: any) {
        this.postid = val;
    }
    get srfmajortext() {
        return this.postname;
    }
    set srfmajortext(val: any) {
        this.postname = val;
    }
    /**
     * 岗位标识
     */
    postid?: any;
    /**
     * 岗位编码
     */
    postcode?: any;
    /**
     * 岗位名称
     */
    postname?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 备注
     */
    memo?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysPostBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.postid = data.postid || data.srfkey;
        this.postname = data.postname || data.srfmajortext;
    }
}
