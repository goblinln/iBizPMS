import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductLine, ProductLine } from '../../entities';
import keys from '../../entities/product-line/product-line-keys';

/**
 * 产品线（废弃）服务对象基类
 *
 * @export
 * @class ProductLineBaseService
 * @extends {EntityBaseService}
 */
export class ProductLineBaseService extends EntityBaseService<IProductLine> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'ProductLine';
    protected APPDENAMEPLURAL = 'ProductLines';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductLine.json';
    protected APPDEKEY = 'productlineid';
    protected APPDETEXT = 'productlinename';
    protected quickSearchFields = ['productlinename',];
    protected selectContextParam = {
    };

    newEntity(data: IProductLine): ProductLine {
        return new ProductLine(data);
    }

    async addLocal(context: IContext, entity: IProductLine): Promise<IProductLine | null> {
        return this.cache.add(context, new ProductLine(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductLine): Promise<IProductLine | null> {
        return super.createLocal(context, new ProductLine(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductLine> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductLine): Promise<IProductLine> {
        return super.updateLocal(context, new ProductLine(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductLine = {}): Promise<IProductLine> {
        return new ProductLine(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductLineService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
}
