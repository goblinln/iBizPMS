import { IbzLibUIServiceBase } from './ibz-lib-ui-service-base';

/**
 * 用例库UI服务对象
 *
 * @export
 * @class IbzLibUIService
 */
export default class IbzLibUIService extends IbzLibUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzLibUIService
     */
    private static basicUIServiceInstance: IbzLibUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzLibUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibUIService
     */
    public static getInstance(context: any): IbzLibUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzLibUIService.UIServiceMap.set(context.srfdynainstid, new IbzLibUIService({context:context}));
            }
            return IbzLibUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}