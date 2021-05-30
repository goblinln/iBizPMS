
import { IPSAppDEUIAction } from "@ibiz/dynamic-model-api";
import { UIActionTool, Util } from "ibiz-core"; 
/**
 * 文件下载插件类
 *
 * @export
 * @class Fileibzdownload
 * @class Fileibzdownload
 */
export default class Fileibzdownload {

    /**
     * 模型数据
     * 
     * @memberof Fileibzdownload
     */
    private actionModel !: IPSAppDEUIAction;

    

    /**
     * 初始化 Fileibzdownload
     * 
     * @memberof Fileibzdownload
     */
    constructor(opts: any, context?: any) {
        this.actionModel = opts;
    }

    /**
     * 执行界面行为
     * 
     * @param args 
     * @param context 
     * @param params 
     * @param $event 
     * @param xData 
     * @param actionContext 
     * @param srfParentDeName 
     * 
     * @memberof Fileibzdownload
     */
    public async execute(args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
                 let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { file: '%file%' });
        Object.assign(params, { id: '%file%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        // 自定义实体界面行为
        let url = "../ibizutilpms/ztdownload/" + context.file;
        window.open(url);
    }

}
