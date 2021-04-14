import { BranchUIServiceBase } from './branch-ui-service-base';

/**
 * 产品的分支和平台信息UI服务对象
 *
 * @export
 * @class BranchUIService
 */
export default class BranchUIService extends BranchUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof BranchUIService
     */
    private static basicUIServiceInstance: BranchUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof BranchUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BranchUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  BranchUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BranchUIService
     */
    public static getInstance(context: any): BranchUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BranchUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BranchUIService.UIServiceMap.get(context.srfdynainstid)) {
                BranchUIService.UIServiceMap.set(context.srfdynainstid, new BranchUIService({context:context}));
            }
            return BranchUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}