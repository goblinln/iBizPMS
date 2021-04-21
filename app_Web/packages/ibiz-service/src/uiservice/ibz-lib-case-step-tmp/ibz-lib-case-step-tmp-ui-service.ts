import { IbzLibCaseStepTmpUIServiceBase } from './ibz-lib-case-step-tmp-ui-service-base';

/**
 * 用例库用例步骤UI服务对象
 *
 * @export
 * @class IbzLibCaseStepTmpUIService
 */
export default class IbzLibCaseStepTmpUIService extends IbzLibCaseStepTmpUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzLibCaseStepTmpUIService
     */
    private static basicUIServiceInstance: IbzLibCaseStepTmpUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzLibCaseStepTmpUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibCaseStepTmpUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCaseStepTmpUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibCaseStepTmpUIService
     */
    public static getInstance(context: any): IbzLibCaseStepTmpUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibCaseStepTmpUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibCaseStepTmpUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzLibCaseStepTmpUIService.UIServiceMap.set(context.srfdynainstid, new IbzLibCaseStepTmpUIService({context:context}));
            }
            return IbzLibCaseStepTmpUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}