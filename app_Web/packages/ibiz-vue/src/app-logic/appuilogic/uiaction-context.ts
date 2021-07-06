import { IPSAppUILogic } from "@ibiz/dynamic-model-api";
import { IContext, IParams } from "ibiz-core";

/**
 * 界面处理逻辑上下文参数对象
 *
 * @export
 * @class UIActionContext
 */
export class UIActionContext {

    /**
     * 应用上下文
     *
     * @type {IContext}
     * @memberof UIActionContext
     */
    public context: IContext;

    /**
     * 数据对象
     *
     * @type {*}
     * @memberof UIActionContext
     */
    public data: any;

    /**
     * 其他附加参数
     *
     * @type {*}
     * @memberof UIActionContext
     */
    public otherParams: any;

    /**
     * 逻辑处理参数集合
     *
     * @type {Map<string, any>}
     * @memberof UIActionContext
     */
    public paramsMap: Map<string, any> = new Map();

    /**
     * 默认逻辑处理参数名称
     *
     * @type {string}
     * @memberof UIActionContext
     */
    public defaultParamName: string = '';

    /**
     * 默认逻辑处理参数
     *
     * @readonly
     * @memberof UIActionContext
     */
    get defaultParam() {
        return this.paramsMap.get(this.defaultParamName);
    }

    /**
     * 获取逻辑处理参数
     *
     * @param {string} key 逻辑处理参数的codeName
     * @memberof UIActionContext
     */
    public getParam(key: string) {
        return this.paramsMap.get(key);
    }

    /**
     * 构造函数
     * 
     * @param {IPSAppUILogic} logic 处理逻辑模型对象
     * @param {any[]} args 数据对象
     * @param {*} context 应用上下文
     * @param {*} params 视图参数
     * @param {*} $event 事件源对象
     * @param {*} xData 部件对象
     * @param {*} actioncontext 界面容器对象
     * @param {*} srfParentDeName 关联父应用实体代码名称
     * @memberof UIActionContext
     */
    constructor(logic: IPSAppUILogic, args: any[], context: any = {}, params: any = {},
        $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
        this.context = context;
        this.data = args && Array.isArray(args) ? args[0] : {};
        this.otherParams = {
            viewparams: params,
            event: $event,
            control: xData,
            container: actioncontext,
            parentDeName: srfParentDeName
        };
        // 初始化逻辑处理参数
        // if (logic.getPSDELogicParams() && (logic.getPSDELogicParams() as IPSDELogicParam[]).length > 0) {
        //     for (let logicParam of (logic.getPSDELogicParams() as IPSDELogicParam[])) {
        //         this.paramsMap.set(logicParam.codeName, logicParam.default ? params : {})
        //         if (logicParam.default) this.defaultParamName = logicParam.codeName;
        //     }
        // }
    }
}
