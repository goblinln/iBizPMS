import { IbzProjectMemberUIServiceBase } from './ibz-project-member-ui-service-base';

/**
 * 项目相关成员UI服务对象
 *
 * @export
 * @class IbzProjectMemberUIService
 */
export default class IbzProjectMemberUIService extends IbzProjectMemberUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProjectMemberUIService
     */
    private static basicUIServiceInstance: IbzProjectMemberUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProjectMemberUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProjectMemberUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProjectMemberUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProjectMemberUIService
     */
    public static getInstance(context: any): IbzProjectMemberUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProjectMemberUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProjectMemberUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProjectMemberUIService.UIServiceMap.set(context.srfdynainstid, new IbzProjectMemberUIService({context:context}));
            }
            return IbzProjectMemberUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}