import { IbzproConfigBaseService } from './ibzpro-config-base.service';

/**
 * 系统配置表服务
 *
 * @export
 * @class IbzproConfigService
 * @extends {IbzproConfigBaseService}
 */
export class IbzproConfigService extends IbzproConfigBaseService {
    /**
     * Creates an instance of IbzproConfigService.
     * @memberof IbzproConfigService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzproConfigService')) {
            return ___ibz___.sc.get('IbzproConfigService');
        }
        ___ibz___.sc.set('IbzproConfigService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzproConfigService}
     * @memberof IbzproConfigService
     */
    static getInstance(): IbzproConfigService {
        if (!___ibz___.sc.has('IbzproConfigService')) {
            new IbzproConfigService();
        }
        return ___ibz___.sc.get('IbzproConfigService');
    }
}
export default IbzproConfigService;