import { IBZTestSuiteActionBaseService } from './ibztest-suite-action-base.service';

/**
 * 套件日志服务
 *
 * @export
 * @class IBZTestSuiteActionService
 * @extends {IBZTestSuiteActionBaseService}
 */
export class IBZTestSuiteActionService extends IBZTestSuiteActionBaseService {
    /**
     * Creates an instance of IBZTestSuiteActionService.
     * @memberof IBZTestSuiteActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZTestSuiteActionService')) {
            return ___ibz___.sc.get('IBZTestSuiteActionService');
        }
        ___ibz___.sc.set('IBZTestSuiteActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZTestSuiteActionService}
     * @memberof IBZTestSuiteActionService
     */
    static getInstance(): IBZTestSuiteActionService {
        if (!___ibz___.sc.has('IBZTestSuiteActionService')) {
            new IBZTestSuiteActionService();
        }
        return ___ibz___.sc.get('IBZTestSuiteActionService');
    }
}
export default IBZTestSuiteActionService;
