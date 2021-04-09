import { PSSystemDBCfgBaseService } from './pssystem-dbcfg-base.service';

/**
 * 系统数据库服务
 *
 * @export
 * @class PSSystemDBCfgService
 * @extends {PSSystemDBCfgBaseService}
 */
export class PSSystemDBCfgService extends PSSystemDBCfgBaseService {
    /**
     * Creates an instance of PSSystemDBCfgService.
     * @memberof PSSystemDBCfgService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('PSSystemDBCfgService')) {
            return ___ibz___.sc.get('PSSystemDBCfgService');
        }
        ___ibz___.sc.set('PSSystemDBCfgService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {PSSystemDBCfgService}
     * @memberof PSSystemDBCfgService
     */
    static getInstance(): PSSystemDBCfgService {
        if (!___ibz___.sc.has('PSSystemDBCfgService')) {
            new PSSystemDBCfgService();
        }
        return ___ibz___.sc.get('PSSystemDBCfgService');
    }
}
export default PSSystemDBCfgService;