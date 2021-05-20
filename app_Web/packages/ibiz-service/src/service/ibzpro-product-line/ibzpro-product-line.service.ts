import { IBZProProductLineBaseService } from './ibzpro-product-line-base.service';

/**
 * 产品线服务
 *
 * @export
 * @class IBZProProductLineService
 * @extends {IBZProProductLineBaseService}
 */
export class IBZProProductLineService extends IBZProProductLineBaseService {
    /**
     * Creates an instance of IBZProProductLineService.
     * @memberof IBZProProductLineService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProProductLineService')) {
            return ___ibz___.sc.get('IBZProProductLineService');
        }
        ___ibz___.sc.set('IBZProProductLineService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProProductLineService}
     * @memberof IBZProProductLineService
     */
    static getInstance(): IBZProProductLineService {
        if (!___ibz___.sc.has('IBZProProductLineService')) {
            new IBZProProductLineService();
        }
        return ___ibz___.sc.get('IBZProProductLineService');
    }
}
export default IBZProProductLineService;
