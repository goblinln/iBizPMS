import { Util, ModelParser, DynamicService, IBizContextMenuModel } from "ibiz-core";
import { MainControlBase } from './MainControlBase';


/**
 * 上下文菜单部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class ContextMenuBase extends MainControlBase {

    /**
     * 部件模型
     *
     * @type {AppContextMenuBase}
     * @memberof ContextMenuBase
     */
    public controlInstance!: IBizContextMenuModel;

    
    /**
     * 上下文菜单界面行为模型
     *
     * @type {AppContextMenuBase}
     * @memberof ContextMenuBase
     */
    public contextMenuActionModel!: any;

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        this.contextMenuActionModel = newVal?.contextMenuActionModel || {};
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof ContextMenuBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
    }

}
