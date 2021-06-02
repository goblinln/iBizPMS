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
        project: 'project',
        test: 'product',
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
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestReport): Promise<ITestReport> {
        return super.updateLocal(context, new TestReport(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestReport = {}): Promise<ITestReport> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        if (_context.test && _context.test !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(_context, _context.test);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
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
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/testreports/${_context.testreport}`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * LoadTestReportBasic
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async LoadTestReportBasic(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}/loadtestreportbasic`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/loadtestreportbasic`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/loadtestreportbasic`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * InitTestTaskReportMulti
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async InitTestTaskReportMulti(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}/inittesttaskreportmulti`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/inittesttaskreportmulti`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/inittesttaskreportmulti`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports`, _data);
        }
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/testreports`, _data);
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testreports`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && true) {
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/fetchdefault`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/testreports/fetchdefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/testreports/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * InitTestTaskReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async InitTestTaskReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}/inittesttaskreport`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/inittesttaskreport`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/inittesttaskreport`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * InitProjectReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async InitProjectReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}/initprojectreport`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/initprojectreport`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/initprojectreport`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.testreport) {
            return this.http.delete(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}`);
        }
        if (_context.test && _context.testreport) {
            return this.http.delete(`/tests/${_context.test}/testreports/${_context.testreport}`);
        }
        if (_context.project && _context.testreport) {
            return this.http.delete(`/projects/${_context.project}/testreports/${_context.testreport}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/accounts/${_context.account}/projects/${_context.project}/testreports/getdraft`, _data);
            return res;
        }
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/testreports/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testreports/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.testreport) {
            const res = await this.http.get(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}`);
            return res;
        }
        if (_context.test && _context.testreport) {
            const res = await this.http.get(`/tests/${_context.test}/testreports/${_context.testreport}`);
            return res;
        }
        if (_context.project && _context.testreport) {
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * InitProjectDuringReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async InitProjectDuringReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/testreports/${_context.testreport}/initprojectduringreport`, _data);
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/initprojectduringreport`, _data);
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/initprojectduringreport`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
