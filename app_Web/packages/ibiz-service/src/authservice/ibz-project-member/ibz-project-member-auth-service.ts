import { IbzProjectMemberAuthServiceBase } from './ibz-project-member-auth-service-base';


/**
 * 项目相关成员权限服务对象
 *
 * @export
 * @class IbzProjectMemberAuthService
 * @extends {IbzProjectMemberAuthServiceBase}
 */
export default class IbzProjectMemberAuthService extends IbzProjectMemberAuthServiceBase {

    /**
     * Creates an instance of  IbzProjectMemberAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProjectMemberAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}