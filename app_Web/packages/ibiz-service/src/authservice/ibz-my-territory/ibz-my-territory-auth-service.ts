import { IbzMyTerritoryAuthServiceBase } from './ibz-my-territory-auth-service-base';


/**
 * 我的地盘权限服务对象
 *
 * @export
 * @class IbzMyTerritoryAuthService
 * @extends {IbzMyTerritoryAuthServiceBase}
 */
export default class IbzMyTerritoryAuthService extends IbzMyTerritoryAuthServiceBase {

    /**
     * Creates an instance of  IbzMyTerritoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMyTerritoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}