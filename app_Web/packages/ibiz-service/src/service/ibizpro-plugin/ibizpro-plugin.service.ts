import { IBIZProPluginBaseService } from './ibizpro-plugin-base.service';

/**
 * 系统插件服务
 *
 * @export
 * @class IBIZProPluginService
 * @extends {IBIZProPluginBaseService}
 */
export class IBIZProPluginService extends IBIZProPluginBaseService {
    /**
     * Creates an instance of IBIZProPluginService.
     * @memberof IBIZProPluginService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBIZProPluginService')) {
            return ___ibz___.sc.get('IBIZProPluginService');
        }
        ___ibz___.sc.set('IBIZProPluginService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBIZProPluginService}
     * @memberof IBIZProPluginService
     */
    static getInstance(): IBIZProPluginService {
        if (!___ibz___.sc.has('IBIZProPluginService')) {
            new IBIZProPluginService();
        }
        return ___ibz___.sc.get('IBIZProPluginService');
    }
}
export default IBIZProPluginService;
