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
        product: 'product',
        project: 'project',
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
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.productsn;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.projectsn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestReport): Promise<ITestReport> {
        return super.updateLocal(context, new TestReport(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestReport = {}): Promise<ITestReport> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.productsn;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.projectsn;
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
     * getinfotesttask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async getinfotesttask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotesttask`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotesttask`, _data);
        }
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
        if (_context.project && _context.testreport) {
            return this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/select`);
        }
        if (_context.product && _context.testreport) {
            return this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/select`);
        }
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
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testreports`, _data);
        }
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}`, _data);
        }
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
        if (_context.project && _context.testreport) {
            return this.http.delete(`/projects/${_context.project}/testreports/${_context.testreport}`);
        }
        if (_context.product && _context.testreport) {
            return this.http.delete(`/products/${_context.product}/testreports/${_context.testreport}`);
        }
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
        if (_context.project && _context.testreport) {
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}`);
            return res;
        }
        if (_context.product && _context.testreport) {
            const res = await this.http.get(`/products/${_context.product}/testreports/${_context.testreport}`);
            return res;
        }
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
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testreports/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testreports/getdraft`, _data);
            return res;
        }
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotaskovbytime`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotaskovbytime`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/getinfotaskovbytime`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotesttaskovproject`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotesttaskovproject`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/getinfotesttaskovproject`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotesttaskproject`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotesttaskproject`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/getinfotesttaskproject`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotesttaskr`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotesttaskr`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/getinfotesttaskr`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/getinfotesttasks`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/getinfotesttasks`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/getinfotesttasks`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/gettestreportbasicinfo`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/gettestreportbasicinfo`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/gettestreportbasicinfo`, _data);
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
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/gettestreportproject`, _data);
        }
        if (_context.product && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/gettestreportproject`, _data);
        }
        return this.http.put(`/testreports/${_context.testreport}/gettestreportproject`, _data);
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
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/testreports/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testreports/fetchdefault`, _data);
        }
        return this.http.post(`/testreports/fetchdefault`, _data);
    }
}
