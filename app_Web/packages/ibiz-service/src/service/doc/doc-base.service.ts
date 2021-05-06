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
    protected APPNAME = 'Web';
    protected APPDENAME = 'Doc';
    protected APPDENAMEPLURAL = 'Docs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
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
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDoc): Promise<IDoc> {
        return super.updateLocal(context, new Doc(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDoc = {}): Promise<IDoc> {
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
    public async ByVersionUpdateContextBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/byversionupdatecontextbatch`,tempData,isloading);
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
    public async CollectBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/collectbatch`,tempData,isloading);
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
    public async GetDocStatusBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/getdocstatusbatch`,tempData,isloading);
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
    public async OnlyCollectDocBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/onlycollectdocbatch`,tempData,isloading);
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
    public async OnlyUnCollectDocBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/onlyuncollectdocbatch`,tempData,isloading);
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
    public async UnCollectBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/docs/uncollectbatch`,tempData,isloading);
    }
}
