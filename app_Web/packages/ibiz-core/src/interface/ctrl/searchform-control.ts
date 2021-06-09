

/**
 * 搜索表单基类接口
 *
 * @interface SearchFormControlInterface
 */
export interface SearchFormControlInterface {


    /**
     * 加载草稿
     *
     * @param {*} opt 额外参数
     * @memberof SearchFormControlInterface
     */
    loadDraft(opt: any): void;


    /**
     * 保存
     *
     * @param {*} opt 额外参数
     * @param {boolean} showResultInfo 是否显示提示信息
     * @param {boolean} isStateNext formState是否下发通知
     * @return {*}  {Promise<any>}
     * @memberof SearchFormControlInterface
     */
    save(opt: any, showResultInfo: boolean, isStateNext: boolean): Promise<any>;


    /**
     * 表单值变化 
     *
     * @param {{ name: string; newVal: any; oldVal: any }} param 参数
     * @memberof SearchFormControlInterface
     */
    formDataChange(param: { name: string; newVal: any; oldVal: any }): void;


    /**
     * 搜索
     *
     * @memberof SearchFormControlInterface
     */
    onSearch(): void;


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


    /**
     * 重置
     *
     * @memberof SearchFormControlInterface
     */
    onReset():void;

}
