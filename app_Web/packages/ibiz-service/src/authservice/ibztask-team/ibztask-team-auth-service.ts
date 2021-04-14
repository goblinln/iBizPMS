import { IBZTaskTeamAuthServiceBase } from './ibztask-team-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class IBZTaskTeamAuthService
 * @extends {IBZTaskTeamAuthServiceBase}
 */
export default class IBZTaskTeamAuthService extends IBZTaskTeamAuthServiceBase {

    /**
     * Creates an instance of  IBZTaskTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}