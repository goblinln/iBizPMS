import { IbzproProjectUserTaskBaseService } from './ibzpro-project-user-task-base.service';

/**
 * 项目汇报用户任务服务
 *
 * @export
 * @class IbzproProjectUserTaskService
 * @extends {IbzproProjectUserTaskBaseService}
 */
export class IbzproProjectUserTaskService extends IbzproProjectUserTaskBaseService {
    /**
     * Creates an instance of IbzproProjectUserTaskService.
     * @memberof IbzproProjectUserTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzproProjectUserTaskService')) {
            return ___ibz___.sc.get('IbzproProjectUserTaskService');
        }
        ___ibz___.sc.set('IbzproProjectUserTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzproProjectUserTaskService}
     * @memberof IbzproProjectUserTaskService
     */
    static getInstance(): IbzproProjectUserTaskService {
        if (!___ibz___.sc.has('IbzproProjectUserTaskService')) {
            new IbzproProjectUserTaskService();
        }
        return ___ibz___.sc.get('IbzproProjectUserTaskService');
    }
}
export default IbzproProjectUserTaskService;