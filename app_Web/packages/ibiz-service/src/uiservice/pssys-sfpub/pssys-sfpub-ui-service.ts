import { PSSysSFPubUIServiceBase } from './pssys-sfpub-ui-service-base';

/**
 * 后台服务架构UI服务对象
 *
 * @export
 * @class PSSysSFPubUIService
 */
export default class PSSysSFPubUIService extends PSSysSFPubUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof PSSysSFPubUIService
     */
    private static basicUIServiceInstance: PSSysSFPubUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof PSSysSFPubUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSysSFPubUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysSFPubUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSysSFPubUIService
     */
    public static getInstance(context: any): PSSysSFPubUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSysSFPubUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSysSFPubUIService.UIServiceMap.get(context.srfdynainstid)) {
                PSSysSFPubUIService.UIServiceMap.set(context.srfdynainstid, new PSSysSFPubUIService({context:context}));
            }
            return PSSysSFPubUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}