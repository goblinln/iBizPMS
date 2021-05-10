import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBranch, Branch } from '../../entities';
import keys from '../../entities/branch/branch-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品的分支和平台信息服务对象基类
 *
 * @export
 * @class BranchBaseService
 * @extends {EntityBaseService}
 */
export class BranchBaseService extends EntityBaseService<IBranch> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Branch';
    protected APPDENAMEPLURAL = 'Branches';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IBranch): Branch {
        return new Branch(data);
    }

    async addLocal(context: IContext, entity: IBranch): Promise<IBranch | null> {
        return this.cache.add(context, new Branch(entity) as any);
    }

    async createLocal(context: IContext, entity: IBranch): Promise<IBranch | null> {
        return super.createLocal(context, new Branch(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBranch> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.product = data.productsn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBranch): Promise<IBranch> {
        return super.updateLocal(context, new Branch(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBranch = {}): Promise<IBranch> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.productsn;
            }
        }
        return new Branch(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.branch) {
            return this.http.get(`/products/${_context.product}/branches/${_context.branch}/select`);
        }
        return this.http.get(`/branches/${_context.branch}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
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
            return this.http.post(`/products/${_context.product}/branches`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/branches`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.branch) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/branches/${_context.branch}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/branches/${_context.branch}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.branch) {
            return this.http.delete(`/products/${_context.product}/branches/${_context.branch}`);
        }
        return this.http.delete(`/branches/${_context.branch}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.branch) {
            const res = await this.http.get(`/products/${_context.product}/branches/${_context.branch}`);
            return res;
        }
        const res = await this.http.get(`/branches/${_context.branch}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/branches/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/branches/getdraft`, _data);
        return res;
    }
    /**
     * Sort
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async Sort(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.branch) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/branches/${_context.branch}/sort`, _data);
        }
        return this.http.post(`/branches/${_context.branch}/sort`, _data);
    }
    /**
     * FetchCurProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async FetchCurProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/branches/fetchcurproduct`, _data);
        }
        return this.http.post(`/branches/fetchcurproduct`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/branches/fetchdefault`, _data);
        }
        return this.http.post(`/branches/fetchdefault`, _data);
    }

    /**
     * SortBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BranchServiceBase
     */
    public async SortBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/branches/sortbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/branches/sortbatch`,_data);
    }
}
