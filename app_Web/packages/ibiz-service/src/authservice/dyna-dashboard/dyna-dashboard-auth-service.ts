import { DynaDashboardAuthServiceBase } from './dyna-dashboard-auth-service-base';


/**
 * 动态数据看板权限服务对象
 *
 * @export
 * @class DynaDashboardAuthService
 * @extends {DynaDashboardAuthServiceBase}
 */
export default class DynaDashboardAuthService extends DynaDashboardAuthServiceBase {

    /**
     * Creates an instance of  DynaDashboardAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaDashboardAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}