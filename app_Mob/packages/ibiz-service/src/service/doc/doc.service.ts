import { DocBaseService } from './doc-base.service';

/**
 * 文档服务
 *
 * @export
 * @class DocService
 * @extends {DocBaseService}
 */
export class DocService extends DocBaseService {
    /**
     * Creates an instance of DocService.
     * @memberof DocService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DocService')) {
            return ___ibz___.sc.get('DocService');
        }
        ___ibz___.sc.set('DocService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DocService}
     * @memberof DocService
     */
    static getInstance(): DocService {
        if (!___ibz___.sc.has('DocService')) {
            new DocService();
        }
        return ___ibz___.sc.get('DocService');
    }
}
export default DocService;
