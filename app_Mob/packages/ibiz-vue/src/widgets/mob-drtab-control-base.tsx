import { MainControlBase } from "./main-control-base";

/**
 * 数据关系分页部件基类
 *
 * @export
 * @class MobDrtabControlBase
 * @extends {MainControlBase}
 */
export class MobDrtabControlBase extends MainControlBase {
    
    /**
     * 被激活的分页面板
     *
     * @type {string}
     * @memberof MobDrtabControlBase
     */
     public activiedTabViewPanel: string = '';    

    /**
     * 选中节点
     *
     * @param {string} tabPaneName 分页name
     * @memberof MobDrtabControlBase
     */
     public tabPanelClick(tabPaneName: string): void {
        this.activiedTabViewPanel = tabPaneName;
        this.onCtrlEvent(this.controlInstance.name, 'selectionchange', [{id: tabPaneName}])
    }
}
