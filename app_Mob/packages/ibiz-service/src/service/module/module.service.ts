import { ModuleBaseService } from './module-base.service';

/**
 * 模块服务
 *
 * @export
 * @class ModuleService
 * @extends {ModuleBaseService}
 */
export class ModuleService extends ModuleBaseService {
    /**
     * Creates an instance of ModuleService.
     * @memberof ModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ModuleService')) {
            return ___ibz___.sc.get('ModuleService');
        }
        ___ibz___.sc.set('ModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ModuleService}
     * @memberof ModuleService
     */
    static getInstance(): ModuleService {
        if (!___ibz___.sc.has('ModuleService')) {
            new ModuleService();
        }
        return ___ibz___.sc.get('ModuleService');
    }
}
export default ModuleService;
