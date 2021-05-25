import { IBZProToDoActionBaseService } from './ibzpro-to-do-action-base.service';

/**
 * ToDo日志服务
 *
 * @export
 * @class IBZProToDoActionService
 * @extends {IBZProToDoActionBaseService}
 */
export class IBZProToDoActionService extends IBZProToDoActionBaseService {
    /**
     * Creates an instance of IBZProToDoActionService.
     * @memberof IBZProToDoActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProToDoActionService')) {
            return ___ibz___.sc.get('IBZProToDoActionService');
        }
        ___ibz___.sc.set('IBZProToDoActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProToDoActionService}
     * @memberof IBZProToDoActionService
     */
    static getInstance(): IBZProToDoActionService {
        if (!___ibz___.sc.has('IBZProToDoActionService')) {
            new IBZProToDoActionService();
        }
        return ___ibz___.sc.get('IBZProToDoActionService');
    }
}
export default IBZProToDoActionService;
