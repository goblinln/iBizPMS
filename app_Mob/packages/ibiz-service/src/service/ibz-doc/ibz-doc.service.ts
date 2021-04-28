import { IBzDocBaseService } from './ibz-doc-base.service';

/**
 * 文档服务
 *
 * @export
 * @class IBzDocService
 * @extends {IBzDocBaseService}
 */
export class IBzDocService extends IBzDocBaseService {
    /**
     * Creates an instance of IBzDocService.
     * @memberof IBzDocService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBzDocService')) {
            return ___ibz___.sc.get('IBzDocService');
        }
        ___ibz___.sc.set('IBzDocService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBzDocService}
     * @memberof IBzDocService
     */
    static getInstance(): IBzDocService {
        if (!___ibz___.sc.has('IBzDocService')) {
            new IBzDocService();
        }
        return ___ibz___.sc.get('IBzDocService');
    }
}
export default IBzDocService;
