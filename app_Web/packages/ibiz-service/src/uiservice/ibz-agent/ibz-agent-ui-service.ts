import { IbzAgentUIServiceBase } from './ibz-agent-ui-service-base';

/**
 * 代理UI服务对象
 *
 * @export
 * @class IbzAgentUIService
 */
export default class IbzAgentUIService extends IbzAgentUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzAgentUIService
     */
    private static basicUIServiceInstance: IbzAgentUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzAgentUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzAgentUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzAgentUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzAgentUIService
     */
    public static getInstance(context: any): IbzAgentUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzAgentUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzAgentUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzAgentUIService.UIServiceMap.set(context.srfdynainstid, new IbzAgentUIService({context:context}));
            }
            return IbzAgentUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}