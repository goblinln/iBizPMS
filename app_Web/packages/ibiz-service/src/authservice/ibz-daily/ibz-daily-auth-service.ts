import { IbzDailyAuthServiceBase } from './ibz-daily-auth-service-base';


/**
 * 日报权限服务对象
 *
 * @export
 * @class IbzDailyAuthService
 * @extends {IbzDailyAuthServiceBase}
 */
export default class IbzDailyAuthService extends IbzDailyAuthServiceBase {

    /**
     * Creates an instance of  IbzDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}