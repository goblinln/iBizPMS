import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDocLibModule, DocLibModule } from '../../entities';
import keys from '../../entities/doc-lib-module/doc-lib-module-keys';

/**
 * 文档库分类服务对象基类
 *
 * @export
 * @class DocLibModuleBaseService
 * @extends {EntityBaseService}
 */
export class DocLibModuleBaseService extends EntityBaseService<IDocLibModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'DocLibModule';
    protected APPDENAMEPLURAL = 'DocLibModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IDocLibModule): DocLibModule {
        return new DocLibModule(data);
    }

    async addLocal(context: IContext, entity: IDocLibModule): Promise<IDocLibModule | null> {
        return this.cache.add(context, new DocLibModule(entity) as any);
    }

    async createLocal(context: IContext, entity: IDocLibModule): Promise<IDocLibModule | null> {
        return super.createLocal(context, new DocLibModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDocLibModule> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDocLibModule): Promise<IDocLibModule> {
        return super.updateLocal(context, new DocLibModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDocLibModule = {}): Promise<IDocLibModule> {
        return new DocLibModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
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
     * @memberof DocLibModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibmodules/${_context.doclibmodule}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/doclibmodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/doclibmodules/${_context.doclibmodule}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/doclibmodules/${_context.doclibmodule}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/doclibmodules/${_context.doclibmodule}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/doclibmodules/getdraft`, _data);
        return res;
    }
    /**
     * Collect
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Collect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/collect`, _data);
    }
    /**
     * DocLibModuleNFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async DocLibModuleNFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/doclibmodulenfavorite`, _data);
    }
    /**
     * DoclibModuleFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async DoclibModuleFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/doclibmodulefavorite`, _data);
    }
    /**
     * Fix
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Fix(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/fix`, _data);
    }
    /**
     * UnCollect
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async UnCollect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
    }
    /**
     * FetchAllDocLibModule_Custom
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchAllDocLibModule_Custom(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchalldoclibmodule_custom`, _data);
    }
    /**
     * FetchAllDoclibModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchAllDoclibModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchalldoclibmodule`, _data);
    }
    /**
     * FetchChildModuleByParent
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchChildModuleByParent(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchchildmodulebyparent`, _data);
    }
    /**
     * FetchChildModuleByRealParent
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchChildModuleByRealParent(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchchildmodulebyrealparent`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchdefault`, _data);
    }
    /**
     * FetchMyFavourites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchMyFavourites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchmyfavourites`, _data);
    }
    /**
     * FetchParentModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchParentModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchparentmodule`, _data);
    }
    /**
     * FetchRootModuleMuLu
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchRootModuleMuLu(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchrootmodulemulu`, _data);
    }
    /**
     * FetchRootModuleMuLuByRoot
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchRootModuleMuLuByRoot(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchrootmodulemulubyroot`, _data);
    }
    /**
     * FetchRootModuleMuLuBysrfparentkey
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchRootModuleMuLuBysrfparentkey(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/doclibmodules/fetchrootmodulemulubysrfparentkey`, _data);
    }
}