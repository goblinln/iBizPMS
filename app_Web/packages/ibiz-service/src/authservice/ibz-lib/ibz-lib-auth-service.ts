import { IbzLibAuthServiceBase } from './ibz-lib-auth-service-base';


/**
 * 用例库权限服务对象
 *
 * @export
 * @class IbzLibAuthService
 * @extends {IbzLibAuthServiceBase}
 */
export default class IbzLibAuthService extends IbzLibAuthServiceBase {

    /**
     * Creates an instance of  IbzLibAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}