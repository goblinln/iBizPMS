import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzTaskestimate, IbzTaskestimate } from '../../entities';
import keys from '../../entities/ibz-taskestimate/ibz-taskestimate-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务预计服务对象基类
 *
 * @export
 * @class IbzTaskestimateBaseService
 * @extends {EntityBaseService}
 */
export class IbzTaskestimateBaseService extends EntityBaseService<IIbzTaskestimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'IbzTaskestimate';
    protected APPDENAMEPLURAL = 'IbzTaskestimates';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzTaskestimate.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzTaskestimate): IbzTaskestimate {
        return new IbzTaskestimate(data);
    }

    async addLocal(context: IContext, entity: IIbzTaskestimate): Promise<IIbzTaskestimate | null> {
        return this.cache.add(context, new IbzTaskestimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzTaskestimate): Promise<IIbzTaskestimate | null> {
        return super.createLocal(context, new IbzTaskestimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzTaskestimate> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzTaskestimate): Promise<IIbzTaskestimate> {
        return super.updateLocal(context, new IbzTaskestimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzTaskestimate = {}): Promise<IIbzTaskestimate> {
        return new IbzTaskestimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/ibztaskestimates`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibztaskestimates/${_context.ibztaskestimate}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibztaskestimates/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * PMEvaluation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async PMEvaluation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/${_context.ibztaskestimate}/pmevaluation`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/ibztaskestimates/${_context.ibztaskestimate}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/ibztaskestimates/${_context.ibztaskestimate}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchactionmonth`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchActionMonth');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchactionyear`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchActionYear');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefaults
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchDefaults(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchdefaults`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefaults');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchProjectActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchprojectactionmonth`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectActionMonth');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchProjectActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchprojectactionyear`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectActionYear');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectTaskEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async FetchProjectTaskEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibztaskestimates/fetchprojecttaskestimate`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTaskEstimate');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzTaskestimateService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibztaskestimates/${_context.ibztaskestimate}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * PMEvaluationBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzTaskestimateServiceBase
     */
    public async PMEvaluationBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/ibztaskestimates/pmevaluationbatch`,_data);
        return res;
    }
}
