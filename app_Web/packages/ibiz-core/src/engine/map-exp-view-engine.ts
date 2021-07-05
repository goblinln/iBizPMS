import { ViewEngine } from './view-engine';

/**
 * 地图导航视图界面引擎
 *
 * @export
 * @class MapExpViewEngine
 * @extends {ViewEngine}
 */
export class MapExpViewEngine extends ViewEngine {

    /**
     * 表格导航栏部件
     *
     * @type {*}
     * @memberof MapExpViewEngine
     */
    public mapExpBar: any = null;

    /**
     * Creates an instance of MapExpViewEngine.
     * 
     * @memberof MapExpViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MapExpViewEngine
     */
    public init(options: any): void {
        this.mapExpBar = options.mapexpbar;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MapExpViewEngine
     */
    public load(): void {
        super.load();
        if (this.getMapExpBar() && this.isLoadDefault) {
            const tag = this.getMapExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: this.view.viewparams });
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
     * @memberof MapExpViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'mapexpbar')) {
            this.mapExpBarEvent(eventName, args);
        }
    }

    /**
     * 地图导航事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MapExpViewEngine
     */
    public mapExpBarEvent(eventName: string, args: any): void {
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
     * 获取部件对象
     *
     * @returns {*}
     * @memberof MapExpViewEngine
     */
    public getMapExpBar(): any {
        return this.mapExpBar;
    }


}