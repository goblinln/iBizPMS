import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProStoryModule, IBZProStoryModule } from '../../entities';
import keys from '../../entities/ibzpro-story-module/ibzpro-story-module-keys';

/**
 * 需求模块服务对象基类
 *
 * @export
 * @class IBZProStoryModuleBaseService
 * @extends {EntityBaseService}
 */
export class IBZProStoryModuleBaseService extends EntityBaseService<IIBZProStoryModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProStoryModule';
    protected APPDENAMEPLURAL = 'IBZProStoryModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IIBZProStoryModule): IBZProStoryModule {
        return new IBZProStoryModule(data);
    }

    async addLocal(context: IContext, entity: IIBZProStoryModule): Promise<IIBZProStoryModule | null> {
        return this.cache.add(context, new IBZProStoryModule(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProStoryModule): Promise<IIBZProStoryModule | null> {
        return super.createLocal(context, new IBZProStoryModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProStoryModule> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProStoryModule): Promise<IIBZProStoryModule> {
        return super.updateLocal(context, new IBZProStoryModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProStoryModule = {}): Promise<IIBZProStoryModule> {
        return new IBZProStoryModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
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
     * @memberof IBZProStoryModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzprostorymodules/${_context.ibzprostorymodule}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzprostorymodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzprostorymodules/${_context.ibzprostorymodule}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzprostorymodules/${_context.ibzprostorymodule}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzprostorymodules/${_context.ibzprostorymodule}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprostorymodules/getdraft`, _data);
        return res;
    }
    /**
     * SyncFromIBIZ
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async SyncFromIBIZ(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzprostorymodules/${_context.ibzprostorymodule}/syncfromibiz`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzprostorymodules/fetchdefault`, _data);
    }
}
