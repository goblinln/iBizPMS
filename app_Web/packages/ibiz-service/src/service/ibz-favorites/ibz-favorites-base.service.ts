import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzFavorites, IbzFavorites } from '../../entities';
import keys from '../../entities/ibz-favorites/ibz-favorites-keys';

/**
 * 收藏服务对象基类
 *
 * @export
 * @class IbzFavoritesBaseService
 * @extends {EntityBaseService}
 */
export class IbzFavoritesBaseService extends EntityBaseService<IIbzFavorites> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzFavorites';
    protected APPDENAMEPLURAL = 'IbzFavorites';
    protected APPDEKEY = 'ibzfavoritesid';
    protected APPDETEXT = 'ibzfavoritesname';
    protected quickSearchFields = ['ibzfavoritesname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzFavorites): IbzFavorites {
        return new IbzFavorites(data);
    }

    async addLocal(context: IContext, entity: IIbzFavorites): Promise<IIbzFavorites | null> {
        return this.cache.add(context, new IbzFavorites(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzFavorites): Promise<IIbzFavorites | null> {
        return super.createLocal(context, new IbzFavorites(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzFavorites> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzFavorites): Promise<IIbzFavorites> {
        return super.updateLocal(context, new IbzFavorites(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzFavorites = {}): Promise<IIbzFavorites> {
        return new IbzFavorites(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
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
     * @memberof IbzFavoritesService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzfavorites/${_context.ibzfavorites}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzfavorites`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzfavorites/${_context.ibzfavorites}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzfavorites/${_context.ibzfavorites}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzfavorites/${_context.ibzfavorites}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzfavorites/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzFavoritesService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzfavorites/fetchdefault`, _data);
    }
}