import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestReport, TestReport } from '../../entities';
import keys from '../../entities/test-report/test-report-keys';

/**
 * 测试报告服务对象基类
 *
 * @export
 * @class TestReportBaseService
 * @extends {EntityBaseService}
 */
export class TestReportBaseService extends EntityBaseService<ITestReport> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestReport';
    protected APPDENAMEPLURAL = 'TestReports';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: ITestReport): TestReport {
        return new TestReport(data);
    }

    async addLocal(context: IContext, entity: ITestReport): Promise<ITestReport | null> {
        return this.cache.add(context, new TestReport(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestReport): Promise<ITestReport | null> {
        return super.createLocal(context, new TestReport(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestReport> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestReport): Promise<ITestReport> {
        return super.updateLocal(context, new TestReport(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestReport = {}): Promise<ITestReport> {
        return new TestReport(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * getinfotesttask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async getinfotesttask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/testreports/${_context.testreport}/getinfotesttask`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/testreports/${_context.testreport}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testreports`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testreports/${_context.testreport}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/testreports/${_context.testreport}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/testreports/${_context.testreport}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testreports/getdraft`, _data);
        return res;
    }
    /**
     * GetInfoTaskOvByTime
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetInfoTaskOvByTime(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/${_context.testreport}/getinfotaskovbytime`, _data);
    }
    /**
     * GetInfoTestTaskOvProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetInfoTestTaskOvProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/testreports/${_context.testreport}/getinfotesttaskovproject`, _data);
    }
    /**
     * GetInfoTestTaskProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetInfoTestTaskProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/${_context.testreport}/getinfotesttaskproject`, _data);
    }
    /**
     * GetInfoTestTaskR
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetInfoTestTaskR(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/${_context.testreport}/getinfotesttaskr`, _data);
    }
    /**
     * GetInfoTestTaskS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetInfoTestTaskS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/${_context.testreport}/getinfotesttasks`, _data);
    }
    /**
     * GetTestReportBasicInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetTestReportBasicInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/${_context.testreport}/gettestreportbasicinfo`, _data);
    }
    /**
     * GetTestReportProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async GetTestReportProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/testreports/${_context.testreport}/gettestreportproject`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/testreports/fetchdefault`, _data);
    }
}
