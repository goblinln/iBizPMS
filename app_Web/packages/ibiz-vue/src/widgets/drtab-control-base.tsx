import { MainControlBase } from "./main-control-base";

/**
 * 数据关系分页部件基类
 *
 * @export
 * @class DrtabControlBase
 * @extends {MainControlBase}
 */
export class DrtabControlBase extends MainControlBase {
    
    /**
     * 选中节点
     *
     * @param {string} tabPaneName 分页name
     * @memberof AppDrtabBase
     */
     public tabPanelClick(tabPaneName: string): void {
        this.onCtrlEvent(this.controlInstance.name, 'selectionchange', [{id: tabPaneName}])
    }
}
