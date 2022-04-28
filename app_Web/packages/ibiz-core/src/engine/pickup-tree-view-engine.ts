import { Util } from '../utils';
import { TreeViewEngine } from './tree-view-engine';

/**
 * 实体选择树视图（部件视图）界面引擎
 *
 * @export
 * @class PickupTreeViewEngine
 * @extends {TreeViewEngine}
 */
export  class PickupTreeViewEngine extends TreeViewEngine {

    /**
     * 部件加载完
     *
     * @param {*} args
     * @memberof PickupTreeViewEngine
     */
    public onLoad(args: any): void { 
        super.onLoad(args);
        if (this.view) {
            this.emitViewEvent('viewload', args);
        }
    }

    /**
     * 选中处理
     *
     * @param {any[]} args
     * @memberof PickupTreeViewEngine
     */
    public onSelectionChange(args: any[]): void {
        super.onSelectionChange(args);
        if (this.view) {
            this.view.viewSelections =  [...args];
            this.emitViewEvent('viewdataschange', args);
        }
    }

    /**
     * 双击选中激活数据
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof PickupTreeViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
      if (Object.is(ctrlName, 'tree') && Object.is(eventName, 'nodedblclick')) {
          this.emitViewEvent('viewdatasactivated', args);
          return ;
      }
      super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 父数据参数模式
     *
     * @param {{ tag: string, action: string, viewdata: any }} { tag, action, viewdata }
     * @memberof PickupTreeViewEngine
     */
     public setViewState2({ tag, action, viewdata }: { tag: string, action: string, viewdata: any }): void {
        if (Util.isExistAndNotEmpty(this.view.context.query)) {
            this.view.viewState.next({ tag: tag, action: 'filter', data: { srfnodefilter: this.view.context.query } });
        } else {
            this.view.viewState.next({ tag: tag, action: action, data: viewdata });
        }
    }
}