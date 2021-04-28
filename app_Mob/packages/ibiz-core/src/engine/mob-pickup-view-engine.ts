import { ViewEngine } from './view-engine';

/**
 * 实体移动端数据选择视图界面引擎
 *
 * @export
 * @class MobPickupViewEngine
 * @extends {ViewEngine}
 */
export class MobPickupViewEngine extends ViewEngine {

    /**
     * 选择视图面板
     *
     * @type {*}
     * @memberof MobPickupViewEngine
     */
    public pickupViewPanel: any = null;

    /**
     * Creates an instance of MobPickupViewEngine.
     * 
     * @memberof MobPickupViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MobPickupViewEngine
     */
    public init(options: any): void {
        this.pickupViewPanel = options.pickupviewpanel;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MobPickupViewEngine
     */
    public load(): void {
        super.load();
        if (this.view) {
            this.view.viewSelections = [];
        }
        if (this.getPickupViewPanel()) {
            const tag = this.getPickupViewPanel().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
        }
    }

    /**
     * 
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobPickupViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);

        if (Object.is(eventName, 'selectionchange')) {
            this.onSelectionChange(ctrlName, args);
        }
        if (Object.is(eventName, 'load')) {
            this.onLoad(ctrlName, args);
        }
    }

    /**
     * 值选中变化
     *
     * @param {any[]} args
     * @memberof MobPickupViewEngine
     */
    public onSelectionChange(ctrlName: any, args: any[]): void {
        this.view.viewSelections = [];
        this.view.viewSelections = [...args];
        const _disabled: boolean = this.view.viewSelections.length > 0 ? false : true;
        // this.view.containerModel.view_okbtn.disabled = _disabled;
    }

    /**
     * 视图加载完成
     *
     * @param {string} ctrlName 选择视图面板名称
     * @param {any[]} args 选中数据
     * @memberof MobPickupViewEngine
     */
    public onLoad(ctrlName: string, args: any[]): void {
        this.view.containerModel[`view_${ctrlName}`].datas = [...JSON.parse(JSON.stringify(args))];
    }

    /**
     * 获取选择视图面板
     *
     * @returns {*}
     * @memberof MobPickupViewEngine
     */
    public getPickupViewPanel(): any {
        return this.pickupViewPanel;
    }
}