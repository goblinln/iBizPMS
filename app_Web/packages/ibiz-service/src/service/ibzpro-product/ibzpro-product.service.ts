import { IBZProProductBaseService } from './ibzpro-product-base.service';

/**
 * 平台产品服务
 *
 * @export
 * @class IBZProProductService
 * @extends {IBZProProductBaseService}
 */
export class IBZProProductService extends IBZProProductBaseService {
    /**
     * Creates an instance of IBZProProductService.
     * @memberof IBZProProductService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProProductService')) {
            return ___ibz___.sc.get('IBZProProductService');
        }
        ___ibz___.sc.set('IBZProProductService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProProductService}
     * @memberof IBZProProductService
     */
    static getInstance(): IBZProProductService {
        if (!___ibz___.sc.has('IBZProProductService')) {
            new IBZProProductService();
        }
        return ___ibz___.sc.get('IBZProProductService');
    }
}
export default IBZProProductService;
