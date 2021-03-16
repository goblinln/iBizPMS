import { CaseStepUIServiceBase } from './case-step-ui-service-base';

/**
 * 用例步骤UI服务对象
 *
 * @export
 * @class CaseStepUIService
 */
export default class CaseStepUIService extends CaseStepUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof CaseStepUIService
     */
    private static basicUIServiceInstance: CaseStepUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof CaseStepUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseStepUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStepUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseStepUIService
     */
    public static getInstance(context: any): CaseStepUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseStepUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseStepUIService.UIServiceMap.get(context.srfdynainstid)) {
                CaseStepUIService.UIServiceMap.set(context.srfdynainstid, new CaseStepUIService({context:context}));
            }
            return CaseStepUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}