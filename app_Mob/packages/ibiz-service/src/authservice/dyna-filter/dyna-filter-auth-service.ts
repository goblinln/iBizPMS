import { DynaFilterAuthServiceBase } from './dyna-filter-auth-service-base';


/**
 * 动态搜索栏权限服务对象
 *
 * @export
 * @class DynaFilterAuthService
 * @extends {DynaFilterAuthServiceBase}
 */
export default class DynaFilterAuthService extends DynaFilterAuthServiceBase {

    /**
     * Creates an instance of  DynaFilterAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaFilterAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}