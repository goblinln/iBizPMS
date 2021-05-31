import { IbzTaskestimateBaseService } from './ibz-taskestimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class IbzTaskestimateService
 * @extends {IbzTaskestimateBaseService}
 */
export class IbzTaskestimateService extends IbzTaskestimateBaseService {
    /**
     * Creates an instance of IbzTaskestimateService.
     * @memberof IbzTaskestimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzTaskestimateService')) {
            return ___ibz___.sc.get('IbzTaskestimateService');
        }
        ___ibz___.sc.set('IbzTaskestimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzTaskestimateService}
     * @memberof IbzTaskestimateService
     */
    static getInstance(): IbzTaskestimateService {
        if (!___ibz___.sc.has('IbzTaskestimateService')) {
            new IbzTaskestimateService();
        }
        return ___ibz___.sc.get('IbzTaskestimateService');
    }
}
export default IbzTaskestimateService;
