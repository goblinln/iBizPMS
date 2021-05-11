
import { IPSAppDEUIAction } from "@ibiz/dynamic-model-api";
 
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
         
    }

}
