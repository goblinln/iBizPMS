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
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductLife): Promise<IProductLife> {
        return super.updateLocal(context, new ProductLife(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductLife = {}): Promise<IProductLife> {
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
     * FetchGetRoadmapS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async FetchGetRoadmapS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productlives/fetchgetroadmaps`, _data);
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
        return this.http.get(`/productlives/fetchroadmapyear`, _data);
    }
    /**
     * FetchGetRoadmap
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async FetchGetRoadmap(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productlives/fetchgetroadmap`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLifeService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productlives/${_context.productlife}/select`);
    }
}
