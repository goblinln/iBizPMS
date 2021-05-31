import { ModuleAuthServiceBase } from './module-auth-service-base';


/**
 * 模块权限服务对象
 *
 * @export
 * @class ModuleAuthService
 * @extends {ModuleAuthServiceBase}
 */
export default class ModuleAuthService extends ModuleAuthServiceBase {

    /**
     * Creates an instance of  ModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}