import { IContext, IParams, Util } from "ibiz-core";
import { IPSDEUILogicParam } from "@ibiz/dynamic-model-api";

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
    private appContext: IContext;

    /**
     * 视图参数
     *
     * @type {IParams}
     * @memberof UIActionContext
     */
    private viewParam: IParams;

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
     * 逻辑局部应用上下文参数名称
     *
     * @type {string}
     * @memberof UIActionContext
     */
    public navContextParamName: string = '';

    /**
     * 逻辑局部视图参数名称
     *
     * @type {string}
     * @memberof UIActionContext
     */
     public navViewParamParamName: string = '';

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
     * 逻辑上下文参数
     *
     * @readonly
     * @memberof UIActionContext
     */
    get navContextParam(){
        return this.paramsMap.get(this.navContextParamName);
    }

    /**
     * 逻辑视图参数
     *
     * @readonly
     * @memberof UIActionContext
     */
     get navViewParamParam(){
        return this.paramsMap.get(this.navViewParamParamName);
    }

    /**
     * 上下文数据（包括应用上下文和逻辑局部上下文参数）
     *
     * @readonly
     * @memberof UIActionContext
     */
    get context(){
        if(this.navContextParam){
            const tempContext = Util.deepCopy(this.appContext);
            Object.assign(tempContext,this.navContextParam);
            return tempContext;
        }else{
            return this.appContext;
        }
    }

    /**
     * 视图参数数据（包括外部传入视图参数和逻辑局部视图参数）
     *
     * @readonly
     * @memberof UIActionContext
     */
    get viewparams(){
        if(this.navViewParamParam){
            const tempViewParam = Util.deepCopy(this.viewParam);
            Object.assign(tempViewParam,this.navViewParamParam);
            return tempViewParam;
        }else{
            return this.viewParam;
        }
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
     * @param {*} logic 处理逻辑模型对象
     * @param {any[]} args 数据对象
     * @param {*} context 应用上下文
     * @param {*} params 视图参数
     * @param {*} $event 事件源对象
     * @param {*} xData 部件对象
     * @param {*} actioncontext 界面容器对象
     * @param {*} srfParentDeName 关联父应用实体代码名称
     * @memberof UIActionContext
     */
    constructor(logic: any, args: any[], context: any = {}, params: any = {},
        $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
        this.appContext = context;
        this.viewParam = params;
        this.data = args?.length > 0 ? args[0] : {};
        this.otherParams = {
            event: $event,
            control: xData,
            container: actioncontext,
            parentDeName: srfParentDeName
        };
        // 初始化界面逻辑处理参数
        if (logic.getPSDEUILogicParams() && (logic.getPSDEUILogicParams() as IPSDEUILogicParam[]).length > 0) {
            for (let logicParam of (logic.getPSDEUILogicParams() as IPSDEUILogicParam[])) {
                this.paramsMap.set(logicParam.codeName, logicParam.default ? this.data : {})
                if (logicParam.default) this.defaultParamName = logicParam.codeName;
                if(logicParam.navContextParam) this.navContextParamName = logicParam.codeName;
                if(logicParam.navViewParamParam) this.navViewParamParamName = logicParam.codeName;
            }
        }
    }
}
