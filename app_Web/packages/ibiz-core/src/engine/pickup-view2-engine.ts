import { ViewEngine } from './view-engine';

/**
 * 实体选择视图(左右关系)
 *
 * @export
 * @class PickupView2Engine
 * @extends {ViewEngine}
 */
export class PickupView2Engine extends ViewEngine {

    /**
     * 树导航
     *
     * @type {*}
     * @memberof PickupView2Engine
     */
    public treeExpBar: any = null;

    /**
     * Creates an instance of PickupView2Engine.
     * 
     * @memberof PickupView2Engine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof PickupView2Engine
     */
    public init(options: any): void {
        this.treeExpBar = options.treeExpBar;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof PickupView2Engine
     */
    public load(): void {
        this.view.viewSelections = [];
        super.load();
        if (this.getTreeExpBar()) {
            const tag = this.getTreeExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: this.view.viewparams });
        }
    }

    /**
     * 
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof PickupView2Engine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);

        if (Object.is(eventName, 'selectionchange')) {
            this.onSelectionChange(args);
        }
        if (Object.is(eventName, 'activated')) {
            this.emitViewEvent('viewdatasactivated', args);
        }
    }

    /**
     * 值选中变化
     *
     * @param {any[]} args
     * @memberof PickupView2Engine
     */
    public onSelectionChange(args: any[]): void {
        this.view.viewSelections = [];
        this.view.viewSelections = [...args]
        const _disabled: boolean = this.view.viewSelections.length > 0 ? false : true;
        this.view.containerModel.view_okbtn.disabled = _disabled;
        if(!this.view.isShowButton){
            this.emitViewEvent('viewdataschange', [...args]);
        }
    }

    /**
     * 获取树导航
     *
     * @returns {*}
     * @memberof PickupView2Engine
     */
    public getTreeExpBar(): any {
        return this.treeExpBar;
    }
}