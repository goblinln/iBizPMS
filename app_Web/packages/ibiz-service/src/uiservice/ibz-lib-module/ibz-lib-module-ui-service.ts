import { IbzLibModuleUIServiceBase } from './ibz-lib-module-ui-service-base';

/**
 * 用例库模块UI服务对象
 *
 * @export
 * @class IbzLibModuleUIService
 */
export default class IbzLibModuleUIService extends IbzLibModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzLibModuleUIService
     */
    private static basicUIServiceInstance: IbzLibModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzLibModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibModuleUIService
     */
    public static getInstance(context: any): IbzLibModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibModuleUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzLibModuleUIService.UIServiceMap.set(context.srfdynainstid, new IbzLibModuleUIService({context:context}));
            }
            return IbzLibModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}