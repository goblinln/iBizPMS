import { EditFormControlInterface } from "./editform-control";

/**
 * 搜索表单基类接口
 *
 * @interface SearchFormControlInterface
 */
export interface SearchFormControlInterface extends EditFormControlInterface {

    /**
     * 确定
     *
     * @memberof SearchFormControlInterface
     */
    onOk(): void;

    /**
     * 取消设置
     *
     * @memberof SearchFormControlInterface
     */
    onCancel(): void;

    /**
     * 删除记录
     *
     * @param {*} event
     * @param {*} item
     * @memberof SearchFormControlInterface
     */
    removeHistoryItem(event: any, item: any): void;

    /**
     * 保存
     *
     * @param {string} [name] 名称
     * @memberof SearchFormControlInterface
     */
    onSave(name?: string): void;

    /**
     * 改变过滤条件
     *
     * @param {*} evt 改变值
     * @memberof SearchFormControlInterface
     */
    onFilterChange(evt: any): void;

}
