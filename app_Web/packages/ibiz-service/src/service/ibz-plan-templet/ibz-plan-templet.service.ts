import { IbzPlanTempletBaseService } from './ibz-plan-templet-base.service';

/**
 * 计划模板服务
 *
 * @export
 * @class IbzPlanTempletService
 * @extends {IbzPlanTempletBaseService}
 */
export class IbzPlanTempletService extends IbzPlanTempletBaseService {
    /**
     * Creates an instance of IbzPlanTempletService.
     * @memberof IbzPlanTempletService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzPlanTempletService')) {
            return ___ibz___.sc.get('IbzPlanTempletService');
        }
        ___ibz___.sc.set('IbzPlanTempletService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzPlanTempletService}
     * @memberof IbzPlanTempletService
     */
    static getInstance(): IbzPlanTempletService {
        if (!___ibz___.sc.has('IbzPlanTempletService')) {
            new IbzPlanTempletService();
        }
        return ___ibz___.sc.get('IbzPlanTempletService');
    }
}
export default IbzPlanTempletService;
