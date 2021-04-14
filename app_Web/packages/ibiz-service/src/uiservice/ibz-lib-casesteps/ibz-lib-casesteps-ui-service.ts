import { IbzLibCasestepsUIServiceBase } from './ibz-lib-casesteps-ui-service-base';

/**
 * 用例库用例步骤UI服务对象
 *
 * @export
 * @class IbzLibCasestepsUIService
 */
export default class IbzLibCasestepsUIService extends IbzLibCasestepsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzLibCasestepsUIService
     */
    private static basicUIServiceInstance: IbzLibCasestepsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzLibCasestepsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibCasestepsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCasestepsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibCasestepsUIService
     */
    public static getInstance(context: any): IbzLibCasestepsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibCasestepsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibCasestepsUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzLibCasestepsUIService.UIServiceMap.set(context.srfdynainstid, new IbzLibCasestepsUIService({context:context}));
            }
            return IbzLibCasestepsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}