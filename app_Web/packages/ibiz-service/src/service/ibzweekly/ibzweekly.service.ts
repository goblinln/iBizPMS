import { IBZWEEKLYBaseService } from './ibzweekly-base.service';

/**
 * 周报服务
 *
 * @export
 * @class IBZWEEKLYService
 * @extends {IBZWEEKLYBaseService}
 */
export class IBZWEEKLYService extends IBZWEEKLYBaseService {
    /**
     * Creates an instance of IBZWEEKLYService.
     * @memberof IBZWEEKLYService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZWEEKLYService')) {
            return ___ibz___.sc.get('IBZWEEKLYService');
        }
        ___ibz___.sc.set('IBZWEEKLYService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZWEEKLYService}
     * @memberof IBZWEEKLYService
     */
    static getInstance(): IBZWEEKLYService {
        if (!___ibz___.sc.has('IBZWEEKLYService')) {
            new IBZWEEKLYService();
        }
        return ___ibz___.sc.get('IBZWEEKLYService');
    }
}
export default IBZWEEKLYService;
