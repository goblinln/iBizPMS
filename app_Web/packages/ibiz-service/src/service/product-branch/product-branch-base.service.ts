import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductBranch, ProductBranch } from '../../entities';
import keys from '../../entities/product-branch/product-branch-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品的分支和平台信息服务对象基类
 *
 * @export
 * @class ProductBranchBaseService
 * @extends {EntityBaseService}
 */
export class ProductBranchBaseService extends EntityBaseService<IProductBranch> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductBranch';
    protected APPDENAMEPLURAL = 'ProductBranches';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IProductBranch): ProductBranch {
        return new ProductBranch(data);
    }

    async addLocal(context: IContext, entity: IProductBranch): Promise<IProductBranch | null> {
        return this.cache.add(context, new ProductBranch(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductBranch): Promise<IProductBranch | null> {
        return super.createLocal(context, new ProductBranch(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductBranch> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductBranch): Promise<IProductBranch> {
        return super.updateLocal(context, new ProductBranch(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductBranch = {}): Promise<IProductBranch> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return new ProductBranch(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getCurProductCond() {
        if (!this.condCache.has('curProduct')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'products'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curProduct', cond);
            }
        }
        return this.condCache.get('curProduct');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultBranchCond() {
        if (!this.condCache.has('defaultBranch')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('defaultBranch', cond);
            }
        }
        return this.condCache.get('defaultBranch');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productbranch) {
            return this.http.delete(`/products/${_context.product}/productbranches/${_context.productbranch}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productbranches/getdraft`, _data);
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
     * @memberof ProductBranchService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productbranch) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productbranches/${_context.productbranch}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productbranch) {
            const res = await this.http.get(`/products/${_context.product}/productbranches/${_context.productbranch}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productbranches/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
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
            return this.http.post(`/products/${_context.product}/productbranches`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchCurProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBranchService
     */
    async FetchCurProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productbranches/fetchcurproduct`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
