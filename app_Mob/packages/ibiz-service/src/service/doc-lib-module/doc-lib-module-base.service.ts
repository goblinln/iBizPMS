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
    protected APPNAME = 'Mob';
    protected APPDENAME = 'DocLibModule';
    protected APPDENAMEPLURAL = 'DocLibModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        doclib: 'root',
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
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getDocLibService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.doclibname = data.name;
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDocLibModule): Promise<IDocLibModule> {
        return super.updateLocal(context, new DocLibModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDocLibModule = {}): Promise<IDocLibModule> {
        if (_context.doclib && _context.doclib !== '') {
            const s = await ___ibz___.gs.getDocLibService();
            const data = await s.getLocal2(_context, _context.doclib);
            if (data) {
                entity.doclibname = data.name;
                entity.root = data.id;
            }
        }
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
     * Collect
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Collect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
        }
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/collect`, _data);
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
        if (_context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules`, _data);
        }
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
     * DocLibModuleNFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async DocLibModuleNFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/doclibmodulenfavorite`, _data);
        }
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
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/doclibmodulefavorite`, _data);
        }
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
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/fix`, _data);
        }
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/fix`, _data);
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
        if (_context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
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
        if (_context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/doclibmodules/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/doclibmodules/getdraft`, _data);
        return res;
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
        if (_context.doclib && _context.doclibmodule) {
            return this.http.delete(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        }
        return this.http.delete(`/doclibmodules/${_context.doclibmodule}`);
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
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
        }
        return this.http.post(`/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
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
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/doclibmodules/${_context.doclibmodule}`, _data);
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchalldoclibmodule_custom`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchalldoclibmodule`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchchildmodulebyparent`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchchildmodulebyrealparent`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchdefault`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchparentmodule`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchrootmodulemulu`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchrootmodulemulubyroot`, _data);
        }
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
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchrootmodulemulubysrfparentkey`, _data);
        }
        return this.http.post(`/doclibmodules/fetchrootmodulemulubysrfparentkey`, _data);
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
        if (_context.doclib && _context.doclibmodule) {
            return this.http.get(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/select`);
        }
        return this.http.get(`/doclibmodules/${_context.doclibmodule}/select`);
    }

    /**
     * CollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async CollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/doclibmodules/collectbatch`,_data);
    }

    /**
     * UnCollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async UnCollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/doclibmodules/uncollectbatch`,_data);
    }
}
