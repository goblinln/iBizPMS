import { GroupBaseService } from './group-base.service';

/**
 * 群组服务
 *
 * @export
 * @class GroupService
 * @extends {GroupBaseService}
 */
export class GroupService extends GroupBaseService {
    /**
     * Creates an instance of GroupService.
     * @memberof GroupService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('GroupService')) {
            return ___ibz___.sc.get('GroupService');
        }
        ___ibz___.sc.set('GroupService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {GroupService}
     * @memberof GroupService
     */
    static getInstance(): GroupService {
        if (!___ibz___.sc.has('GroupService')) {
            new GroupService();
        }
        return ___ibz___.sc.get('GroupService');
    }
}
export default GroupService;