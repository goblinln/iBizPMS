import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProProductLine, IBZProProductLine } from '../../entities';
import keys from '../../entities/ibzpro-product-line/ibzpro-product-line-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品线服务对象基类
 *
 * @export
 * @class IBZProProductLineBaseService
 * @extends {EntityBaseService}
 */
export class IBZProProductLineBaseService extends EntityBaseService<IIBZProProductLine> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProProductLine';
    protected APPDENAMEPLURAL = 'IBZProProductLines';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IIBZProProductLine): IBZProProductLine {
        return new IBZProProductLine(data);
    }

    async addLocal(context: IContext, entity: IIBZProProductLine): Promise<IIBZProProductLine | null> {
        return this.cache.add(context, new IBZProProductLine(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProProductLine): Promise<IIBZProProductLine | null> {
        return super.createLocal(context, new IBZProProductLine(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProProductLine> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProProductLine): Promise<IIBZProProductLine> {
        return super.updateLocal(context, new IBZProProductLine(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProProductLine = {}): Promise<IIBZProProductLine> {
        return new IBZProProductLine(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductLineService
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
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','line']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getSimpleCond() {
        if (!this.condCache.has('simple')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','line']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('simple', cond);
            }
        }
        return this.condCache.get('simple');
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
     * @memberof IBZProProductLineService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproproductlines/${_context.ibzproproductline}/select`);
    }
}
