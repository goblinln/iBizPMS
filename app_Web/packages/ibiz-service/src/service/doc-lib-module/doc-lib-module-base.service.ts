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
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDir
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchDir(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchAllDir
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibModuleService
     */
    async FetchAllDir(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules`, _data);
        }
        if (_context.product && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules`, _data);
        }
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/getdraft`, _data);
            return res;
        }
        if (_context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/doclibmodules/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && _context.doclibmodule) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        }
        if (_context.doclib && _context.doclibmodule) {
            return this.http.delete(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
