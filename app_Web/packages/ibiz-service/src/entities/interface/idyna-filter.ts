import { IEntityBase } from 'ibiz-core';

/**
 * 动态搜索栏
 *
 * @export
 * @interface IDynaFilter
 * @extends {IEntityBase}
 */
export interface IDynaFilter extends IEntityBase {
    /**
     * 动态搜索栏标识
     */
    dynafilterid?: any;
    /**
     * 动态搜索栏名称
     */
    dynafiltername?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 组织部门标识
     */
    deptid?: any;
    /**
     * 表单名称
     */
    formname?: any;
    /**
     * 实体名称
     */
    dename?: any;
    /**
     * 数据
     */
    data?: any;
}
