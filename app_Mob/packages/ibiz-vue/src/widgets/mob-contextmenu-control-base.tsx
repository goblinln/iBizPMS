import { Util, ModelParser, MobContextMenuControlInterface} from "ibiz-core";
import { MainControlBase } from './main-control-base';
import { IPSDEContextMenu } from '@ibiz/dynamic-model-api';

/**
 * 上下文菜单部件基类
 *
 * @export
 * @class MobContextMenuBase
 * @extends {MainControlBase}
 */
export class MobContextMenuControlBase extends MainControlBase implements MobContextMenuControlInterface{

    /**
     * 部件模型
     *
     * @type {AppContextMenuBase}
     * @memberof ContextMenuBase
     */
    public controlInstance!: IPSDEContextMenu;

    
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
     * @memberof AppControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        this.contextMenuActionModel = newVal?.contextMenuActionModel || {};
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 菜单项点击
     * 
     * @memberof AppContextMenuBase
     */
    public itemClick({ tag }: any) {
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "contextMenuItemClick", data: tag });
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
