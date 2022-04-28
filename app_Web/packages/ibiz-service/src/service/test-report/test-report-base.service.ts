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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TestReport.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        project: 'project',
        test: 'product',
    };

    constructor(opts?: any) {
        super(opts, 'TestReport');
    }

    newEntity(data: ITestReport): TestReport {
        return new TestReport(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestReport> {
        const entity = await super.getLocal(context, srfKey);
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReportService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports`, _data);
            return res;
        }
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports`, _data);
            return res;
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.testreport) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.testreport) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[TestReport]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/getdraft`, _data);
            return res;
        }
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testreports/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testreports/getdraft`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectDuringReport');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectduringreport`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectDuringReport');
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectduringreport`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectDuringReport');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectduringreport`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[InitProjectDuringReport函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectReport');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectreport`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectReport');
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectreport`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitProjectReport');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/initprojectreport`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[InitProjectReport函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReport');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreport`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReport');
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreport`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReport');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreport`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[InitTestTaskReport函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReportMulti');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreportmulti`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReportMulti');
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreportmulti`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'InitTestTaskReportMulti');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/inittesttaskreportmulti`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[InitTestTaskReportMulti函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LoadTestReportBasic');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/loadtestreportbasic`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LoadTestReportBasic');
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/loadtestreportbasic`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LoadTestReportBasic');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/loadtestreportbasic`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[LoadTestReportBasic函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`);
            return res;
        }
        if (_context.test && _context.testreport) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}`);
            return res;
        }
        if (_context.project && _context.testreport) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`);
            return res;
        }
    this.log.warn([`[TestReport]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`, _data);
            return res;
        }
        if (_context.test && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}`, _data);
            return res;
        }
        if (_context.project && _context.testreport) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}`, _data);
            return res;
        }
    this.log.warn([`[TestReport]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[TestReport]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
