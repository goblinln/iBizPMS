import { LogUtil, Util } from "../utils";
import { ViewEngine } from "./view-engine";

/**
 * 视图引擎基类
 *
 * @export
 * @class CommonViewEngine
 */
export class CommonViewEngine extends ViewEngine {

    /**
     * 部件引擎集合
     *
     * @memberof CommonViewEngine
     */
    public ctrlEngineArray: Array<any> = [];

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
        this.initViewControlMap(options.ctrl);
        this.initCtrlEngineArray(options.engine);
        super.init(options);
    }

    /**
     * 初始化引擎Map
     *
     * @param {*} options
     * @memberof CommonViewEngine
     */
    public initCtrlEngineArray(options: any) {
        if (options && options.length > 0) {
            this.ctrlEngineArray = [];
            options.forEach((element: any) => {
                const result = this.handleViewEngineParams(element);
                this.ctrlEngineArray.push(result);
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
        // 处理搜索部件加载并搜索（参数可指定触发部件）
        if (this.ctrlEngineArray.length > 0) {
            for (let element of this.ctrlEngineArray) {
                if (element.triggerCtrlName && Object.is(element.triggerCtrlName, 'VIEW')) {
                    if (element.triggerType && Object.is(element.triggerType, 'CtrlLoadAndSearch')) {
                        this.setViewState2({ tag: element.targetCtrlName, action: 'loaddraft', viewdata: Util.deepCopy(opts) });
                    }
                }
            }
        }
        // 处理部件加载（参数可指定触发部件）无指定触发部件时由容器触发
        if (this.ctrlEngineArray.length > 0) {
            for (let element of this.ctrlEngineArray) {
                if (element.triggerType && Object.is(element.triggerType, 'CtrlLoad') && !element.triggerCtrlName) {
                    this.setViewState2({ tag: element.targetCtrlName, action: 'load', viewdata: Util.deepCopy(opts) });
                }
            }
        }
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
        // 处理部件加载（参数可指定触发部件）
        if (Object.is(eventName, 'search') ||
            Object.is(eventName, 'load') ||
            Object.is(eventName, 'selectionchange')) {
            if (this.ctrlEngineArray.length > 0) {
                for (let element of this.ctrlEngineArray) {
                    if (element.triggerCtrlName && Object.is(element.triggerCtrlName, ctrlName)) {
                        if (element.triggerType && Object.is(element.triggerType, 'CtrlLoad')) {
                            if (this.view) {
                                if (this.view.$refs[element.targetCtrlName] && this.view.$refs[element.targetCtrlName].ctrl) {
                                    this.view.$refs[element.targetCtrlName].ctrl.setNavdatas(Util.deepCopy(args));
                                }
                                if (Util.isExistData(args)) {
                                    this.setViewState2({ tag: element.targetCtrlName, action: 'load', viewdata: Util.deepCopy(args) });
                                } else {
                                    this.setViewState2({ tag: element.targetCtrlName, action: 'reset', viewdata: Util.deepCopy(args) });
                                }
                            }
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
                return this.handleCtrlLoad(args.getPSUIEngineParams);
            case 'CtrlLoad':
                return this.handleCtrlLoad(args.getPSUIEngineParams);
            case 'CtrlLoadAndSearch':
                return this.CtrlLoadAndSearch(args.getPSUIEngineParams);
            default:
                LogUtil.warn(`${args.engineType}暂未支持`);
                break;
        }
    }

    /**
     * 处理搜索部件加载并搜索（参数可指定触发部件）
     *
     * @param {*} args 引擎参数
     * @memberof CommonViewEngine
     */
    public CtrlLoadAndSearch(args: any) {
        if (!args || args.length < 1) {
            return null;
        }
        const targetCtrl = args.find((item: any) => {
            return item.name === 'CTRL' && item.paramType === 'CTRL';
        })
        return { triggerCtrlName: 'VIEW', triggerType: 'CtrlLoadAndSearch', targetCtrlName: targetCtrl.ctrlName };
    }

    /**
     * 处理部件加载（参数可指定触发部件）
     *
     * @param {*} args 引擎参数
     * @memberof CommonViewEngine
     */
    public handleCtrlLoad(args: any) {
        if (!args || args.length < 1) {
            return null;
        }
        const triggerCtrl = args.find((item: any) => {
            return item.name === 'TRIGGER' && item.paramType === 'CTRL';
        })
        const targetCtrl = args.find((item: any) => {
            return item.name === 'CTRL' && item.paramType === 'CTRL';
        })
        return { triggerCtrlName: triggerCtrl?.ctrlName, triggerType: 'CtrlLoad', targetCtrlName: targetCtrl.ctrlName };
    }

}