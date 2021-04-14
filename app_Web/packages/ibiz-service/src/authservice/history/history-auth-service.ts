import { HistoryAuthServiceBase } from './history-auth-service-base';


/**
 * 操作历史权限服务对象
 *
 * @export
 * @class HistoryAuthService
 * @extends {HistoryAuthServiceBase}
 */
export default class HistoryAuthService extends HistoryAuthServiceBase {

    /**
     * Creates an instance of  HistoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  HistoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}