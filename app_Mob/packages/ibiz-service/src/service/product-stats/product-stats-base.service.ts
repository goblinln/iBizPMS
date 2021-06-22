import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductStats, ProductStats } from '../../entities';
import keys from '../../entities/product-stats/product-stats-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品统计服务对象基类
 *
 * @export
 * @class ProductStatsBaseService
 * @extends {EntityBaseService}
 */
export class ProductStatsBaseService extends EntityBaseService<IProductStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'ProductStats';
    protected APPDENAMEPLURAL = 'ProductStats';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IProductStats): ProductStats {
        return new ProductStats(data);
    }

    async addLocal(context: IContext, entity: IProductStats): Promise<IProductStats | null> {
        return this.cache.add(context, new ProductStats(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductStats): Promise<IProductStats | null> {
        return super.createLocal(context, new ProductStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductStats): Promise<IProductStats> {
        return super.updateLocal(context, new ProductStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductStats = {}): Promise<IProductStats> {
        return new ProductStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
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

    protected getNoOpenProductCond() {
        if (!this.condCache.has('noOpenProduct')) {
            const strCond: any[] = ['AND', ['EQ', 'STATUS','normal']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('noOpenProduct', cond);
            }
        }
        return this.condCache.get('noOpenProduct');
    }

    protected getProdctQuantiGirdCond() {
        return this.condCache.get('prodctQuantiGird');
    }

    protected getProductInputTableCond() {
        return this.condCache.get('productInputTable');
    }

    protected getProductcompletionstatisticsCond() {
        return this.condCache.get('productcompletionstatistics');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productstats`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/productstats/${_context.productstats}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productstats/getdraft`, _data);
        return res;
    }
    /**
     * GetTestStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async GetTestStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/productstats/${_context.productstats}/getteststats`);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/productstats/${_context.productstats}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productstats/${_context.productstats}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productstats/fetchdefault`, _data);
    }
    /**
     * FetchNoOpenProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async FetchNoOpenProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productstats/fetchnoopenproduct`, _data);
    }
    /**
     * FetchProdctQuantiGird
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async FetchProdctQuantiGird(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productstats/fetchprodctquantigird`, _data);
    }
    /**
     * FetchProductInputTable
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async FetchProductInputTable(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productstats/fetchproductinputtable`, _data);
    }
    /**
     * FetchProductcompletionstatistics
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async FetchProductcompletionstatistics(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productstats/fetchproductcompletionstatistics`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productstats/${_context.productstats}/select`);
    }
}
