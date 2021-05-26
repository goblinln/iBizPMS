import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProProductHistory, IBZProProductHistory } from '../../entities';
import keys from '../../entities/ibzpro-product-history/ibzpro-product-history-keys';

/**
 * 产品操作历史服务对象基类
 *
 * @export
 * @class IBZProProductHistoryBaseService
 * @extends {EntityBaseService}
 */
export class IBZProProductHistoryBaseService extends EntityBaseService<IIBZProProductHistory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProProductHistory';
    protected APPDENAMEPLURAL = 'IBZProProductHistories';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'diff';
    protected quickSearchFields = ['diff',];
    protected selectContextParam = {
    };

    newEntity(data: IIBZProProductHistory): IBZProProductHistory {
        return new IBZProProductHistory(data);
    }

    async addLocal(context: IContext, entity: IIBZProProductHistory): Promise<IIBZProProductHistory | null> {
        return this.cache.add(context, new IBZProProductHistory(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProProductHistory): Promise<IIBZProProductHistory | null> {
        return super.createLocal(context, new IBZProProductHistory(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProProductHistory> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProProductHistory): Promise<IIBZProProductHistory> {
        return super.updateLocal(context, new IBZProProductHistory(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProProductHistory = {}): Promise<IIBZProProductHistory> {
        return new IBZProProductHistory(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductHistoryService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductHistoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproproducthistories/${_context.ibzproproducthistory}/select`);
    }
}
