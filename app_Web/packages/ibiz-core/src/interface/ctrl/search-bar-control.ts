import { MDControlInterface } from 'ibiz-core';

/**
 * 搜索栏部件接口
 *
 * @interface SearchBarControlInterface
 */
export interface SearchBarControlInterface extends MDControlInterface {

    /**
     * 删除过滤项
     *
     * @param {number} index 索引
     * @memberof SearchBarControlInterface
     */
    onRemove(index: number): void;

    /**
     * 搜索
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    onSearch(): void;

    /**
     * 保存
     *
     * @param {string} [name] 名称
     * @memberof SearchBarControlInterface
     */
    onSave(name?: string): void;

    /**
     * 重置
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    onReset(): void;

    /**
     * 加载
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    load(): void;

    /**
     * 改变过滤条件
     *
     * @param {*} evt
     * @memberof SearchBarControlInterface
     */
    onFilterChange(evt: any): void;

    /**
     * 打开弹框
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    openPoper(): void;

    /**
     * 确定
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    onOk(): void;

    /**
     * 取消设置
     *
     * @return {*}
     * @memberof SearchBarControlInterface
     */
    onCancel(): void;
}
