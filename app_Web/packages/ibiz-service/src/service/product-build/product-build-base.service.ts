import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductBuild, ProductBuild } from '../../entities';
import keys from '../../entities/product-build/product-build-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 版本服务对象基类
 *
 * @export
 * @class ProductBuildBaseService
 * @extends {EntityBaseService}
 */
export class ProductBuildBaseService extends EntityBaseService<IProductBuild> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductBuild';
    protected APPDENAMEPLURAL = 'ProductBuilds';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IProductBuild): ProductBuild {
        return new ProductBuild(data);
    }

    async addLocal(context: IContext, entity: IProductBuild): Promise<IProductBuild | null> {
        return this.cache.add(context, new ProductBuild(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductBuild): Promise<IProductBuild | null> {
        return super.createLocal(context, new ProductBuild(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductBuild> {
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

    async updateLocal(context: IContext, entity: IProductBuild): Promise<IProductBuild> {
        return super.updateLocal(context, new ProductBuild(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductBuild = {}): Promise<IProductBuild> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new ProductBuild(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBuildService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugProductBuildCond() {
        return this.condCache.get('bugProductBuild');
    }

    protected getBugProductOrProjectBuildCond() {
        return this.condCache.get('bugProductOrProjectBuild');
    }

    protected getCurProductCond() {
        if (!this.condCache.has('curProduct')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}]]];
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

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getTestBuildCond() {
        return this.condCache.get('testBuild');
    }

    protected getTestRoundsCond() {
        if (!this.condCache.has('testRounds')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('testRounds', cond);
            }
        }
        return this.condCache.get('testRounds');
    }

    protected getUpdateLogCond() {
        return this.condCache.get('updateLog');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductBuildService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productbuilds/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
