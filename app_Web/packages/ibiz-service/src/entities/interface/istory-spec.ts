import { IEntityBase } from 'ibiz-core';

/**
 * 需求描述
 *
 * @export
 * @interface IStorySpec
 * @extends {IEntityBase}
 */
export interface IStorySpec extends IEntityBase {
    /**
     * 需求描述	
     */
    spec?: any;
    /**
     * 验收标准
     */
    verify?: any;
    /**
     * 虚拟主键
     */
    id?: any;
    /**
     * 需求名称
     */
    title?: any;
    /**
     * 版本号
     */
    version?: any;
    /**
     * 需求
     */
    story?: any;
}
