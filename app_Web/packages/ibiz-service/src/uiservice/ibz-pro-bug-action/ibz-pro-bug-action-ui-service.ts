import { IbzProBugActionUIServiceBase } from './ibz-pro-bug-action-ui-service-base';

/**
 * Bug日志UI服务对象
 *
 * @export
 * @class IbzProBugActionUIService
 */
export default class IbzProBugActionUIService extends IbzProBugActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProBugActionUIService
     */
    private static basicUIServiceInstance: IbzProBugActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProBugActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProBugActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProBugActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProBugActionUIService
     */
    public static getInstance(context: any): IbzProBugActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProBugActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProBugActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProBugActionUIService.UIServiceMap.set(context.srfdynainstid, new IbzProBugActionUIService({context:context}));
            }
            return IbzProBugActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}