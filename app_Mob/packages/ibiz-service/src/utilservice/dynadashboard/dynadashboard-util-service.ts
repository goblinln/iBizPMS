import { DYNADASHBOARDUtilServiceBase } from './dynadashboard-util-service-base';

/**
 * 移动端动态数据看板功能服务对象
 *
 * @export
 * @class DYNADASHBOARDUtilService
 */
export default class DYNADASHBOARDUtilService extends DYNADASHBOARDUtilServiceBase {

    /**
    * 基础Util服务实例
    * 
    * @private
    * @type {DYNADASHBOARDUtilService}
    * @memberof DYNADASHBOARDUtilService
    */
     private static basicUtilServiceInstance: DYNADASHBOARDUtilService;

    /**
    * 动态模型服务存储Map对象
    *
    * @private
    * @type {Map<string, any>}
    * @memberof DYNADASHBOARDUtilService
    */
     private static UtilServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DYNADASHBOARDUtilService.
     * 
     * @param {*} [opts={}]
     * @memberof  DYNADASHBOARDUtilService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DYNADASHBOARDUtilService
     */
     public static getInstance(context: any): DYNADASHBOARDUtilService {
        if (!this.basicUtilServiceInstance) {
            this.basicUtilServiceInstance = new DYNADASHBOARDUtilService();
        }
        if (!context.srfdynainstid) {
            return this.basicUtilServiceInstance;
        } else {
            if (!DYNADASHBOARDUtilService.UtilServiceMap.get(context.srfdynainstid)) {
                DYNADASHBOARDUtilService.UtilServiceMap.set(context.srfdynainstid, new DYNADASHBOARDUtilService({context:context}));
            }
            return DYNADASHBOARDUtilService.UtilServiceMap.get(context.srfdynainstid);
        }
    }

}