import { IbzProjectMemberBaseService } from './ibz-project-member-base.service';

/**
 * 项目相关成员服务
 *
 * @export
 * @class IbzProjectMemberService
 * @extends {IbzProjectMemberBaseService}
 */
export class IbzProjectMemberService extends IbzProjectMemberBaseService {
    /**
     * Creates an instance of IbzProjectMemberService.
     * @memberof IbzProjectMemberService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProjectMemberService')) {
            return ___ibz___.sc.get('IbzProjectMemberService');
        }
        ___ibz___.sc.set('IbzProjectMemberService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProjectMemberService}
     * @memberof IbzProjectMemberService
     */
    static getInstance(): IbzProjectMemberService {
        if (!___ibz___.sc.has('IbzProjectMemberService')) {
            new IbzProjectMemberService();
        }
        return ___ibz___.sc.get('IbzProjectMemberService');
    }
}
export default IbzProjectMemberService;