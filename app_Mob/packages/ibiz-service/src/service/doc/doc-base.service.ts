import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDoc, Doc } from '../../entities';
import keys from '../../entities/doc/doc-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 文档服务对象基类
 *
 * @export
 * @class DocBaseService
 * @extends {EntityBaseService}
 */
export class DocBaseService extends EntityBaseService<IDoc> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Doc';
    protected APPDENAMEPLURAL = 'Docs';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/Doc.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        doclib: 'lib',
    };

    newEntity(data: IDoc): Doc {
        return new Doc(data);
    }

    async addLocal(context: IContext, entity: IDoc): Promise<IDoc | null> {
        return this.cache.add(context, new Doc(entity) as any);
    }

    async createLocal(context: IContext, entity: IDoc): Promise<IDoc | null> {
        return super.createLocal(context, new Doc(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDoc> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.lib && entity.lib !== '') {
            const s = await ___ibz___.gs.getDocLibService();
            const data = await s.getLocal2(context, entity.lib);
            if (data) {
                entity.libname = data.name;
                entity.lib = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDoc): Promise<IDoc> {
        return super.updateLocal(context, new Doc(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDoc = {}): Promise<IDoc> {
        if (_context.doclib && _context.doclib !== '') {
            const s = await ___ibz___.gs.getDocLibService();
            const data = await s.getLocal2(_context, _context.doclib);
            if (data) {
                entity.libname = data.name;
                entity.lib = data.id;
            }
        }
        return new Doc(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getChildDocLibDocCond() {
        return this.condCache.get('childDocLibDoc');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDocLibAndDocCond() {
        return this.condCache.get('docLibAndDoc');
    }

    protected getDocLibDocCond() {
        return this.condCache.get('docLibDoc');
    }

    protected getDocModuleDocCond() {
        return this.condCache.get('docModuleDoc');
    }

    protected getDocStatusCond() {
        return this.condCache.get('docStatus');
    }

    protected getLastedModifyCond() {
        return this.condCache.get('lastedModify');
    }

    protected getModuleDocChildCond() {
        if (!this.condCache.has('moduleDocChild')) {
            const strCond: any[] = ['AND', ['EQ', 'MODULE',{ type: 'WEBCONTEXT', value: 'srfparentkey'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleDocChild', cond);
            }
        }
        return this.condCache.get('moduleDocChild');
    }

    protected getMyCreateOrUpdateDocCond() {
        if (!this.condCache.has('myCreateOrUpdateDoc')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ADDEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginnane'}], ['EQ', 'EDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginnane'}], ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginnane'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrUpdateDoc', cond);
            }
        }
        return this.condCache.get('myCreateOrUpdateDoc');
    }

    protected getMyFavouriteCond() {
        return this.condCache.get('myFavourite');
    }

    protected getMyFavouritesOnlyDocCond() {
        return this.condCache.get('myFavouritesOnlyDoc');
    }

    protected getNotRootDocCond() {
        return this.condCache.get('notRootDoc');
    }

    protected getRootDocCond() {
        return this.condCache.get('rootDoc');
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
     * @memberof DocService
     */
    async Collect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
            return res;
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Collect');
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/collect`, _data);
            return res;
        }
        const res = await this.http.post(`/docs/${_context.doc}/collect`, _data);
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
     * @memberof DocService
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
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs`, _data);
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
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs`, _data);
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
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs`, _data);
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
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs`, _data);
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
        const res = await this.http.post(`/docs`, _data);
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
     * @memberof DocService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.doclib && _context.doc) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.sysaccount && _context.doc) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        const res = await this.http.get(`/docs/${_context.doc}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDocStatus
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async GetDocStatus(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'GetDocStatus');
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'GetDocStatus');
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'GetDocStatus');
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
            return res;
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'GetDocStatus');
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'GetDocStatus');
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/getdocstatus`, _data);
            return res;
        }
        const res = await this.http.get(`/docs/${_context.doc}/getdocstatus`, _data);
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
     * @memberof DocService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/getdraft`, _data);
            return res;
        }
        if (_context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/docs/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/docs/getdraft`, _data);
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
     * @memberof DocService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
            const res = await this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
            const res = await this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.doclib && _context.doc) {
            const res = await this.http.delete(`/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.sysaccount && _context.doc) {
            const res = await this.http.delete(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}`);
            return res;
        }
        const res = await this.http.delete(`/docs/${_context.doc}`);
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
     * @memberof DocService
     */
    async UnCollect(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
            return res;
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnCollect');
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/uncollect`, _data);
            return res;
        }
        const res = await this.http.post(`/docs/${_context.doc}/uncollect`, _data);
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
     * @memberof DocService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
            return res;
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}`, _data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/docs/${_context.doc}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchLastedModify
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchLastedModify(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
            return res;
        }
        const res = await this.http.post(`/docs/fetchlastedmodify`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLastedModify');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        const res = await this.http.post(`/docs/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
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
     * @memberof DocService
     */
    async FetchMyFavourites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
            return res;
        }
        const res = await this.http.post(`/docs/fetchmyfavourites`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyFavourites');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchRootDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchRootDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
            return res;
        }
        const res = await this.http.post(`/docs/fetchrootdoc`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchRootDoc');
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
     * @memberof DocServiceBase
     */
    public async CollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/collectbatch`,_data);
            return res;
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/collectbatch`,_data);
            return res;
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/collectbatch`,_data);
            return res;
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/collectbatch`,_data);
            return res;
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/collectbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/docs/collectbatch`,_data);
        return res;
    }

    /**
     * GetDocStatusBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocServiceBase
     */
    public async GetDocStatusBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
            return res;
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
            return res;
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
            return res;
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
            return res;
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/getdocstatusbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/docs/getdocstatusbatch`,_data);
        return res;
    }

    /**
     * UnCollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocServiceBase
     */
    public async UnCollectBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
            return res;
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
            return res;
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
            return res;
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
            return res;
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/uncollectbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/docs/uncollectbatch`,_data);
        return res;
    }
}
