import { ViewEngine } from './view-engine';

/**
 * 实体移动端面板视图界面引擎
 *
 * @export
 * @class MobpanelViewEngine
 * @extends {ViewEngine}
 */
export class MobPanelViewEngine extends ViewEngine {

    /**
     * 面板部件
     *
     * @type {*}
     * @memberof MobPanelViewEngine
     */
    public panel: any = null;

    /**
     * Creates an instance of MobPanelViewEngine
     * 
     * @memberof MobPanelViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MobPanelViewEngine
     */
    public init(options: any): void {
        this.panel = options.panel;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MobPanelViewEngine
     */
    public load(): void {
        super.load();
        if (this.getPanel() && this.isLoadDefault) {
            const tag = this.getPanel().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
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
     * @memberof MobPanelViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'panel')) {
            this.PanelEvent(eventName, args);

        }
    }

    /**
     * 实体数据看板事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MobPanelViewEngine
     */
    public PanelEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.emitViewEvent('viewload', args);
        }
    }

    /**
     * 获取部件对象
     *
     * @returns {*}
     * @memberof MobPanelViewEngine
     */
    public getPanel(): any {
        return this.panel;
    }


}