import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproIndex, IbizproIndex } from '../../entities';
import keys from '../../entities/ibizpro-index/ibizpro-index-keys';

/**
 * 索引检索服务对象基类
 *
 * @export
 * @class IbizproIndexBaseService
 * @extends {EntityBaseService}
 */
export class IbizproIndexBaseService extends EntityBaseService<IIbizproIndex> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproIndex';
    protected APPDENAMEPLURAL = 'IbizproIndices';
    protected APPDEKEY = 'indexid';
    protected APPDETEXT = 'indexname';
    protected quickSearchFields = ['indexname','indexdesc',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproIndex): IbizproIndex {
        return new IbizproIndex(data);
    }

    async addLocal(context: IContext, entity: IIbizproIndex): Promise<IIbizproIndex | null> {
        return this.cache.add(context, new IbizproIndex(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproIndex): Promise<IIbizproIndex | null> {
        return super.createLocal(context, new IbizproIndex(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproIndex> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproIndex): Promise<IIbizproIndex> {
        return super.updateLocal(context, new IbizproIndex(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproIndex = {}): Promise<IIbizproIndex> {
        return new IbizproIndex(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
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
     * @memberof IbizproIndexService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproindices/${_context.ibizproindex}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproindices`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproindices/${_context.ibizproindex}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproindices/${_context.ibizproindex}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproindices/${_context.ibizproindex}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproindices/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproindices/fetchdefault`, _data);
    }
    /**
     * FetchESquery
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async FetchESquery(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproindices/fetchesquery`, _data);
    }
    /**
     * FetchIndexDER
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproIndexService
     */
    async FetchIndexDER(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproindices/fetchindexder`, _data);
    }
}
