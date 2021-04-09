import { IEntityBase } from 'ibiz-core';

/**
 * 岗位
 *
 * @export
 * @interface ISysPost
 * @extends {IEntityBase}
 */
export interface ISysPost extends IEntityBase {
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
}
