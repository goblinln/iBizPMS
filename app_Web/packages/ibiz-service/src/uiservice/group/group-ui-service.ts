import { GroupUIServiceBase } from './group-ui-service-base';

/**
 * 群组UI服务对象
 *
 * @export
 * @class GroupUIService
 */
export default class GroupUIService extends GroupUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof GroupUIService
     */
    private static basicUIServiceInstance: GroupUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof GroupUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  GroupUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  GroupUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof GroupUIService
     */
    public static getInstance(context: any): GroupUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new GroupUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!GroupUIService.UIServiceMap.get(context.srfdynainstid)) {
                GroupUIService.UIServiceMap.set(context.srfdynainstid, new GroupUIService({context:context}));
            }
            return GroupUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}