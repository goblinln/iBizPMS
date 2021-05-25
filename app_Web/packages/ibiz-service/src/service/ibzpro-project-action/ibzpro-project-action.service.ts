import { IBZProProjectActionBaseService } from './ibzpro-project-action-base.service';

/**
 * 项目日志服务
 *
 * @export
 * @class IBZProProjectActionService
 * @extends {IBZProProjectActionBaseService}
 */
export class IBZProProjectActionService extends IBZProProjectActionBaseService {
    /**
     * Creates an instance of IBZProProjectActionService.
     * @memberof IBZProProjectActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProProjectActionService')) {
            return ___ibz___.sc.get('IBZProProjectActionService');
        }
        ___ibz___.sc.set('IBZProProjectActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProProjectActionService}
     * @memberof IBZProProjectActionService
     */
    static getInstance(): IBZProProjectActionService {
        if (!___ibz___.sc.has('IBZProProjectActionService')) {
            new IBZProProjectActionService();
        }
        return ___ibz___.sc.get('IBZProProjectActionService');
    }
}
export default IBZProProjectActionService;
