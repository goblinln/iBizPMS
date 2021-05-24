import { IBZProProductActionBaseService } from './ibzpro-product-action-base.service';

/**
 * 产品日志服务
 *
 * @export
 * @class IBZProProductActionService
 * @extends {IBZProProductActionBaseService}
 */
export class IBZProProductActionService extends IBZProProductActionBaseService {
    /**
     * Creates an instance of IBZProProductActionService.
     * @memberof IBZProProductActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProProductActionService')) {
            return ___ibz___.sc.get('IBZProProductActionService');
        }
        ___ibz___.sc.set('IBZProProductActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProProductActionService}
     * @memberof IBZProProductActionService
     */
    static getInstance(): IBZProProductActionService {
        if (!___ibz___.sc.has('IBZProProductActionService')) {
            new IBZProProductActionService();
        }
        return ___ibz___.sc.get('IBZProProductActionService');
    }
}
export default IBZProProductActionService;
