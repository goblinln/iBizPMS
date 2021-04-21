import { IbzAgentBaseService } from './ibz-agent-base.service';

/**
 * 代理服务
 *
 * @export
 * @class IbzAgentService
 * @extends {IbzAgentBaseService}
 */
export class IbzAgentService extends IbzAgentBaseService {
    /**
     * Creates an instance of IbzAgentService.
     * @memberof IbzAgentService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzAgentService')) {
            return ___ibz___.sc.get('IbzAgentService');
        }
        ___ibz___.sc.set('IbzAgentService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzAgentService}
     * @memberof IbzAgentService
     */
    static getInstance(): IbzAgentService {
        if (!___ibz___.sc.has('IbzAgentService')) {
            new IbzAgentService();
        }
        return ___ibz___.sc.get('IbzAgentService');
    }
}
export default IbzAgentService;
