import { ViewEngine } from './view-engine';

/**
 * 实体移动端多数据选择视图界面引擎
 *
 * @export
 * @class MobMPickupViewEngine
 * @extends {ViewEngine}
 */
export class MobMPickupViewEngine extends ViewEngine {

    /**
     * 选择视图面板
     *
     * @type {*}
     * @memberof MobMPickupViewEngine
     */
    public pickupViewPanel: any = null;

    /**
     * Creates an instance of MobMPickupViewEngine.
     * 
     * @memberof MobMPickupViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MobMPickupViewEngine
     */
    public init(options: any): void {
        this.pickupViewPanel = options.pickupviewpanel;
        if (options.view.viewdata && options.view.viewdata.selectedData && Array.isArray(options.view.viewdata.selectedData)) {
            options.view.viewSelections = [...options.view.viewdata.selectedData];
            delete options.view.viewdata.selectedData;
        }
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MobMPickupViewEngine
     */
    public load(): void {
        super.load();
        if (this.getPickupViewPanel()) {
            const tag = this.getPickupViewPanel().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
        }
    }

    /**
     * 引擎事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobMPickupViewEngine
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
     * @param {string} ctrlName 选择视图面板名称
     * @param {any[]} args 选中数据
     * @memberof MobMPickupViewEngine
     */
    public onSelectionChange(ctrlName: string, args: any[]): void {
        this.view.viewSelections = [];
        this.view.viewSelections = [...args];
        Object.values(this.view.containerModel).forEach((model: any) => {
            if (!Object.is(model.type, 'PICKUPVIEWPANEL')) {
                return;
            }
        });
    }

    /**
     * 视图加载完成
     *
     * @param {string} ctrlName 选择视图面板名称
     * @param {any[]} args 选中数据
     * @memberof MobMPickupViewEngine
     */
    public onLoad(ctrlName: string, args: any[]): void {
        this.view.containerModel[`view_${ctrlName}`].datas = [...JSON.parse(JSON.stringify(args))];
    }

    /**
     * 获取选择视图面板
     *
     * @returns {*}
     * @memberof MobMPickupViewEngine
     */
    public getPickupViewPanel(): any {
        return this.pickupViewPanel;
    }
}