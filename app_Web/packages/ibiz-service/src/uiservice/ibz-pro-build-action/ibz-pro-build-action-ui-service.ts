import { IbzProBuildActionUIServiceBase } from './ibz-pro-build-action-ui-service-base';

/**
 * 版本日志UI服务对象
 *
 * @export
 * @class IbzProBuildActionUIService
 */
export default class IbzProBuildActionUIService extends IbzProBuildActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProBuildActionUIService
     */
    private static basicUIServiceInstance: IbzProBuildActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProBuildActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProBuildActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProBuildActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProBuildActionUIService
     */
    public static getInstance(context: any): IbzProBuildActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProBuildActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProBuildActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProBuildActionUIService.UIServiceMap.set(context.srfdynainstid, new IbzProBuildActionUIService({context:context}));
            }
            return IbzProBuildActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}