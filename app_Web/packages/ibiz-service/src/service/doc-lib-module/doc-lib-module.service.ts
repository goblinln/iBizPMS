import { DocLibModuleBaseService } from './doc-lib-module-base.service';

/**
 * 文档库分类服务
 *
 * @export
 * @class DocLibModuleService
 * @extends {DocLibModuleBaseService}
 */
export class DocLibModuleService extends DocLibModuleBaseService {
    /**
     * Creates an instance of DocLibModuleService.
     * @memberof DocLibModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DocLibModuleService')) {
            return ___ibz___.sc.get('DocLibModuleService');
        }
        ___ibz___.sc.set('DocLibModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DocLibModuleService}
     * @memberof DocLibModuleService
     */
    static getInstance(): DocLibModuleService {
        if (!___ibz___.sc.has('DocLibModuleService')) {
            new DocLibModuleService();
        }
        return ___ibz___.sc.get('DocLibModuleService');
    }
}
export default DocLibModuleService;
