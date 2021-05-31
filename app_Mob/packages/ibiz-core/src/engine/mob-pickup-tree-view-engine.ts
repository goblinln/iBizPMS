import { ViewEngine } from './view-engine';

/**
 * 实体移动端选择树视图（部件视图）界面引擎
 *
 * @export
 * @class MobPickupTreeViewEngine
 * @extends {ViewEngine}
 */
export class MobPickupTreeViewEngine extends ViewEngine {

    /**
       * 树部件
       *
       * @protected
       * @type {*}
       * @memberof MobPickupTreeViewEngine
       */
    protected tree: any;


    /**
     * 初始化编辑视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobPickupTreeViewEngine
     */
    public init(options: any = {}): void {
        this.tree = options.tree;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobPickupTreeViewEngine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        if (this.getTree()) {
            const tag = this.getTree().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobPickupTreeViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'tree')) {
            this.treeEvent(eventName, args);
        }
    }

    /**
     * 树事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MobPickupTreeViewEngine
     */
    public treeEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.onTreeLoad(args);
        }
        if (Object.is(eventName, 'selectchange')) {
            this.onTreeSelectchange(args);
        }
    }

    /**
     * 树加载完成
     *
     * @param {*} args
     * @memberof MobPickupTreeViewEngine
     */
    public onTreeLoad(args: any): void {
        if (this.view) {
            this.view.datainfo = args.srfmajortext;
            // this.view.viewdataschange.emit({ action: 'load', data: args });
        }
    }

    /**
     * 树点击事件
     *
     * @param {*} args
     * @memberof MobPickupTreeViewEngine
     */
    public onTreeSelectchange(args: any): void {
        if (this.view) {
            this.emitViewEvent('viewdataschange', args);
            this.view.selectedArray = args;
        }
    }

    /**
     * 获取树对象
     *
     * @returns {*}
     * @memberof MobPickupTreeViewEngine
     */
    public getTree(): any {
        return this.tree;
    }

}