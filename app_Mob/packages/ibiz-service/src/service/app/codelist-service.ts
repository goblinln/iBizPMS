import { AppServiceBase } from "ibiz-core";

/**
 * 临时补充代码表服务
 * 
 * 代码表服务类
 */
export class CodeListService{

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {CodeListService}
     * @memberof CodeListService
     */
    private static CodeListService: CodeListService;

    /**
     * 获取 CodeListService 单例对象
     *
     * @static
     * @returns {CodeListService}
     * @memberof CodeListService
     */
    public static getInstance(): CodeListService {
        if (!CodeListService.CodeListService) {
            CodeListService.CodeListService = new CodeListService();
        }
        return this.CodeListService;
    }

    /**
     * 构建 CodeListService 对象
     *
     * @memberof CodeListService
     */
    constructor(){}

    /**
     * 通过代码表标识获取代码表对象
     *
     * @memberof CodeListService
     */
    public getCodeListByTag(tag:string){
        const appData:any = AppServiceBase.getInstance().getAppModelDataObject();
        const codelist:any = appData.getAllPSAppCodeLists.find((item:any) =>{
            return item.codeName == tag;
        })
        return codelist;
    }

}