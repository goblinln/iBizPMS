import { SysOrganizationBaseService } from './sys-organization-base.service';

/**
 * 单位服务
 *
 * @export
 * @class SysOrganizationService
 * @extends {SysOrganizationBaseService}
 */
export class SysOrganizationService extends SysOrganizationBaseService {
    /**
     * Creates an instance of SysOrganizationService.
     * @memberof SysOrganizationService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysOrganizationService')) {
            return ___ibz___.sc.get('SysOrganizationService');
        }
        ___ibz___.sc.set('SysOrganizationService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysOrganizationService}
     * @memberof SysOrganizationService
     */
    static getInstance(): SysOrganizationService {
        if (!___ibz___.sc.has('SysOrganizationService')) {
            new SysOrganizationService();
        }
        return ___ibz___.sc.get('SysOrganizationService');
    }
}
export default SysOrganizationService;
