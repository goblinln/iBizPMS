import { IBZPROJECTTEAMBaseService } from './ibzprojectteam-base.service';

/**
 * 项目团队服务
 *
 * @export
 * @class IBZPROJECTTEAMService
 * @extends {IBZPROJECTTEAMBaseService}
 */
export class IBZPROJECTTEAMService extends IBZPROJECTTEAMBaseService {
    /**
     * Creates an instance of IBZPROJECTTEAMService.
     * @memberof IBZPROJECTTEAMService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZPROJECTTEAMService')) {
            return ___ibz___.sc.get('IBZPROJECTTEAMService');
        }
        ___ibz___.sc.set('IBZPROJECTTEAMService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZPROJECTTEAMService}
     * @memberof IBZPROJECTTEAMService
     */
    static getInstance(): IBZPROJECTTEAMService {
        if (!___ibz___.sc.has('IBZPROJECTTEAMService')) {
            new IBZPROJECTTEAMService();
        }
        return ___ibz___.sc.get('IBZPROJECTTEAMService');
    }
}
export default IBZPROJECTTEAMService;
