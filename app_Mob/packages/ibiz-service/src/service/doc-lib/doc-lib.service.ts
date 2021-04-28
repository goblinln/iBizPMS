import { DocLibBaseService } from './doc-lib-base.service';

/**
 * 文档库服务
 *
 * @export
 * @class DocLibService
 * @extends {DocLibBaseService}
 */
export class DocLibService extends DocLibBaseService {
    /**
     * Creates an instance of DocLibService.
     * @memberof DocLibService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DocLibService')) {
            return ___ibz___.sc.get('DocLibService');
        }
        ___ibz___.sc.set('DocLibService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DocLibService}
     * @memberof DocLibService
     */
    static getInstance(): DocLibService {
        if (!___ibz___.sc.has('DocLibService')) {
            new DocLibService();
        }
        return ___ibz___.sc.get('DocLibService');
    }
}
export default DocLibService;
