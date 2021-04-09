import { IbzMonthlyAuthServiceBase } from './ibz-monthly-auth-service-base';


/**
 * 月报权限服务对象
 *
 * @export
 * @class IbzMonthlyAuthService
 * @extends {IbzMonthlyAuthServiceBase}
 */
export default class IbzMonthlyAuthService extends IbzMonthlyAuthServiceBase {

    /**
     * Creates an instance of  IbzMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}