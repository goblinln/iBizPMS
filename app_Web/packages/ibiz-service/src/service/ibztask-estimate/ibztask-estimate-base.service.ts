import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZTaskEstimate, IBZTaskEstimate } from '../../entities';
import keys from '../../entities/ibztask-estimate/ibztask-estimate-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { SearchFilter } from 'ibiz-core';

/**
 * 任务预计服务对象基类
 *
 * @export
 * @class IBZTaskEstimateBaseService
 * @extends {EntityBaseService}
 */
export class IBZTaskEstimateBaseService extends EntityBaseService<IIBZTaskEstimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZTaskEstimate';
    protected APPDENAMEPLURAL = 'IBZTaskEstimates';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
        task: 'task',
    };

    newEntity(data: IIBZTaskEstimate): IBZTaskEstimate {
        return new IBZTaskEstimate(data);
    }

    async addLocal(context: IContext, entity: IIBZTaskEstimate): Promise<IIBZTaskEstimate | null> {
        return this.cache.add(context, new IBZTaskEstimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZTaskEstimate): Promise<IIBZTaskEstimate | null> {
        return super.createLocal(context, new IBZTaskEstimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZTaskEstimate> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.task && entity.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.task);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.task = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZTaskEstimate): Promise<IIBZTaskEstimate> {
        return super.updateLocal(context, new IBZTaskEstimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZTaskEstimate = {}): Promise<IIBZTaskEstimate> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.task = data;
            }
        }
        return new IBZTaskEstimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getActionMonthCond() {
        return this.condCache.get('actionMonth');
    }

    protected getActionYearCond() {
        return this.condCache.get('actionYear');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultsCond() {
        if (!this.condCache.has('defaults')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('defaults', cond);
            }
        }
        return this.condCache.get('defaults');
    }

    protected getProjectActionMonthCond() {
        return this.condCache.get('projectActionMonth');
    }

    protected getProjectActionYearCond() {
        return this.condCache.get('projectActionYear');
    }

    protected getProjectTaskEstimateCond() {
        if (!this.condCache.has('projectTaskEstimate')) {
            const strCond: any[] = ['AND', ['EQ', 'PROJECT',{ type: 'WEBCONTEXT', value: 'project'}], ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectTaskEstimate', cond);
            }
        }
        return this.condCache.get('projectTaskEstimate');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * PMEvaluation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async PMEvaluation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        throw new Error('自定义实体行为「PMEvaluation」需要重写实现');
    }
    /**
     * FetchActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getActionMonthCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getActionYearCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchDefaults
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchDefaults(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultsCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchProjectActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchProjectActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getProjectActionMonthCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchProjectActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchProjectActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getProjectActionYearCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchProjectTaskEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskEstimateService
     */
    async FetchProjectTaskEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getProjectTaskEstimateCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
}