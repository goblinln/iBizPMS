import { IPSAppDataEntity, IPSDEMainState, IPSDEMainStateOPPriv, IPSDEOPPriv } from "@ibiz/dynamic-model-api";
import { setSessionStorage } from "../..";
import { AppServiceBase } from "../app-service/app-base.service";
import { AuthServiceBase } from "../auth-service/auth-base.service";
import { GetModelService } from "../model-service/model-service";

/**
 * 界面服务基类
 *
 * @export
 * @class UIServiceBase
 */
export class UIServiceBase {

    /**
     * 应用存储对象
     *
     * @protected
     * @type {any}
     * @memberof UIServiceBase
     */
    protected $store: any;

    /**
     * 流程状态数组
     *
     * @protected
     * @type {Array<string>}
     * @memberof UIServiceBase
     */
     protected InWorkflowArray: Array<string> = ["todo","toread","done"];

    /**
     * 应用上下文
     * 
     * @protected
     * @type {any}
     * @memberof UIServiceBase
     */
    protected context: any;

    /**
     * 所依赖权限服务
     *
     * @memberof UIServiceBase
     */
    protected authService: any;

    /**
     * 所依赖数据服务
     * 
     * @memberof  UIServiceBase
     */
    protected dataService: any;

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof UIServiceBase
     */
    protected dynaModelFilePath: string = "";

    /**
     * 应用实体模型对象
     *
     * @protected
     * @type {IBizEntityModel}
     * @memberof UIServiceBase
     */
    protected entityModel !: IPSAppDataEntity;

    /**
     * 是否支持实体主状态
     * 
     * @memberof  UIServiceBase
     */
    protected isEnableDEMainState: boolean = false;

    /**
     * 界面行为Map
     * 
     * @memberof  UIServiceBase
     */
    protected actionMap: Map<string, any> = new Map();

    /**
     * 所有关联视图功能数据
     * 
     * @memberof  UIServiceBase
     */
    protected allViewFuncMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  UIServiceBase
     */
    protected stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  UIServiceBase
     */
    protected stateField: string = "";

    /**
     * 多表单属性
     * 
     * @memberof  UIServiceBase
     */
    protected multiFormDEField: string | null = null;

    /**
     * 索引类型属性
     * 
     * @memberof  UIServiceBase
     */
    protected indexTypeDEField: string | null = null;

    /**
     * 临时组织标识属性
     * 
     * @memberof  UIServiceBase
     */
    protected tempOrgIdDEField: string | null = null;

    /**
     * 动态实例标记
     * 
     * @memberof  UIServiceBase
     */
    protected dynaInstTag: string | null = null;

    /**
     * 主状态属性集合
     * 
     * @memberof  UIServiceBase
     */
    protected mainStateFields: Array<any> = [];

    /**
     * 主状态集合Map
     * 
     * @memberof  UIServiceBase
     */
    protected allDeMainStateMap: Map<string, string> = new Map();

    /**
     * 主状态操作标识Map
     * 
     * @memberof  UIServiceBase
     */
    protected allDeMainStateOPPrivsMap: Map<string, any> = new Map();

    /**
     * Creates an instance of UIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof UIServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();
        this.context = opts.context ? opts.context : {};
    }

    /**
     * 获取应用存储对象
     *
     * @returns {(any | null)}
     * @memberof UIServiceBase
     */
    public getStore() {
        return this.$store;
    }

    /**
     * 执行界面行为统一入口
     *
     * @param {string} uIActionTag 界面行为tag
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * 
     * @memberof UIServiceBase
     */
    protected excuteAction(uIActionTag: string, args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (this.actionMap.get(uIActionTag)) {
            this.actionMap.get(uIActionTag).execute(args, context, params, $event, xData, actionContext, srfParentDeName, this);
        } else {
            console.warn(`当前实例${uIActionTag}界面行为未实现`);
        }
    }

    /**
     * 加载应用实体模型数据
     * 
     * @memberof  UIServiceBase
     */
    protected async loaded() {
        this.entityModel = await (await GetModelService(this.context)).getPSAppDataEntity(this.dynaModelFilePath);
        this.initRuntimeData();
        await this.initActionMap();
    }

