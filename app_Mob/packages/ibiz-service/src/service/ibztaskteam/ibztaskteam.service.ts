import { IbztaskteamBaseService } from './ibztaskteam-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class IbztaskteamService
 * @extends {IbztaskteamBaseService}
 */
export class IbztaskteamService extends IbztaskteamBaseService {
    /**
     * Creates an instance of IbztaskteamService.
     * @memberof IbztaskteamService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbztaskteamService')) {
            return ___ibz___.sc.get('IbztaskteamService');
        }
        ___ibz___.sc.set('IbztaskteamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbztaskteamService}
     * @memberof IbztaskteamService
     */
    static getInstance(): IbztaskteamService {
        if (!___ibz___.sc.has('IbztaskteamService')) {
            new IbztaskteamService();
        }
        return ___ibz___.sc.get('IbztaskteamService');
    }
}
export default IbztaskteamService;
