import { TestReportBaseService } from './test-report-base.service';

/**
 * 测试报告服务
 *
 * @export
 * @class TestReportService
 * @extends {TestReportBaseService}
 */
export class TestReportService extends TestReportBaseService {
    /**
     * Creates an instance of TestReportService.
     * @memberof TestReportService
     */
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestReportService}
     * @memberof TestReportService
     */
	static getInstance(context?: any): TestReportService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestReportService` : `TestReportService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestReportService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }

    /**
     * GetInfoTestTaskR接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskR(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        if (context.project && context.testreport) {
            let masterData: any = {};
            Object.assign(data, masterData);
            context.testreport = 0;
            data.id = 0;
            data.project = context.project;
            if (!data.tasks) {
                data.tasks = context.tasks;
            }
            let res: any = await this.http.put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttaskr`, data, isloading);

            return res;
        }
        if (context.product && context.testreport) {
            let masterData: any = {};
            Object.assign(data, masterData);
            context.testreport = 0;
            data.id = 0;
            data.project = context.project;
            if (!data.tasks) {
                data.tasks = context.tasks;
            }
            let res: any = await this.http.put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttaskr`, data, isloading);

            return res;
        }
        context.testreport = 0;
        data.id = 0;
        data.project = context.project;
        if (!data.tasks) {
            data.tasks = context.tasks;
        }
        let res: any = this.http.put(`/testreports/${context.testreport}/getinfotesttaskr`, data, isloading);
        return res;
    }

    /**
     * GetInfoTestTaskS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskS(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        if (context.project && context.testreport) {
            let masterData: any = {};
            Object.assign(data, masterData);
            context.testreport = 0;
            data.id = 0;
            data.testreport = "0";
            data.project = context.project;
            data.tasks = context.tasks;
            let res: any = await this.http.put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttasks`, data, isloading);
            return res;
        }
        if (context.product && context.testreport) {
            let masterData: any = {};
            Object.assign(data, masterData);
            context.testreport = 0;
            data.id = 0;
            data.testreport = "0";
            data.project = context.project;
            data.tasks = context.tasks;
            let res: any = await this.http.put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttasks`, data, isloading);
            return res;
        }
        context.testreport = 0;
        data.id = 0;
        data.testreport = "0";
        data.project = context.project;
        data.tasks = context.tasks;
        let res: any = this.http.put(`/testreports/${context.testreport}/getinfotesttasks`, data, isloading);
        return res;
    }
}
export default TestReportService;
