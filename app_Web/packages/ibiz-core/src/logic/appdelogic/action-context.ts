import { IPSAppDELogic, IPSDELogicParam } from "@ibiz/dynamic-model-api";
import { IContext, IParams } from "../../interface";

/**
 * 实体处理逻辑上下文参数对象
 *
 * @export
 * @class ActionContext
 */
export class ActionContext {

    /**
     * 实体行为服务context
     *
     * @type {IContext}
     * @memberof ActionContext
     */
    public context: IContext;

    /**
     * 异常信息
     *
     * @type {*}
     * @memberof ActionContext
     */    
    public throwExceptionInfo:any;

    /**
     * 逻辑处理参数集合
     *
     * @type {Map<string, any>}
     * @memberof ActionContext
     */
    public paramsMap: Map<string, any> = new Map();

    /**
     * 默认逻辑处理参数名称
     *
     * @type {string}
     * @memberof ActionContext
     */
    public defaultParamName: string = '';

    /**
     * 默认逻辑处理参数
     *
     * @readonly
     * @memberof ActionContext
     */
    get defaultParam() {
        if(this.throwExceptionInfo){
            return this.throwExceptionInfo;
        }else{
            return this.paramsMap.get(this.defaultParamName);
        }
    }

    /**
     * 获取逻辑处理参数
     *
     * @param {string} key 逻辑处理参数的codeName
     * @memberof ActionContext
     */
    public getParam(key: string) {
        return this.paramsMap.get(key);
    }

    /**
     * 构造函数
     * 
     * @param {IPSAppDELogic} logic 处理逻辑模型对象
     * @param {IContext} context 实体行为服务context
     * @param {IParams} params 实体行为服务data
     * @memberof ActionContext
     */
    constructor(logic: IPSAppDELogic, context: IContext, params: IParams) {
        this.context = context;
        // 初始化逻辑处理参数
        if (logic.getPSDELogicParams() && (logic.getPSDELogicParams() as IPSDELogicParam[]).length > 0) {
            for (let logicParam of (logic.getPSDELogicParams() as IPSDELogicParam[])) {
                this.paramsMap.set(logicParam.codeName, logicParam.default ? params : {})
                if (logicParam.default) this.defaultParamName = logicParam.codeName;
            }
        }
    }
}
