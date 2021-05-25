import { IbzProBugActionBaseService } from './ibz-pro-bug-action-base.service';

/**
 * Bug日志服务
 *
 * @export
 * @class IbzProBugActionService
 * @extends {IbzProBugActionBaseService}
 */
export class IbzProBugActionService extends IbzProBugActionBaseService {
    /**
     * Creates an instance of IbzProBugActionService.
     * @memberof IbzProBugActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProBugActionService')) {
            return ___ibz___.sc.get('IbzProBugActionService');
        }
        ___ibz___.sc.set('IbzProBugActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProBugActionService}
     * @memberof IbzProBugActionService
     */
    static getInstance(): IbzProBugActionService {
        if (!___ibz___.sc.has('IbzProBugActionService')) {
            new IbzProBugActionService();
        }
        return ___ibz___.sc.get('IbzProBugActionService');
    }
}
export default IbzProBugActionService;
