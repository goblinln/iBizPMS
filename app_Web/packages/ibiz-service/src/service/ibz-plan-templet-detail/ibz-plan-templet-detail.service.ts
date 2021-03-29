import { IbzPlanTempletDetailBaseService } from './ibz-plan-templet-detail-base.service';

/**
 * 计划模板详情服务
 *
 * @export
 * @class IbzPlanTempletDetailService
 * @extends {IbzPlanTempletDetailBaseService}
 */
export class IbzPlanTempletDetailService extends IbzPlanTempletDetailBaseService {
    /**
     * Creates an instance of IbzPlanTempletDetailService.
     * @memberof IbzPlanTempletDetailService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzPlanTempletDetailService')) {
            return ___ibz___.sc.get('IbzPlanTempletDetailService');
        }
        ___ibz___.sc.set('IbzPlanTempletDetailService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzPlanTempletDetailService}
     * @memberof IbzPlanTempletDetailService
     */
    static getInstance(): IbzPlanTempletDetailService {
        if (!___ibz___.sc.has('IbzPlanTempletDetailService')) {
            new IbzPlanTempletDetailService();
        }
        return ___ibz___.sc.get('IbzPlanTempletDetailService');
    }
}
export default IbzPlanTempletDetailService;
