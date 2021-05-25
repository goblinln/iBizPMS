import { IbzProBuildActionBaseService } from './ibz-pro-build-action-base.service';

/**
 * 版本日志服务
 *
 * @export
 * @class IbzProBuildActionService
 * @extends {IbzProBuildActionBaseService}
 */
export class IbzProBuildActionService extends IbzProBuildActionBaseService {
    /**
     * Creates an instance of IbzProBuildActionService.
     * @memberof IbzProBuildActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProBuildActionService')) {
            return ___ibz___.sc.get('IbzProBuildActionService');
        }
        ___ibz___.sc.set('IbzProBuildActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProBuildActionService}
     * @memberof IbzProBuildActionService
     */
    static getInstance(): IbzProBuildActionService {
        if (!___ibz___.sc.has('IbzProBuildActionService')) {
            new IbzProBuildActionService();
        }
        return ___ibz___.sc.get('IbzProBuildActionService');
    }
}
export default IbzProBuildActionService;
