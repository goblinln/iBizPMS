import { SysUpdateFeaturesUIServiceBase } from './sys-update-features-ui-service-base';

/**
 * 系统更新功能UI服务对象
 *
 * @export
 * @class SysUpdateFeaturesUIService
 */
export default class SysUpdateFeaturesUIService extends SysUpdateFeaturesUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysUpdateFeaturesUIService
     */
    private static basicUIServiceInstance: SysUpdateFeaturesUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysUpdateFeaturesUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUpdateFeaturesUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateFeaturesUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUpdateFeaturesUIService
     */
    public static getInstance(context: any): SysUpdateFeaturesUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUpdateFeaturesUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUpdateFeaturesUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysUpdateFeaturesUIService.UIServiceMap.set(context.srfdynainstid, new SysUpdateFeaturesUIService({context:context}));
            }
            return SysUpdateFeaturesUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}