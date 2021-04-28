import { IbzReportlyAuthServiceBase } from './ibz-reportly-auth-service-base';


/**
 * 汇报权限服务对象
 *
 * @export
 * @class IbzReportlyAuthService
 * @extends {IbzReportlyAuthServiceBase}
 */
export default class IbzReportlyAuthService extends IbzReportlyAuthServiceBase {

    /**
     * Creates an instance of  IbzReportlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}