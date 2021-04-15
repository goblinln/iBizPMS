import { CompanyUIServiceBase } from './company-ui-service-base';

/**
 * 公司UI服务对象
 *
 * @export
 * @class CompanyUIService
 */
export default class CompanyUIService extends CompanyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof CompanyUIService
     */
    private static basicUIServiceInstance: CompanyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof CompanyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CompanyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CompanyUIService
     */
    public static getInstance(context: any): CompanyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CompanyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CompanyUIService.UIServiceMap.get(context.srfdynainstid)) {
                CompanyUIService.UIServiceMap.set(context.srfdynainstid, new CompanyUIService({context:context}));
            }
            return CompanyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}