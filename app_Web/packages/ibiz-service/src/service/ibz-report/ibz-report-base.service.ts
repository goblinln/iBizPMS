import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzReport, IbzReport } from '../../entities';
import keys from '../../entities/ibz-report/ibz-report-keys';

/**
 * 汇报汇总服务对象基类
 *
 * @export
 * @class IbzReportBaseService
 * @extends {EntityBaseService}
 */
export class IbzReportBaseService extends EntityBaseService<IIbzReport> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzReport';
    protected APPDENAMEPLURAL = 'IbzReports';
    protected APPDEKEY = 'ibzdailyid';
    protected APPDETEXT = 'ibzdailyname';
    protected quickSearchFields = ['ibzdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzReport): IbzReport {
        return new IbzReport(data);
    }

    async addLocal(context: IContext, entity: IIbzReport): Promise<IIbzReport | null> {
        return this.cache.add(context, new IbzReport(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzReport): Promise<IIbzReport | null> {
        return super.createLocal(context, new IbzReport(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzReport> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzReport): Promise<IIbzReport> {
        return super.updateLocal(context, new IbzReport(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzReport = {}): Promise<IIbzReport> {
        return new IbzReport(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzreports/${_context.ibzreport}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzreports`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzreports/${_context.ibzreport}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzreports/${_context.ibzreport}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzreports/${_context.ibzreport}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzreports/getdraft`, _data);
        return res;
    }
    /**
     * MyReportINotSubmit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async MyReportINotSubmit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreports/${_context.ibzreport}/myreportinotsubmit`, _data);
    }
    /**
     * ReportIReceived
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async ReportIReceived(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreports/${_context.ibzreport}/reportireceived`, _data);
    }
    /**
     * FetchAllReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async FetchAllReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreports/fetchallreport`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreports/fetchdefault`, _data);
    }
    /**
     * FetchMyReAllReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportService
     */
    async FetchMyReAllReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreports/fetchmyreallreport`, _data);
    }
}
