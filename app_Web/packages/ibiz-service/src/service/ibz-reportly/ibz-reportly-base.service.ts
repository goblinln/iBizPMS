import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzReportly, IbzReportly } from '../../entities';
import keys from '../../entities/ibz-reportly/ibz-reportly-keys';

/**
 * 汇报服务对象基类
 *
 * @export
 * @class IbzReportlyBaseService
 * @extends {EntityBaseService}
 */
export class IbzReportlyBaseService extends EntityBaseService<IIbzReportly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzReportly';
    protected APPDENAMEPLURAL = 'IbzReportlies';
    protected APPDEKEY = 'ibzreportlyid';
    protected APPDETEXT = 'ibzreportlyname';
    protected quickSearchFields = ['ibzreportlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzReportly): IbzReportly {
        return new IbzReportly(data);
    }

    async addLocal(context: IContext, entity: IIbzReportly): Promise<IIbzReportly | null> {
        return this.cache.add(context, new IbzReportly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzReportly): Promise<IIbzReportly | null> {
        return super.createLocal(context, new IbzReportly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzReportly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzReportly): Promise<IIbzReportly> {
        return super.updateLocal(context, new IbzReportly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzReportly = {}): Promise<IIbzReportly> {
        return new IbzReportly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
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
     * @memberof IbzReportlyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzreportlies/${_context.ibzreportly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzreportlies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzreportlies/${_context.ibzreportly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzreportlies/${_context.ibzreportly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzreportlies/${_context.ibzreportly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzreportlies/getdraft`, _data);
        return res;
    }
    /**
     * HaveRead
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async HaveRead(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreportlies/${_context.ibzreportly}/haveread`, _data);
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/ibzreportlies/${_context.ibzreportly}/submit`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreportlies/fetchdefault`, _data);
    }
    /**
     * FetchMyAllReportly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async FetchMyAllReportly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreportlies/fetchmyallreportly`, _data);
    }
    /**
     * FetchMyReceived
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async FetchMyReceived(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreportlies/fetchmyreceived`, _data);
    }
    /**
     * FetchMyReportlyMob
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportlyService
     */
    async FetchMyReportlyMob(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzreportlies/fetchmyreportlymob`, _data);
    }

    /**
     * HaveReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzReportlyServiceBase
     */
    public async HaveReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzreportlies/havereadbatch`,_data);
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzReportlyServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzreportlies/submitbatch`,_data);
    }
}
