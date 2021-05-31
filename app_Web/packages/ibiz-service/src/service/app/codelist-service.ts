import { IPSAppCodeList } from "@ibiz/dynamic-model-api";
import { GetModelService } from "ibiz-core";

/**
 * 临时补充代码表服务
 * 
 * 代码表服务类
 */
export class CodeListService {

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
    constructor() { }

    /**
     * 通过代码表标识获取代码表对象
     *
     * @memberof CodeListService
     */
    public async getCodeListByTag(context: any, tag: string) {
        let appModelService: any = await GetModelService(context);
        if (appModelService?.app) {
            const codelist: IPSAppCodeList = appModelService.app.getAllPSAppCodeLists().find((item: IPSAppCodeList) => {
                return item.codeName == tag;
            })
            return codelist;
        }
    }

}