import { ViewEngine } from './view-engine';

/**
 * 实体移动端树视图界面引擎
 *
 * @export
 * @class MobTreeViewEngine
 * @extends {ViewEngine}
 */
export class MobTreeViewEngine extends ViewEngine {

    /**
     * 树部件
     *
     * @protected
     * @type {*}
     * @memberof MobTreeViewEngine
     */
    protected tree: any;


    /**
     * 初始化编辑视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobTreeViewEngine
     */
    public init(options: any = {}): void {
        this.tree = options.tree;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobTreeViewEngine
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
     * @memberof MobTreeViewEngine
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
     * @memberof MobTreeViewEngine
     */
    public treeEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.onTreeLoad(args);
        }
        if (Object.is(eventName, 'click')) {
            this.onTreeClick(args);
        }
    }

    /**
     * 树加载完成
     *
     * @param {*} args
     * @memberof MobTreeViewEngine
     */
    public onTreeLoad(args: any): void {
        if (this.view) {
            this.view.datainfo = args.srfmajortext;
            // this.view.viewdataschange.emit({action:'load',data:args});
        }
    }

    /**
     * 树点击事件
     *
     * @param {*} args
     * @memberof MobTreeViewEngine
     */
    public onTreeClick(args: any): void {
        if (this.view) {
            this.view.viewdataschange.emit({ action: 'click', data: args });
        }
    }

    /**
     * 保存视图数据
     *
     * @param {*} [arg={}]
     * @memberof MobTreeViewEngine
     */
    public saveData(arg: any = {}): void {
        if (this.getTree()) {
            const tag = this.getTree().name;
            this.setViewState2({ tag: tag, action: 'save', viewdata: arg });
        }
    }

    /**
     * 获取树对象
     *
     * @returns {*}
     * @memberof MobTreeViewEngine
     */
    public getTree(): any {
        return this.tree;
    }

}