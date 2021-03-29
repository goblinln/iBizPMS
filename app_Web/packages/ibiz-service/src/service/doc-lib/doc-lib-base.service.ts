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
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IDocLib): DocLib {
        return new DocLib(data);
    }

    async addLocal(context: IContext, entity: IDocLib): Promise<IDocLib | null> {
        return this.cache.add(context, new DocLib(entity) as any);
    }

    async createLocal(context: IContext, entity: IDocLib): Promise<IDocLib | null> {
        return super.createLocal(context, new DocLib(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDocLib> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDocLib): Promise<IDocLib> {
        return super.updateLocal(context, new DocLib(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDocLib = {}): Promise<IDocLib> {
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

    protected getMyFavouritesCond() {
        return this.condCache.get('myFavourites');
    }

    protected getRootModuleMuLuCond() {
        return this.condCache.get('rootModuleMuLu');
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
     * @memberof DocLibService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibs/${_context.doclib}/select`);
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
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/doclibs`, _data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/doclibs/${_context.doclib}`, _data);
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
        return this.http.delete(`/doclibs/${_context.doclib}`);
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
        const res = await this.http.get(`/doclibs/${_context.doclib}`);
        return res;
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
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/doclibs/getdraft`, _data);
        return res;
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
        return this.http.post(`/doclibs/${_context.doclib}/collect`, _data);
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
        return this.http.post(`/doclibs/${_context.doclib}/uncollect`, _data);
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
        return this.http.get(`/doclibs/fetchbycustom`, _data);
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
        return this.http.post(`/doclibs/fetchbyproduct`, _data);
    }
    /**
     * FetchByProductNotFiles
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchByProductNotFiles(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibs/fetchbyproductnotfiles`, _data);
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
        return this.http.post(`/doclibs/fetchbyproject`, _data);
    }
    /**
     * FetchByProjectNotFiles
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchByProjectNotFiles(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibs/fetchbyprojectnotfiles`, _data);
    }
    /**
     * FetchCurDocLib
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchCurDocLib(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibs/fetchcurdoclib`, _data);
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
        return this.http.get(`/doclibs/fetchdefault`, _data);
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
        return this.http.get(`/doclibs/fetchmyfavourites`, _data);
    }
    /**
     * FetchRootModuleMuLu
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocLibService
     */
    async FetchRootModuleMuLu(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/doclibs/fetchrootmodulemulu`, _data);
    }
}
