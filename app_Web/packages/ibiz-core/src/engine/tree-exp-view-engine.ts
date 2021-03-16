import { ViewEngine } from './view-engine';

/**
 * 实体树导航视图界面引擎
 *
 * @export
 * @class TreeExpViewEngine
 * @extends {ViewEngine}
 */
export class TreeExpViewEngine extends ViewEngine {

    /**
     * 选择视图面板
     *
     * @type {*}
     * @memberof TreeExpViewEngine
     */
    public treeExpBar: any = null;

    /**
     * Creates an instance of TreeExpViewEngine.
     * 
     * @memberof TreeExpViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof TreeExpViewEngine
     */
    public init(options: any): void {
        this.treeExpBar = options.treeexpbar;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof TreeExpViewEngine
     */
    public load(): void {
        super.load();

        if (this.getTreeExpBar() && this.isLoadDefault) {
            const tag = this.getTreeExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: this.view.context });
        } else {
            this.isLoadDefault = true;
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof TreeExpViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'treeexpbar')) {
            this.treeExpBarEvent(eventName, args);

        }
    }

    /**
     * 树导航事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof TreeExpViewEngine
     */
    public treeExpBarEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.emitViewEvent('viewload', args);
        }
        if (Object.is(eventName, 'selectionchange')) {
            this.emitViewEvent('viewdataschange', args);
        }
        if (Object.is(eventName, 'activated')) {
            this.emitViewEvent('viewdatasactivated', args);
        }
    }

    /**
     * 获取部件对象那
     *
     * @returns {*}
     * @memberof TreeExpViewEngine
     */
    public getTreeExpBar(): any {
        return this.treeExpBar;
    }


}