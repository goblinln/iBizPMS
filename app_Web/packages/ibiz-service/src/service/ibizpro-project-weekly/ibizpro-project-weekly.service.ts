import { IbizproProjectWeeklyBaseService } from './ibizpro-project-weekly-base.service';

/**
 * 项目周报服务
 *
 * @export
 * @class IbizproProjectWeeklyService
 * @extends {IbizproProjectWeeklyBaseService}
 */
export class IbizproProjectWeeklyService extends IbizproProjectWeeklyBaseService {
    /**
     * Creates an instance of IbizproProjectWeeklyService.
     * @memberof IbizproProjectWeeklyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProjectWeeklyService')) {
            return ___ibz___.sc.get('IbizproProjectWeeklyService');
        }
        ___ibz___.sc.set('IbizproProjectWeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProjectWeeklyService}
     * @memberof IbizproProjectWeeklyService
     */
    static getInstance(): IbizproProjectWeeklyService {
        if (!___ibz___.sc.has('IbizproProjectWeeklyService')) {
            new IbizproProjectWeeklyService();
        }
        return ___ibz___.sc.get('IbizproProjectWeeklyService');
    }
}
export default IbizproProjectWeeklyService;
