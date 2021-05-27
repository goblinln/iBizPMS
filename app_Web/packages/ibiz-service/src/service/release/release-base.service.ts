import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IRelease, Release } from '../../entities';
import keys from '../../entities/release/release-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 发布服务对象基类
 *
 * @export
 * @class ReleaseBaseService
 * @extends {EntityBaseService}
 */
export class ReleaseBaseService extends EntityBaseService<IRelease> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Release';
    protected APPDENAMEPLURAL = 'Releases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IRelease): Release {
        return new Release(data);
    }

    async addLocal(context: IContext, entity: IRelease): Promise<IRelease | null> {
        return this.cache.add(context, new Release(entity) as any);
    }

    async createLocal(context: IContext, entity: IRelease): Promise<IRelease | null> {
        return super.createLocal(context, new Release(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IRelease> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IRelease): Promise<IRelease> {
        return super.updateLocal(context, new Release(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IRelease = {}): Promise<IRelease> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new Release(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getGetListCond() {
        if (!this.condCache.has('getList')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'STATUS',{ type: 'DATACONTEXT', value: 'status'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('getList', cond);
            }
        }
        return this.condCache.get('getList');
    }

    protected getReportReleaseCond() {
        if (!this.condCache.has('reportRelease')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('reportRelease', cond);
            }
        }
        return this.condCache.get('reportRelease');
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
     * @memberof ReleaseService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
            return this.http.get(`/products/${_context.product}/releases/${_context.release}/select`);
        }
        return this.http.get(`/releases/${_context.release}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/releases`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/releases`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/releases/${_context.release}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/releases/${_context.release}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
            return this.http.delete(`/products/${_context.product}/releases/${_context.release}`);
        }
        return this.http.delete(`/releases/${_context.release}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
            const res = await this.http.get(`/products/${_context.product}/releases/${_context.release}`);
            return res;
        }
        const res = await this.http.get(`/releases/${_context.release}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/releases/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/releases/getdraft`, _data);
        return res;
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/activate`, _data);
        }
        return this.http.post(`/releases/${_context.release}/activate`, _data);
    }
    /**
     * BatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async BatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/batchunlinkbug`, _data);
        }
        return this.http.post(`/releases/${_context.release}/batchunlinkbug`, _data);
    }
    /**
     * ChangeStatus
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async ChangeStatus(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/changestatus`, _data);
        }
        return this.http.post(`/releases/${_context.release}/changestatus`, _data);
    }
    /**
     * LinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async LinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/linkbug`, _data);
        }
        return this.http.post(`/releases/${_context.release}/linkbug`, _data);
    }
    /**
     * LinkBugbyBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async LinkBugbyBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/linkbugbybug`, _data);
        }
        return this.http.post(`/releases/${_context.release}/linkbugbybug`, _data);
    }
    /**
     * LinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async LinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/linkbugbyleftbug`, _data);
        }
        return this.http.post(`/releases/${_context.release}/linkbugbyleftbug`, _data);
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/linkstory`, _data);
        }
        return this.http.post(`/releases/${_context.release}/linkstory`, _data);
    }
    /**
     * MobReleaseCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async MobReleaseCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/releases/${_context.release}/mobreleasecounter`, _data);
        }
        return this.http.put(`/releases/${_context.release}/mobreleasecounter`, _data);
    }
    /**
     * OneClickRelease
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async OneClickRelease(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/oneclickrelease`, _data);
        }
        return this.http.post(`/releases/${_context.release}/oneclickrelease`, _data);
    }
    /**
     * Terminate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async Terminate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/terminate`, _data);
        }
        return this.http.post(`/releases/${_context.release}/terminate`, _data);
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/unlinkbug`, _data);
        }
        return this.http.post(`/releases/${_context.release}/unlinkbug`, _data);
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/unlinkstory`, _data);
        }
        return this.http.post(`/releases/${_context.release}/unlinkstory`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/releases/fetchdefault`, _data);
        }
        return this.http.post(`/releases/fetchdefault`, _data);
    }
    /**
     * FetchReportRelease
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
     */
    async FetchReportRelease(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/releases/fetchreportrelease`, _data);
        }
        return this.http.post(`/releases/fetchreportrelease`, _data);
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/activatebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/activatebatch`,_data);
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async BatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/batchunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/batchunlinkbugbatch`,_data);
    }

    /**
     * ChangeStatusBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async ChangeStatusBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/changestatusbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/changestatusbatch`,_data);
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async LinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/linkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/linkbugbatch`,_data);
    }

    /**
     * LinkBugbyBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async LinkBugbyBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/linkbugbybugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/linkbugbybugbatch`,_data);
    }

    /**
     * LinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async LinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/linkbugbyleftbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/linkbugbyleftbugbatch`,_data);
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/linkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/linkstorybatch`,_data);
    }

    /**
     * OneClickReleaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async OneClickReleaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/oneclickreleasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/oneclickreleasebatch`,_data);
    }

    /**
     * TerminateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async TerminateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/terminatebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/terminatebatch`,_data);
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/unlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/unlinkbugbatch`,_data);
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ReleaseServiceBase
     */
    public async UnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/unlinkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/releases/unlinkstorybatch`,_data);
    }
}
