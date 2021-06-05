import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IReportly, Reportly } from '../../entities';
import keys from '../../entities/reportly/reportly-keys';

/**
 * 汇报服务对象基类
 *
 * @export
 * @class ReportlyBaseService
 * @extends {EntityBaseService}
 */
export class ReportlyBaseService extends EntityBaseService<IReportly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Reportly';
    protected APPDENAMEPLURAL = 'Reportlies';
    protected APPDEKEY = 'ibzreportlyid';
    protected APPDETEXT = 'ibzreportlyname';
    protected quickSearchFields = ['ibzreportlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IReportly): Reportly {
        return new Reportly(data);
    }

    async addLocal(context: IContext, entity: IReportly): Promise<IReportly | null> {
        return this.cache.add(context, new Reportly(entity) as any);
    }

    async createLocal(context: IContext, entity: IReportly): Promise<IReportly | null> {
        return super.createLocal(context, new Reportly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IReportly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IReportly): Promise<IReportly> {
        return super.updateLocal(context, new Reportly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IReportly = {}): Promise<IReportly> {
        return new Reportly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
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
     * @memberof ReportlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/reportlies`, _data);
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/reportlies/${_context.reportly}/submit`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/reportlies/${_context.reportly}`, _data);
    }
    /**
     * Read
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async Read(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/reportlies/${_context.reportly}/read`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/reportlies/fetchdefault`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/reportlies/getdraft`, _data);
        return res;
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/reportlies/${_context.reportly}`);
        return res;
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReportlyServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/reportlies/submitbatch`,_data);
    }

    /**
     * ReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReportlyServiceBase
     */
    public async ReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/reportlies/readbatch`,_data);
    }
}
