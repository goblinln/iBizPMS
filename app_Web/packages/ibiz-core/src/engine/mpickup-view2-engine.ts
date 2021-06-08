import { ViewEngine } from './view-engine';

/**
 * 实体选择视图
 *
 * @export
 * @class MPickupView2Engine
 * @extends {ViewEngine}
 */
export class MPickupView2Engine extends ViewEngine {

    /**
     * 选择视图面板
     *
     * @type {*}
     * @memberof MPickupView2Engine
     */
    public pickupViewPanel: any = null;

    /**
     * 树导航
     *
     * @type {*}
     * @memberof MPickupView2Engine
     */
    public treeExpBar: any = null;

    /**
     * Creates an instance of MPickupView2Engine.
     * 
     * @memberof MPickupView2Engine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MPickupView2Engine
     */
    public init(options: any): void {
        this.treeExpBar = options.treeExpBar;
        if (options.view.viewdata) {
            const isStr: boolean = typeof options.view.viewdata == 'string';
            let viewdata: any = isStr ? JSON.parse(options.view.viewdata) : options.view.viewdata;
            if (viewdata['selectedData']) {
                options.view.viewSelections = [...viewdata['selectedData']];
                delete viewdata['selectedData'];
            }
            options.view.viewdata = isStr ? JSON.stringify(viewdata) : viewdata;
        }
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MPickupView2Engine
     */
    public load(): void {
        super.load();
        if (this.getTreeExpBar()) {
            const tag = this.getTreeExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: this.view.viewparams });
        }
    }

    /**
     * 引擎事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MPickupView2Engine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);

        if (Object.is(eventName, 'selectionchange')) {
            this.onSelectionChange(ctrlName, args);
        }
        if (Object.is(eventName, 'load')) {
            this.onLoad(ctrlName, args);
        }
        if (Object.is(eventName, 'activated')) {
            this.onSelectionChange(ctrlName, args);
            this.view.onCLickRight();
        }
    }

    /**
     * 值选中变化
     *
     * @param {string} ctrlName 选择视图面板名称
     * @param {any[]} args 选中数据
     * @memberof MPickupView2Engine
     */
    public onSelectionChange(ctrlName: string, args: any[]): void {
        this.view.containerModel[`view_${ctrlName}`].selections = [...JSON.parse(JSON.stringify(args))];

        Object.values(this.view.containerModel).forEach((model: any) => {
            if (!Object.is(model.type, 'PICKUPVIEWPANEL')) {
                return;
            }
        });
        const _disbaled: boolean = this.view.containerModel[`view_${ctrlName}`].selections.length > 0 ? true : false;
        this.view.containerModel.view_rightbtn.disabled = !_disbaled;
        if(!this.view.isShowButton){
            this.emitViewEvent('viewdataschange', [...args]);
        }
    }

    /**
     * 视图加载完成
     *
     * @param {string} ctrlName 选择视图面板名称
     * @param {any[]} args 选中数据
     * @memberof MPickupView2Engine
     */
    public onLoad(ctrlName: string, args: any[]): void {
        if (ctrlName == this.getPickupViewPanel().name) {
            this.view.containerModel[`view_${ctrlName}`].datas = [...JSON.parse(JSON.stringify(args))];
        }
    }

    /**
     * 获取选择视图面板
     *
     * @returns {*}
     * @memberof MPickupView2Engine
     */
    public getPickupViewPanel(): any {
        return this.pickupViewPanel;
    }

    /**
     * 获取树导航
     *
     * @returns {*}
     * @memberof MPickupView2Engine
     */
    public getTreeExpBar(): any {
        return this.treeExpBar;
    }
}