    /**
     * 初始化运行时数据
     * 
     * @memberof  UIServiceBase
     */
    protected initRuntimeData() {
        this.initBasicData();
        this.initViewFuncMap();
        this.initDeMainStateMap();
        this.initDeMainStateOPPrivsMap();
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  UIServiceBase
     */
    protected initBasicData() { }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  UIServiceBase
     */
    protected async initActionMap() { }

    /**
      * 初始化视图功能数据Map
      * 
      * @memberof  UIServiceBase
      */
    protected initViewFuncMap() { }

    /**
     * 初始化主状态集合
     * 
     * @memberof  UIServiceBase
     */
    protected initDeMainStateMap() {
        if (this.entityModel && this.entityModel.getAllPSDEMainStates() && (this.entityModel.getAllPSDEMainStates() as IPSDEMainState[]).length > 0) {
            (this.entityModel.getAllPSDEMainStates() as IPSDEMainState[]).forEach((element: IPSDEMainState) => {
                this.allDeMainStateMap.set(element.mSTag, element.mSTag);
            });
        }
    }

    /**
     * 初始化主状态操作标识
     * 
     * @memberof  UIServiceBase
     */
    protected initDeMainStateOPPrivsMap() {
        if (this.entityModel && this.entityModel.getAllPSDEMainStates() && (this.entityModel.getAllPSDEMainStates() as IPSDEMainState[]).length > 0) {
            (this.entityModel.getAllPSDEMainStates() as IPSDEMainState[]).forEach((element: IPSDEMainState) => {
                let tempMainStateOPPrivs: any = {};
                if (this.entityModel && this.entityModel.getAllPSDEOPPrivs() && (this.entityModel.getAllPSDEOPPrivs() as IPSDEOPPriv[]).length > 0) {
                    (this.entityModel.getAllPSDEOPPrivs() as IPSDEOPPriv[]).forEach((deOPPriv: IPSDEOPPriv) => {
                        if (element.oPPrivAllowMode) {
                            tempMainStateOPPrivs[deOPPriv.name] = 0;
                        } else {
                            tempMainStateOPPrivs[deOPPriv.name] = 1;
                        }
                    });
                }
                if (element.getPSDEMainStateOPPrivs() && (element.getPSDEMainStateOPPrivs() as IPSDEMainStateOPPriv[]).length > 0) {
                    (element.getPSDEMainStateOPPrivs() as IPSDEMainStateOPPriv[]).forEach((mainStateOPPriv: IPSDEMainStateOPPriv) => {
                        if (element.oPPrivAllowMode) {
                            tempMainStateOPPrivs[mainStateOPPriv.name] = 1;
                        } else {
                            tempMainStateOPPrivs[mainStateOPPriv.name] = 0;
                        }
                    });
                }
                this.allDeMainStateOPPrivsMap.set(element.mSTag, tempMainStateOPPrivs);
            });
        }
    }

    /**
    * 获取资源标识是否有权限(无数据目标)
    * 
    * @param tag 资源标识
    * @memberof  UIServiceBase
    */
    protected getResourceOPPrivs(tag: any) {
        if (!this.authService) {
            this.authService = new AuthServiceBase(this.getStore());
        }
        return this.authService.getResourcePermission(this.authService.sysOPPrivsMap.get(tag)) ? 1 : 0;
    }

    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param enableWorkflowParam  重定向视图需要处理流程中的数据
     * @memberof  UIServiceBase
     */
    protected async getRDAppView(srfkey: string, enableWorkflowParam: any) {
        // 进行数据查询
        let result: any = await this.dataService.Get({ [this.entityModel.codeName.toLowerCase()]: srfkey });
        const curData: any = result.data;
        // 设置临时组织标识（用于获取多实例）
        if(this.tempOrgIdDEField && curData && curData[this.tempOrgIdDEField]){
            setSessionStorage("tempOrgId",curData[this.tempOrgIdDEField]);
        }
        //判断当前数据模式,默认为true，todo
        const iRealDEModel: boolean = true;

        let bDataInWF: boolean = false;
        let bWFMode: any = false;
        // 计算数据模式
        if (enableWorkflowParam && enableWorkflowParam.srfwf && (this.InWorkflowArray.indexOf(enableWorkflowParam.srfwf) !== -1)) {
            bDataInWF = true;
        }
        let strPDTViewParam: string = await this.getDESDDEViewPDTParam(curData, bDataInWF, bWFMode);
        //若不是当前数据模式，处理strPDTViewParam，todo

        if (bDataInWF) {
            return strPDTViewParam;
        }
        if (this.multiFormDEField || this.indexTypeDEField) {
            return strPDTViewParam;
        } else {
            //返回视图功能数据
            return `${this.allViewFuncMap.get(strPDTViewParam) ? this.allViewFuncMap.get(strPDTViewParam) : ""}`;
        }
    }

    /**
     * 获取实际的数据类型
     * 
     * @memberof  UIServiceBase
     */
    protected getRealDEType(entity: any) { }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  UIServiceBase
     */
    protected async getDESDDEViewPDTParam(curData: any, bDataInWF: boolean, bWFMode: boolean) {
        let strPDTParam: string = '';
        const Environment = AppServiceBase.getInstance().getAppEnvironment();
        if (bDataInWF) {
            // 存在多表单属性
            if (this.multiFormDEField) {
                strPDTParam = "";
                const formFieldValue: string = curData[this.multiFormDEField] ? curData[this.multiFormDEField] : "";
                if (formFieldValue) {
                    if (!Environment.isAppMode) {
                        strPDTParam += 'MOBWFEDITVIEW:' + formFieldValue;
                    }
                    strPDTParam += 'WFEDITVIEW:' + formFieldValue;
                }
            }
            // 存在索引类型属性
            if (this.indexTypeDEField) {
                strPDTParam = "";
                const indexTypeValue: string = curData[this.indexTypeDEField] ? curData[this.indexTypeDEField] : "";
                if (indexTypeValue) {
                    if (!Environment.isAppMode) {
                        strPDTParam += 'MOBWFEDITVIEW:' + indexTypeValue;
                    }
                    strPDTParam += 'WFEDITVIEW:' + indexTypeValue;
                }
            }
            if (strPDTParam && this.dynaInstTag) {
                strPDTParam += `:${this.dynaInstTag}`;
            }
            return strPDTParam ? strPDTParam : 'WFEDITVIEW';
        }
        // 存在多表单属性
        if (this.multiFormDEField) {
            const formFieldValue: string = curData[this.multiFormDEField] ? curData[this.multiFormDEField] : "";
            if (formFieldValue) {
                if (!Environment.isAppMode) {
                    return 'MOBEDITVIEW:' + formFieldValue;
                }
                return 'EDITVIEW:' + formFieldValue;
            }
        }
        // 存在索引类型属性
        if (this.indexTypeDEField) {
            const indexTypeValue: string = curData[this.indexTypeDEField] ? curData[this.indexTypeDEField] : "";
            if (indexTypeValue) {
                if (!Environment.isAppMode) {
                    return 'MOBEDITVIEW:' + indexTypeValue;
                }
                return 'EDITVIEW:' + indexTypeValue;
            }
        }
        if (!Environment.isAppMode) {
            if (this.getDEMainStateTag(curData)) {
                return `MOBEDITVIEW:MSTAG:${this.getDEMainStateTag(curData)}`;
            }
            return 'MOBEDITVIEW:';
        }
        if (this.getDEMainStateTag(curData)) {
            return `EDITVIEW:MSTAG:${this.getDEMainStateTag(curData)}`;
        }
        return 'EDITVIEW:';
    }

    /**
     * 获取数据对象的主状态标识
     * 
     * @param curData 当前数据
     * @memberof  UIServiceBase
     */
    protected getDEMainStateTag(curData: any) {
        if (this.mainStateFields.length === 0) return null;

        this.mainStateFields.forEach((singleMainField: any) => {
            if (!(singleMainField in curData)) {
                console.warn(`当前数据对象不包含属性「${singleMainField}」，根据「${singleMainField}」属性进行的主状态计算默认为空值`);
            }
        })
        for (let i = 0; i <= 1; i++) {
            let strTag: string = (curData[this.mainStateFields[0]] != null && curData[this.mainStateFields[0]] !== "") ? (i == 0) ? `${curData[this.mainStateFields[0]]}` : "" : "";
            if (this.mainStateFields.length >= 2) {
                for (let j = 0; j <= 1; j++) {
                    let strTag2: string = (curData[this.mainStateFields[1]] != null && curData[this.mainStateFields[1]] !== "") ? `${strTag}__${(j == 0) ? `${curData[this.mainStateFields[1]]}` : ""}` : strTag;
                    if (this.mainStateFields.length >= 3) {
                        for (let k = 0; k <= 1; k++) {
                            let strTag3: string = (curData[this.mainStateFields[2]] != null && curData[this.mainStateFields[2]] !== "") ? `${strTag2}__${(k == 0) ? `${curData[this.mainStateFields[2]]}` : ""}` : strTag2;
                            // 判断是否存在
                            return this.allDeMainStateMap.get(strTag3);
                        }
                    } else {
                        return this.allDeMainStateMap.get(strTag2);
                    }
                }
            } else {
                return this.allDeMainStateMap.get(strTag);
            }
        }
        return null;
    }

    /**
    * 获取数据对象当前操作标识
    * 
    * @param data 当前数据
    * @memberof  UIServiceBase
    */
    protected getDEMainStateOPPrivs(data: any) {
        if (this.getDEMainStateTag(data)) {
            return this.allDeMainStateOPPrivsMap.get((this.getDEMainStateTag(data) as string));
        } else {
            return null;
        }
    }

    /**
    * 获取数据对象所有的操作标识
    * 
    * @param data 当前数据
    * @memberof  UIServiceBase
    */
    protected getAllOPPrivs(data: any) {
        return this.authService.getOPPrivs(this.getDEMainStateOPPrivs(data));
    }

}