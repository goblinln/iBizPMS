import { IBIZProTagBaseService } from './ibizpro-tag-base.service';

/**
 * 标签服务
 *
 * @export
 * @class IBIZProTagService
 * @extends {IBIZProTagBaseService}
 */
export class IBIZProTagService extends IBIZProTagBaseService {
    /**
     * Creates an instance of IBIZProTagService.
     * @memberof IBIZProTagService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBIZProTagService')) {
            return ___ibz___.sc.get('IBIZProTagService');
        }
        ___ibz___.sc.set('IBIZProTagService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBIZProTagService}
     * @memberof IBIZProTagService
     */
    static getInstance(): IBIZProTagService {
        if (!___ibz___.sc.has('IBIZProTagService')) {
            new IBIZProTagService();
        }
        return ___ibz___.sc.get('IBIZProTagService');
    }
}
export default IBIZProTagService;
