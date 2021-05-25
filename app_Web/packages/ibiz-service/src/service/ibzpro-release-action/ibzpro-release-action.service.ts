import { IBZProReleaseActionBaseService } from './ibzpro-release-action-base.service';

/**
 * 发布日志服务
 *
 * @export
 * @class IBZProReleaseActionService
 * @extends {IBZProReleaseActionBaseService}
 */
export class IBZProReleaseActionService extends IBZProReleaseActionBaseService {
    /**
     * Creates an instance of IBZProReleaseActionService.
     * @memberof IBZProReleaseActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProReleaseActionService')) {
            return ___ibz___.sc.get('IBZProReleaseActionService');
        }
        ___ibz___.sc.set('IBZProReleaseActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProReleaseActionService}
     * @memberof IBZProReleaseActionService
     */
    static getInstance(): IBZProReleaseActionService {
        if (!___ibz___.sc.has('IBZProReleaseActionService')) {
            new IBZProReleaseActionService();
        }
        return ___ibz___.sc.get('IBZProReleaseActionService');
    }
}
export default IBZProReleaseActionService;
