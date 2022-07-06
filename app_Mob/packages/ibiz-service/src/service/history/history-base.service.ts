import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IHistory, History } from '../../entities';
import keys from '../../entities/history/history-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 操作历史服务对象基类
 *
 * @export
 * @class HistoryBaseService
 * @extends {EntityBaseService}
 */
export class HistoryBaseService extends EntityBaseService<IHistory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'History';
    protected APPDENAMEPLURAL = 'Histories';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/History.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'diff';
    protected quickSearchFields = ['diff',];
    protected selectContextParam = {
    };

    newEntity(data: IHistory): History {
        return new History(data);
    }

    async addLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return this.cache.add(context, new History(entity) as any);
    }

    async createLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return super.createLocal(context, new History(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IHistory> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IHistory): Promise<IHistory> {
        return super.updateLocal(context, new History(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IHistory = {}): Promise<IHistory> {
        return new History(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
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
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
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
     * @memberof HistoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
