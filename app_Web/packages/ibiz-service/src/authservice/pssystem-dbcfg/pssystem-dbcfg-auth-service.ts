import { PSSystemDBCfgAuthServiceBase } from './pssystem-dbcfg-auth-service-base';


/**
 * 系统数据库权限服务对象
 *
 * @export
 * @class PSSystemDBCfgAuthService
 * @extends {PSSystemDBCfgAuthServiceBase}
 */
export default class PSSystemDBCfgAuthService extends PSSystemDBCfgAuthServiceBase {

    /**
     * Creates an instance of  PSSystemDBCfgAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSystemDBCfgAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}