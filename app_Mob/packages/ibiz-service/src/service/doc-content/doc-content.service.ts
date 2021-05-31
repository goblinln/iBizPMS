import { DocContentBaseService } from './doc-content-base.service';

/**
 * 文档内容服务
 *
 * @export
 * @class DocContentService
 * @extends {DocContentBaseService}
 */
export class DocContentService extends DocContentBaseService {
    /**
     * Creates an instance of DocContentService.
     * @memberof DocContentService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DocContentService')) {
            return ___ibz___.sc.get('DocContentService');
        }
        ___ibz___.sc.set('DocContentService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DocContentService}
     * @memberof DocContentService
     */
    static getInstance(): DocContentService {
        if (!___ibz___.sc.has('DocContentService')) {
            new DocContentService();
        }
        return ___ibz___.sc.get('DocContentService');
    }
}
export default DocContentService;
