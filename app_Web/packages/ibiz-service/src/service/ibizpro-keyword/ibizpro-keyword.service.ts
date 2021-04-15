import { IBIZProKeywordBaseService } from './ibizpro-keyword-base.service';

/**
 * 关键字服务
 *
 * @export
 * @class IBIZProKeywordService
 * @extends {IBIZProKeywordBaseService}
 */
export class IBIZProKeywordService extends IBIZProKeywordBaseService {
    /**
     * Creates an instance of IBIZProKeywordService.
     * @memberof IBIZProKeywordService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBIZProKeywordService')) {
            return ___ibz___.sc.get('IBIZProKeywordService');
        }
        ___ibz___.sc.set('IBIZProKeywordService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBIZProKeywordService}
     * @memberof IBIZProKeywordService
     */
    static getInstance(): IBIZProKeywordService {
        if (!___ibz___.sc.has('IBIZProKeywordService')) {
            new IBIZProKeywordService();
        }
        return ___ibz___.sc.get('IBIZProKeywordService');
    }
}
export default IBIZProKeywordService;