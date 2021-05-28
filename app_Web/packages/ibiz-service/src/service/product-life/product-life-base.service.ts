import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductLife, ProductLife } from '../../entities';
import keys from '../../entities/product-life/product-life-keys';

/**
 * 产品生命周期服务对象基类
 *
 * @export
 * @class ProductLifeBaseService
 * @extends {EntityBaseService}
 */
export class ProductLifeBaseService extends EntityBaseService<IProductLife> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductLife';
    protected APPDENAMEPLURAL = 'ProductLives';
    protected APPDEKEY = 'productlifeid';
    protected APPDETEXT = 'productlifename';
    protected quickSearchFields = ['productlifename',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IProductLife): ProductLife {
        return new ProductLife(data);
    }

    async addLocal(context: IContext, entity: IProductLife): Promise<IProductLife | null> {
        return this.cache.add(context, new ProductLife(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductLife): Promise<IProductLife | null> {
        return super.createLocal(context, new ProductLife(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductLife> {
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

    async updateLocal(context: IContext, entity: IProductLife): Promise<IProductLife> {
        return super.updateLocal(context, new ProductLife(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductLife = {}): Promise<IProductLife> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return new ProductLife(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * FetchRoadmap
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async FetchRoadmap(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productlives/fetchroadmap`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchRoadMapYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async FetchRoadMapYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productlives/fetchroadmapyear`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
