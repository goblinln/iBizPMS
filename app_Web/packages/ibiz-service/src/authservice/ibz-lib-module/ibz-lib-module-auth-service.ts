import { IbzLibModuleAuthServiceBase } from './ibz-lib-module-auth-service-base';


/**
 * 用例库模块权限服务对象
 *
 * @export
 * @class IbzLibModuleAuthService
 * @extends {IbzLibModuleAuthServiceBase}
 */
export default class IbzLibModuleAuthService extends IbzLibModuleAuthServiceBase {

    /**
     * Creates an instance of  IbzLibModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}