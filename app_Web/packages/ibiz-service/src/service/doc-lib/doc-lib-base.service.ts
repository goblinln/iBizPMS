import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDocLib, DocLib } from '../../entities';
import keys from '../../entities/doc-lib/doc-lib-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 文档库服务对象基类
 *
 * @export
 * @class DocLibBaseService
 * @extends {EntityBaseService}
 */
export class DocLibBaseService extends EntityBaseService<IDocLib> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'DocLib';
    protected APPDENAMEPLURAL = 'DocLibs';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/DocLib.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name','productname','projectname',];
    protected selectContextParam = {
        product: 'product',
        project: 'project',
    };

    constructor(opts?: any) {
        super(opts, 'DocLib');
    }

    newEntity(data: IDocLib): DocLib {
        return new DocLib(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDocLib> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDocLib): Promise<IDocLib> {
        return super.updateLocal(context, new DocLib(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDocLib = {}): Promise<IDocLib> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return new DocLib(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getByCustomCond() {
        if (!this.condCache.has('byCustom')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','custom'], ['EQ', 'ORGID',{ type: 'SESSIONCONTEXT', value: 'SRFORGID'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('byCustom', cond);
            }
        }
        return this.condCache.get('byCustom');
    }

    protected getByProductCond() {
        if (!this.condCache.has('byProduct')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('byProduct', cond);
            }
        }
        return this.condCache.get('byProduct');
    }

    protected getByProductNotFilesCond() {
        if (!this.condCache.has('byProductNotFiles')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('byProductNotFiles', cond);
            }
        }
        return this.condCache.get('byProductNotFiles');
    }

    protected getByProjectCond() {
        return this.condCache.get('byProject');
    }

    protected getByProjectNotFilesCond() {
        return this.condCache.get('byProjectNotFiles');
    }

    protected getCurDocLibCond() {
        return this.condCache.get('curDocLib');
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'ORGID',{ type: 'SESSIONCONTEXT', value: 'SRFORGID'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getMyCreateDocLibCond() {
        if (!this.condCache.has('myCreateDocLib')) {
            const strCond: any[] = ['AND', ['EQ', 'CREATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateDocLib', cond);
            }
        }
        return this.condCache.get('myCreateDocLib');
    }

    protected getMyFavouritesCond() {
        return this.condCache.get('myFavourites');
    }

    protected getRootModuleMuLuCond() {
        return this.condCache.get('rootModuleMuLu');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Collect
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async Collect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/collect`, _data);
            return res;
        }
        if (_context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/collect`, _data);
            return res;
        }
        if (_context.product && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/collect`, _data);
            return res;
        }
        const res = await this.http.post(`/doclibs/${_context.doclib}/collect`, _data);
        return res;
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
     * @memberof DocLibService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs`, _data);
            return res;
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${_context.project}/doclibs`, _data);
            return res;
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/doclibs`, _data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/doclibs`, _data);
        return res;
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
     * @memberof DocLibService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.doclib) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.doclib) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        const res = await this.http.get(`/doclibs/${_context.doclib}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
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
     * @memberof DocLibService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/doclibs/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/doclibs/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/doclibs/getdraft`, _data);
        return res;
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
     * @memberof DocLibService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}`);
            return res;
        }
        if (_context.project && _context.doclib) {
            const res = await this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}`);
            return res;
        }
        if (_context.product && _context.doclib) {
            const res = await this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}`);
            return res;
        }
        const res = await this.http.delete(`/doclibs/${_context.doclib}`);
        return res;
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
     * @memberof DocLibService
     */
    async UnCollect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/uncollect`, _data);
            return res;
        }
        if (_context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/uncollect`, _data);
            return res;
        }
        if (_context.product && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/uncollect`, _data);
            return res;
        }
        const res = await this.http.post(`/doclibs/${_context.doclib}/uncollect`, _data);
        return res;
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
     * @memberof DocLibService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}`, _data);
            return res;
        }
        if (_context.project && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}`, _data);
            return res;
        }
        if (_context.product && _context.doclib) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}`, _data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/doclibs/${_context.doclib}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchByCustom
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchByCustom(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/fetchbycustom`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByCustom');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/fetchbycustom`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByCustom');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/fetchbycustom`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByCustom');
            return res;
        }
        const res = await this.http.post(`/doclibs/fetchbycustom`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByCustom');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchByProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchByProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/fetchbyproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProduct');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/fetchbyproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProduct');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/fetchbyproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProduct');
            return res;
        }
        const res = await this.http.post(`/doclibs/fetchbyproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProduct');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchByProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchByProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/fetchbyproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProject');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/fetchbyproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProject');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/fetchbyproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProject');
            return res;
        }
        const res = await this.http.post(`/doclibs/fetchbyproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchByProject');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        const res = await this.http.post(`/doclibs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
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
     * @memberof DocLibService
     */
    async FetchMyFavourites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        const res = await this.http.post(`/doclibs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
        return res;
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
     * @memberof DocLibServiceBase
     */
    public async CollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/collectbatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/collectbatch`,_data);
            return res;
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/collectbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/doclibs/collectbatch`,_data);
        return res;
    }

    /**
     * UnCollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibServiceBase
     */
    public async UnCollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/uncollectbatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/uncollectbatch`,_data);
            return res;
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/uncollectbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/doclibs/uncollectbatch`,_data);
        return res;
    }
}
