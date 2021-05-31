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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && _context.doc) {
            return this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/select`);
        }
        if (_context.product && _context.doclib && _context.doc) {
            return this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/select`);
        }
        if (_context.doclib && _context.doc) {
            return this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/select`);
        }
        return this.http.get(`/docs/${_context.doc}/select`);
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
        if (_context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs`, _data);
        }
        if (_context.product && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs`, _data);
        }
        if (_context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/doclibs/${_context.doclib}/docs`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/docs`, _data);
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
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/docs/${_context.doc}`, _data);
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
        if (_context.project && _context.doclib && _context.doc) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
        }
        if (_context.product && _context.doclib && _context.doc) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`);
        }
        if (_context.doclib && _context.doc) {
            return this.http.delete(`/doclibs/${_context.doclib}/docs/${_context.doc}`);
        }
        return this.http.delete(`/docs/${_context.doc}`);
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
        if (_context.project && _context.doclib && _context.doc) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        if (_context.doclib && _context.doc) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}`);
            return res;
        }
        const res = await this.http.get(`/docs/${_context.doc}`);
        return res;
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
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/docs/getdraft`, _data);
        return res;
    }
    /**
     * ByVersionUpdateContext
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async ByVersionUpdateContext(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/byversionupdatecontext`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/byversionupdatecontext`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}/byversionupdatecontext`, _data);
        }
        return this.http.put(`/docs/${_context.doc}/byversionupdatecontext`, _data);
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
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/collect`, _data);
        }
        return this.http.post(`/docs/${_context.doc}/collect`, _data);
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
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}/getdocstatus`, _data);
        }
        return this.http.put(`/docs/${_context.doc}/getdocstatus`, _data);
    }
    /**
     * OnlyCollectDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async OnlyCollectDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/onlycollectdoc`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/onlycollectdoc`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/onlycollectdoc`, _data);
        }
        return this.http.post(`/docs/${_context.doc}/onlycollectdoc`, _data);
    }
    /**
     * OnlyUnCollectDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async OnlyUnCollectDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/onlyuncollectdoc`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/onlyuncollectdoc`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/onlyuncollectdoc`, _data);
        }
        return this.http.post(`/docs/${_context.doc}/onlyuncollectdoc`, _data);
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
        if (_context.project && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
        }
        if (_context.product && _context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
        }
        if (_context.doclib && _context.doc) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/uncollect`, _data);
        }
        return this.http.post(`/docs/${_context.doc}/uncollect`, _data);
    }
    /**
     * FetchChildDocLibDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchChildDocLibDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchchilddoclibdoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchchilddoclibdoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchchilddoclibdoc`, _data);
        }
        return this.http.post(`/docs/fetchchilddoclibdoc`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchdefault`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchdefault`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchdefault`, _data);
        }
        return this.http.post(`/docs/fetchdefault`, _data);
    }
    /**
     * FetchDocLibAndDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchDocLibAndDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchdoclibanddoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchdoclibanddoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchdoclibanddoc`, _data);
        }
        return this.http.post(`/docs/fetchdoclibanddoc`, _data);
    }
    /**
     * FetchDocLibDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchDocLibDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchdoclibdoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchdoclibdoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchdoclibdoc`, _data);
        }
        return this.http.post(`/docs/fetchdoclibdoc`, _data);
    }
    /**
     * FetchDocModuleDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchDocModuleDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchdocmoduledoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchdocmoduledoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchdocmoduledoc`, _data);
        }
        return this.http.post(`/docs/fetchdocmoduledoc`, _data);
    }
    /**
     * FetchDocStatus
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchDocStatus(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchdocstatus`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchdocstatus`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchdocstatus`, _data);
        }
        return this.http.post(`/docs/fetchdocstatus`, _data);
    }
    /**
     * FetchModuleDocChild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchModuleDocChild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmoduledocchild`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchmoduledocchild`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchmoduledocchild`, _data);
        }
        return this.http.post(`/docs/fetchmoduledocchild`, _data);
    }
    /**
     * FetchMyFavourite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchMyFavourite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmyfavourite`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchmyfavourite`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchmyfavourite`, _data);
        }
        return this.http.post(`/docs/fetchmyfavourite`, _data);
    }
    /**
     * FetchMyFavouritesOnlyDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchMyFavouritesOnlyDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchmyfavouritesonlydoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchmyfavouritesonlydoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchmyfavouritesonlydoc`, _data);
        }
        return this.http.post(`/docs/fetchmyfavouritesonlydoc`, _data);
    }
    /**
     * FetchNotRootDoc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocService
     */
    async FetchNotRootDoc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchnotrootdoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchnotrootdoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchnotrootdoc`, _data);
        }
        return this.http.post(`/docs/fetchnotrootdoc`, _data);
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
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/fetchrootdoc`, _data);
        }
        return this.http.post(`/docs/fetchrootdoc`, _data);
    }

    /**
     * ByVersionUpdateContextBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocServiceBase
     */
    public async ByVersionUpdateContextBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/byversionupdatecontextbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/byversionupdatecontextbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/byversionupdatecontextbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/byversionupdatecontextbatch`,_data);
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
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/collectbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/collectbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/collectbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/collectbatch`,_data);
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
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/getdocstatusbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/getdocstatusbatch`,_data);
    }

    /**
     * OnlyCollectDocBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocServiceBase
     */
    public async OnlyCollectDocBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/onlycollectdocbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/onlycollectdocbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/onlycollectdocbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/onlycollectdocbatch`,_data);
    }

    /**
     * OnlyUnCollectDocBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocServiceBase
     */
    public async OnlyUnCollectDocBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/onlyuncollectdocbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/onlyuncollectdocbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/onlyuncollectdocbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/onlyuncollectdocbatch`,_data);
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
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/uncollectbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/docs/uncollectbatch`,_data);
    }
}
