import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductRelease, ProductRelease } from '../../entities';
import keys from '../../entities/product-release/product-release-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 发布服务对象基类
 *
 * @export
 * @class ProductReleaseBaseService
 * @extends {EntityBaseService}
 */
export class ProductReleaseBaseService extends EntityBaseService<IProductRelease> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductRelease';
    protected APPDENAMEPLURAL = 'ProductReleases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IProductRelease): ProductRelease {
        return new ProductRelease(data);
    }

    async addLocal(context: IContext, entity: IProductRelease): Promise<IProductRelease | null> {
        return this.cache.add(context, new ProductRelease(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductRelease): Promise<IProductRelease | null> {
        return super.createLocal(context, new ProductRelease(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductRelease> {
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

    async updateLocal(context: IContext, entity: IProductRelease): Promise<IProductRelease> {
        return super.updateLocal(context, new ProductRelease(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductRelease = {}): Promise<IProductRelease> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new ProductRelease(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
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
     * Terminate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async Terminate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/terminate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/unlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/unlinkstory`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * LinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async LinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/linkbugbyleftbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
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
            return this.http.post(`/products/${_context.product}/productreleases`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productreleases/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/activate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productreleases/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productreleases/${_context.productrelease}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/linkstory`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
            return this.http.delete(`/products/${_context.product}/productreleases/${_context.productrelease}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductReleaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productrelease) {
            const res = await this.http.get(`/products/${_context.product}/productreleases/${_context.productrelease}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * TerminateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async TerminateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/terminatebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/unlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async UnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/unlinkstorybatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * LinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async LinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/linkbugbyleftbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/activatebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductReleaseServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productreleases/linkstorybatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
