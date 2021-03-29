import { ReleaseBaseService } from './release-base.service';

/**
 * 发布服务
 *
 * @export
 * @class ReleaseService
 * @extends {ReleaseBaseService}
 */
export class ReleaseService extends ReleaseBaseService {
    /**
     * Creates an instance of ReleaseService.
     * @memberof ReleaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ReleaseService')) {
            return ___ibz___.sc.get('ReleaseService');
        }
        ___ibz___.sc.set('ReleaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ReleaseService}
     * @memberof ReleaseService
     */
    static getInstance(): ReleaseService {
        if (!___ibz___.sc.has('ReleaseService')) {
            new ReleaseService();
        }
        return ___ibz___.sc.get('ReleaseService');
    }
}
export default ReleaseService;
