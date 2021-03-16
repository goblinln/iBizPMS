import { PSSystemDBCfgUIServiceBase } from './pssystem-dbcfg-ui-service-base';

/**
 * 系统数据库UI服务对象
 *
 * @export
 * @class PSSystemDBCfgUIService
 */
export default class PSSystemDBCfgUIService extends PSSystemDBCfgUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof PSSystemDBCfgUIService
     */
    private static basicUIServiceInstance: PSSystemDBCfgUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof PSSystemDBCfgUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSystemDBCfgUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSystemDBCfgUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSystemDBCfgUIService
     */
    public static getInstance(context: any): PSSystemDBCfgUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSystemDBCfgUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSystemDBCfgUIService.UIServiceMap.get(context.srfdynainstid)) {
                PSSystemDBCfgUIService.UIServiceMap.set(context.srfdynainstid, new PSSystemDBCfgUIService({context:context}));
            }
            return PSSystemDBCfgUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}