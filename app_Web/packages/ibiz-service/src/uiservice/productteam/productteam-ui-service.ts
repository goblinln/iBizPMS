import { PRODUCTTEAMUIServiceBase } from './productteam-ui-service-base';

/**
 * 产品团队UI服务对象
 *
 * @export
 * @class PRODUCTTEAMUIService
 */
export default class PRODUCTTEAMUIService extends PRODUCTTEAMUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof PRODUCTTEAMUIService
     */
    private static basicUIServiceInstance: PRODUCTTEAMUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof PRODUCTTEAMUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PRODUCTTEAMUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  PRODUCTTEAMUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PRODUCTTEAMUIService
     */
    public static getInstance(context: any): PRODUCTTEAMUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PRODUCTTEAMUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PRODUCTTEAMUIService.UIServiceMap.get(context.srfdynainstid)) {
                PRODUCTTEAMUIService.UIServiceMap.set(context.srfdynainstid, new PRODUCTTEAMUIService({context:context}));
            }
            return PRODUCTTEAMUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}