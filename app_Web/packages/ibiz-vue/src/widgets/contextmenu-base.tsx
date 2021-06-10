import { IPSDEContextMenu } from '@ibiz/dynamic-model-api';
import { ContextMenuControlInterface } from 'ibiz-core';
import { MainControlBase } from './main-control-base';

/**
 * 上下文菜单部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class ContextMenuBase extends MainControlBase implements ContextMenuControlInterface{

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

    /**
     * 设置已经绘制完成状态
     *
     * @memberof ContextMenuBase
     */    
    public setIsMounted(){
      // 上下文菜单绘制完成不需要外面部件重新加载
    }

}
