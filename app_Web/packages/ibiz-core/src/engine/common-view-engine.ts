import { Util } from "../utils";
import { ViewEngine } from "./view-engine";

/**
 * 视图引擎基类
 *
 * @export
 * @class CommonViewEngine
 */
export class CommonViewEngine extends ViewEngine {

    /**
     * 视图引擎Map
     *
     * @memberof CommonViewEngine
     */
    public viewEngineMap: Map<string, any> = new Map();

    /**
     * 视图部件Map
     *
     * @memberof CommonViewEngine
     */
    public viewCtrlMap: Map<string, any> = new Map();

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof CommonViewEngine
     */
    public init(options: any = {}): void {
        super.init(options);
        this.initViewControlMap(options.ctrl);
        this.initViewEngineMap(options.engine);
    }

    /**
     * 初始化引擎Map
     *
     * @param {*} options
     * @memberof CommonViewEngine
     */
    public initViewEngineMap(options: any) {
        if (options && options.length > 0) {
            options.forEach((element: any) => {
                const result = this.handleViewEngineParams(element);
                if (result && result.targetCtrlName) {
                    this.viewEngineMap.set(result.targetCtrlName, result);
                }
            });
        }
    }

    /**
     * 初始化视图部件Map
     *
     * @param {*} options
     * @memberof CommonViewEngine
     */
    public initViewControlMap(options: any) {
        if (options && options.length > 0) {
            options.forEach((element: any) => {
                this.viewCtrlMap.set(element.name, element.ctrl);
            });
        }
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof CommonViewEngine
     */
    public load(opts: any = {}): void {

    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof CommonViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        // 执行处理关联部件加载触发器类型触发逻辑
        if (Object.is(eventName, 'search') || Object.is(eventName, 'load')) {
            if (this.viewEngineMap.size > 0) {
                for (let element of this.viewEngineMap.values()) {
                    if (element.triggerCtrlName && Object.is(element.triggerCtrlName, ctrlName)) {
                        if (element.triggerType && Object.is(element.triggerType, 'CtrlLoadTrigger')) {
                            this.setViewState2({ tag: element.targetCtrlName, action: 'load', viewdata: Util.deepCopy(args) });
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理视图引擎参数
     *
     * @param {*} args 引擎数据
     * @memberof CommonViewEngine
     */
    public handleViewEngineParams(args: any) {
        switch (args.engineType) {
            case 'CtrlLoadTrigger':
                return this.handleCtrlLoadTrigger(args.getPSUIEngineParams);
            default:
                break;
        }
    }

    /**
     * 处理关联部件使用类型为加载触发器引擎参数
     *
     * @param {*} args 引擎参数
     * @memberof CommonViewEngine
     */
    public handleCtrlLoadTrigger(args: any) {
        if (!args || args.length < 1) {
            return null;
        }
        const triggerCtrl = args.find((item: any) => {
            return item.name === 'TRIGGER' && item.paramType === 'CTRL';
        })
        const targetCtrl = args.find((item: any) => {
            return item.name === 'CTRL' && item.paramType === 'CTRL';
        })
        return { triggerCtrlName: triggerCtrl.ctrlName, triggerType: 'CtrlLoadTrigger', targetCtrlName: targetCtrl.ctrlName };
    }

}