import { DYNAFILTERUtilServiceBase } from './dynafilter-util-service-base';

/**
 * 移动端动态搜索栏功能服务对象
 *
 * @export
 * @class DYNAFILTERUtilService
 */
export default class DYNAFILTERUtilService extends DYNAFILTERUtilServiceBase {

    /**
    * 基础Util服务实例
    * 
    * @private
    * @type {DYNAFILTERUtilService}
    * @memberof DYNAFILTERUtilService
    */
     private static basicUtilServiceInstance: DYNAFILTERUtilService;

    /**
    * 动态模型服务存储Map对象
    *
    * @private
    * @type {Map<string, any>}
    * @memberof DYNAFILTERUtilService
    */
     private static UtilServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DYNAFILTERUtilService.
     * 
     * @param {*} [opts={}]
     * @memberof  DYNAFILTERUtilService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DYNAFILTERUtilService
     */
     public static getInstance(context: any): DYNAFILTERUtilService {
        if (!this.basicUtilServiceInstance) {
            this.basicUtilServiceInstance = new DYNAFILTERUtilService();
        }
        if (!context.srfdynainstid) {
            return this.basicUtilServiceInstance;
        } else {
            if (!DYNAFILTERUtilService.UtilServiceMap.get(context.srfdynainstid)) {
                DYNAFILTERUtilService.UtilServiceMap.set(context.srfdynainstid, new DYNAFILTERUtilService({context:context}));
            }
            return DYNAFILTERUtilService.UtilServiceMap.get(context.srfdynainstid);
        }
    }

}