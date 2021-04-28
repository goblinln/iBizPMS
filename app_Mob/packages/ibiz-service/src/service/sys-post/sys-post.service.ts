import { SysPostBaseService } from './sys-post-base.service';

/**
 * 岗位服务
 *
 * @export
 * @class SysPostService
 * @extends {SysPostBaseService}
 */
export class SysPostService extends SysPostBaseService {
    /**
     * Creates an instance of SysPostService.
     * @memberof SysPostService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysPostService')) {
            return ___ibz___.sc.get('SysPostService');
        }
        ___ibz___.sc.set('SysPostService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysPostService}
     * @memberof SysPostService
     */
    static getInstance(): SysPostService {
        if (!___ibz___.sc.has('SysPostService')) {
            new SysPostService();
        }
        return ___ibz___.sc.get('SysPostService');
    }
}
export default SysPostService;
