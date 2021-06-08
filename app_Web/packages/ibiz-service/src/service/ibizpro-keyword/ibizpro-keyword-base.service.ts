import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBIZProKeyword, IBIZProKeyword } from '../../entities';
import keys from '../../entities/ibizpro-keyword/ibizpro-keyword-keys';

/**
 * 关键字服务对象基类
 *
 * @export
 * @class IBIZProKeywordBaseService
 * @extends {EntityBaseService}
 */
export class IBIZProKeywordBaseService extends EntityBaseService<IIBIZProKeyword> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBIZProKeyword';
    protected APPDENAMEPLURAL = 'IBIZProKeywords';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
    };

    newEntity(data: IIBIZProKeyword): IBIZProKeyword {
        return new IBIZProKeyword(data);
    }

    async addLocal(context: IContext, entity: IIBIZProKeyword): Promise<IIBIZProKeyword | null> {
        return this.cache.add(context, new IBIZProKeyword(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBIZProKeyword): Promise<IIBIZProKeyword | null> {
        return super.createLocal(context, new IBIZProKeyword(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBIZProKeyword> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBIZProKeyword): Promise<IIBIZProKeyword> {
        return super.updateLocal(context, new IBIZProKeyword(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBIZProKeyword = {}): Promise<IIBIZProKeyword> {
        return new IBIZProKeyword(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
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
     * @memberof IBIZProKeywordService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizprokeywords`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizprokeywords/${_context.ibizprokeyword}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizprokeywords/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizprokeywords/${_context.ibizprokeyword}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizprokeywords/${_context.ibizprokeyword}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizprokeywords/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProKeywordService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizprokeywords/${_context.ibizprokeyword}/select`);
    }
}
