import { SysUpdateFeaturesBaseService } from './sys-update-features-base.service';

/**
 * 系统更新功能服务
 *
 * @export
 * @class SysUpdateFeaturesService
 * @extends {SysUpdateFeaturesBaseService}
 */
export class SysUpdateFeaturesService extends SysUpdateFeaturesBaseService {
    /**
     * Creates an instance of SysUpdateFeaturesService.
     * @memberof SysUpdateFeaturesService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysUpdateFeaturesService')) {
            return ___ibz___.sc.get('SysUpdateFeaturesService');
        }
        ___ibz___.sc.set('SysUpdateFeaturesService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysUpdateFeaturesService}
     * @memberof SysUpdateFeaturesService
     */
    static getInstance(): SysUpdateFeaturesService {
        if (!___ibz___.sc.has('SysUpdateFeaturesService')) {
            new SysUpdateFeaturesService();
        }
        return ___ibz___.sc.get('SysUpdateFeaturesService');
    }
}
export default SysUpdateFeaturesService;
