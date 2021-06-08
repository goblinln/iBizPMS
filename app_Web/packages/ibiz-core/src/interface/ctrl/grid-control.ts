/**
 * 表格基类接口
 *
 * @interface GridControlInterface
 */
export interface GridControlInterface {

    /**
     * 表格数据加载
     *
     * @param {*} [opt] 额外参数
     * @param {boolean} [pageReset] 页码是否重置
     * @memberof GridControlInterface
     */
    load(opt: any, pageReset: boolean): void;

    /**
     * 删除
     *
     * @param {any[]} datas 删除数据
     * @memberof GridControlInterface
     */
    remove(datas: any[]): Promise<any>;

    /**
     * 保存
     *
     * @param {*} args 额外参数
     * @memberof GridControlInterface
     */
    save(args: any): Promise<any>;

    /**
     * 新建行
     *
     * @param {any[]} args 新建数据
     * @memberof GridControlInterface
     */
    newRow(args: any[]): void;

    /**
     * 数据导入
     *
     * @memberof GridControlInterface
     */
    importExcel(data: any): void;

    /**
     * 数据导出
     *
     * @param {*} args 额外参数
     * @memberof GridControlInterface
     */
    exportExcel(args: any): void;

    /**
     * 部件刷新
     *
     * @param {*} args 额外参数
     * @memberof GridControlInterface
     */
    refresh(args?: any): void;

    /**
     * 表格分组
     * 
     * @memberof GridControlInterface
     */
    group(): void;

    /**
     * 单个复选框选中
     *
     * @param {*} selection 所有选中行数据
     * @param {*} row 当前选中行数据
     * @memberof GridControlInterface
     */
    select(selection: any, row: any): void;

    /**
     * 复选框数据全部选中
     *
     * @param {*} selection 选中数据
     * @memberof GridControlInterface
     */
    selectAll(selection: any): void;

    /**
     * 行单击选中
     *
     * @param {*} row 行
     * @param {*} column 列
     * @param {*} event 点击事件
     * @param {boolean} isExecute 是否执行
     * @memberof GridControlInterface
     */
    rowClick(row: any, column?: any, event?: any, isExecute?: boolean): void;

    /**
     * 行双击事件
     *
     * @param {*} $event 事件源
     * @memberof GridControlInterface
     */
    rowDBLClick($event: any): void;

    /**
     * 页面变化
     *
     * @param {*} $event 事件源
     * @memberof GridControlInterface
     */
    pageOnChange($event: any): void;

    /**
     * 分页条数变化
     *
     * @param {*} $event 事件源
     * @memberof GridControlInterface
     */
    onPageSizeChange($event: any): void;

    /**
     * 分页刷新
     *
     * @memberof GridControlInterface
     */
    pageRefresh(): void;

    /**
     * 排序变化
     *
     * @param {{ column: any, prop: any, order: any }} { column, prop, order } UI回调数据
     * @memberof GridControlInterface
     */
    onSortChange({ column, prop, order }: { column: any, prop: any, order: any }): void;

    /**
     * 表格编辑项值变更
     *  
     * @param row 行数据
     * @param {{ name: string, value: any }} 变化数据键值
     * @param rowIndex 行索引
     * @memberof GridControlInterface
     */
    onGridItemValueChange(row: any, $event: { name: string, value: any }, rowIndex: number): void;

    /**
     * 表格编辑项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @memberof GridControlInterface
     */
    updateGridEditItem(mode: string, data: any, updateDetails: string[], showloading?: boolean): void;

    /**
     * 新建默认值
     * 
     * @param {*}  row 行数据
     * @memberof GridControlInterface
     */
    createDefault(row: any): void;

    /**
     * 更新默认值
     *
     * @param {*}  row 行数据
     * @memberof GridControlInterface
     */
    updateDefault(row: any): void;

    /**
     * 重置表格项值
     *
     * @param {*} row 当前行
     * @param {string} property 属性名
     * @param {number} rowIndex 行下标
     * @memberof GridControlInterface
     */
    resetGridData(row: any, property: string, rowIndex: number): void;

    /**
     * 处理操作列点击
     * 
     * @param data 行数据
     * @param event 事件源
     * @param column 列对象
     * @param detail 触发成员对象
     * @memberof GridControlInterface
     */
    handleActionClick(data: any, event: any, column: any, detail: any): void;

    /**
     * 表格列内置界面行为
     * 
     * @param data 行数据
     * @param event 事件源
     * @param column 列对象
     * @memberof GridControlInterface
     */
    columnUIAction(data: any, event: any, column: any): void;

}
