import { IbzWeeklyAuthServiceBase } from './ibz-weekly-auth-service-base';


/**
 * 周报权限服务对象
 *
 * @export
 * @class IbzWeeklyAuthService
 * @extends {IbzWeeklyAuthServiceBase}
 */
export default class IbzWeeklyAuthService extends IbzWeeklyAuthServiceBase {

    /**
     * Creates an instance of  IbzWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}