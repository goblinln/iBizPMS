import { IBZTaskEstimateBaseService } from './ibztask-estimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class IBZTaskEstimateService
 * @extends {IBZTaskEstimateBaseService}
 */
export class IBZTaskEstimateService extends IBZTaskEstimateBaseService {
    /**
     * Creates an instance of IBZTaskEstimateService.
     * @memberof IBZTaskEstimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZTaskEstimateService')) {
            return ___ibz___.sc.get('IBZTaskEstimateService');
        }
        ___ibz___.sc.set('IBZTaskEstimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZTaskEstimateService}
     * @memberof IBZTaskEstimateService
     */
    static getInstance(): IBZTaskEstimateService {
        if (!___ibz___.sc.has('IBZTaskEstimateService')) {
            new IBZTaskEstimateService();
        }
        return ___ibz___.sc.get('IBZTaskEstimateService');
    }
}
export default IBZTaskEstimateService;