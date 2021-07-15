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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {IBZProProductService}
     * @memberof IBZProProductService
     */
    static getInstance(context?: any): IBZProProductService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBZProProductService` : `IBZProProductService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBZProProductService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBZProProductService;
