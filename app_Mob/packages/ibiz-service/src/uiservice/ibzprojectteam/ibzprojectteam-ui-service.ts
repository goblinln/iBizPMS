import { IBZPROJECTTEAMUIServiceBase } from './ibzprojectteam-ui-service-base';

/**
 * 项目团队UI服务对象
 *
 * @export
 * @class IBZPROJECTTEAMUIService
 */
export default class IBZPROJECTTEAMUIService extends IBZPROJECTTEAMUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZPROJECTTEAMUIService
     */
    private static basicUIServiceInstance: IBZPROJECTTEAMUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZPROJECTTEAMUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZPROJECTTEAMUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZPROJECTTEAMUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZPROJECTTEAMUIService
     */
    public static getInstance(context: any): IBZPROJECTTEAMUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZPROJECTTEAMUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZPROJECTTEAMUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZPROJECTTEAMUIService.UIServiceMap.set(context.srfdynainstid, new IBZPROJECTTEAMUIService({context:context}));
            }
            return IBZPROJECTTEAMUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}