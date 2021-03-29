import { BranchBaseService } from './branch-base.service';

/**
 * 产品的分支和平台信息服务
 *
 * @export
 * @class BranchService
 * @extends {BranchBaseService}
 */
export class BranchService extends BranchBaseService {
    /**
     * Creates an instance of BranchService.
     * @memberof BranchService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('BranchService')) {
            return ___ibz___.sc.get('BranchService');
        }
        ___ibz___.sc.set('BranchService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {BranchService}
     * @memberof BranchService
     */
    static getInstance(): BranchService {
        if (!___ibz___.sc.has('BranchService')) {
            new BranchService();
        }
        return ___ibz___.sc.get('BranchService');
    }
}
export default BranchService;
