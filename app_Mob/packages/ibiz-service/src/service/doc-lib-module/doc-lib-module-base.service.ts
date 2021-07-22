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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLibModule.json';
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/collect`, _data);
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[Collect函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules`, _data);
            return res;
        }
        if (_context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules`, _data);
            return res;
        }
        if (_context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules`, _data);
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/getdraft`, _data);
            return res;
        }
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
    this.log.warn([`[DocLibModule]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doclibmodule) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
        if (_context.project && _context.doclib && _context.doclibmodule) {
            const res = await this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
            const res = await this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
            const res = await this.http.delete(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`);
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}/uncollect`, _data);
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[UnCollect函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
            return res;
        }
        if (_context.doclib && _context.doclibmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/doclibs/${_context.doclib}/doclibmodules/${_context.doclibmodule}`, _data);
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAllDir');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAllDir');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAllDir');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchalldir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAllDir');
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[FetchAllDir函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDir');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDir');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDir');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchdir`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDir');
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[FetchDir函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
    this.log.warn([`[DocLibModule]>>>[FetchMyFavourites函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        if(_context.product && _context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
            return res;
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
            return res;
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
            return res;
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/collectbatch`,_data);
            return res;
        }
        this.log.warn([`[DocLibModule]>>>[CollectBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.product && _context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
            return res;
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
            return res;
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
            return res;
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/doclibs/${_context.doclib}/doclibmodules/uncollectbatch`,_data);
            return res;
        }
        this.log.warn([`[DocLibModule]>>>[UnCollectBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
