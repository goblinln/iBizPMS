import { IbizproIndexBaseService } from './ibizpro-index-base.service';

/**
 * 索引检索服务
 *
 * @export
 * @class IbizproIndexService
 * @extends {IbizproIndexBaseService}
 */
export class IbizproIndexService extends IbizproIndexBaseService {
    /**
     * Creates an instance of IbizproIndexService.
     * @memberof IbizproIndexService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproIndexService')) {
            return ___ibz___.sc.get('IbizproIndexService');
        }
        ___ibz___.sc.set('IbizproIndexService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproIndexService}
     * @memberof IbizproIndexService
     */
    static getInstance(): IbizproIndexService {
        if (!___ibz___.sc.has('IbizproIndexService')) {
            new IbizproIndexService();
        }
        return ___ibz___.sc.get('IbizproIndexService');
    }
}
export default IbizproIndexService;