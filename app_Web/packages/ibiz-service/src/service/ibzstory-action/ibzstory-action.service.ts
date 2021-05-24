import { IBZStoryActionBaseService } from './ibzstory-action-base.service';

/**
 * 需求日志服务
 *
 * @export
 * @class IBZStoryActionService
 * @extends {IBZStoryActionBaseService}
 */
export class IBZStoryActionService extends IBZStoryActionBaseService {
    /**
     * Creates an instance of IBZStoryActionService.
     * @memberof IBZStoryActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZStoryActionService')) {
            return ___ibz___.sc.get('IBZStoryActionService');
        }
        ___ibz___.sc.set('IBZStoryActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZStoryActionService}
     * @memberof IBZStoryActionService
     */
    static getInstance(): IBZStoryActionService {
        if (!___ibz___.sc.has('IBZStoryActionService')) {
            new IBZStoryActionService();
        }
        return ___ibz___.sc.get('IBZStoryActionService');
    }
}
export default IBZStoryActionService;
