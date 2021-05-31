import { IbzReportAuthServiceBase } from './ibz-report-auth-service-base';


/**
 * 汇报汇总权限服务对象
 *
 * @export
 * @class IbzReportAuthService
 * @extends {IbzReportAuthServiceBase}
 */
export default class IbzReportAuthService extends IbzReportAuthServiceBase {

    /**
     * Creates an instance of  IbzReportAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}