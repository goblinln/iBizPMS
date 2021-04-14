import { IbizproProjectMonthlyBaseService } from './ibizpro-project-monthly-base.service';

/**
 * 项目月报服务
 *
 * @export
 * @class IbizproProjectMonthlyService
 * @extends {IbizproProjectMonthlyBaseService}
 */
export class IbizproProjectMonthlyService extends IbizproProjectMonthlyBaseService {
    /**
     * Creates an instance of IbizproProjectMonthlyService.
     * @memberof IbizproProjectMonthlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProjectMonthlyService')) {
            return ___ibz___.sc.get('IbizproProjectMonthlyService');
        }
        ___ibz___.sc.set('IbizproProjectMonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProjectMonthlyService}
     * @memberof IbizproProjectMonthlyService
     */
    static getInstance(): IbizproProjectMonthlyService {
        if (!___ibz___.sc.has('IbizproProjectMonthlyService')) {
            new IbizproProjectMonthlyService();
        }
        return ___ibz___.sc.get('IbizproProjectMonthlyService');
    }
}
export default IbizproProjectMonthlyService;