import { BuildUIServiceBase } from './build-ui-service-base';

/**
 * 版本UI服务对象
 *
 * @export
 * @class BuildUIService
 */
export default class BuildUIService extends BuildUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof BuildUIService
     */
    private static basicUIServiceInstance: BuildUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof BuildUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BuildUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  BuildUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BuildUIService
     */
    public static getInstance(context: any): BuildUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BuildUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BuildUIService.UIServiceMap.get(context.srfdynainstid)) {
                BuildUIService.UIServiceMap.set(context.srfdynainstid, new BuildUIService({context:context}));
            }
            return BuildUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}