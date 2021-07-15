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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {IbzProjectMemberService}
     * @memberof IbzProjectMemberService
     */
    static getInstance(context?: any): IbzProjectMemberService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzProjectMemberService` : `IbzProjectMemberService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzProjectMemberService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzProjectMemberService;
