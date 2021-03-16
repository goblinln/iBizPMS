import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzDaily, IbzDaily } from '../../entities';
import keys from '../../entities/ibz-daily/ibz-daily-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 日报服务对象基类
 *
 * @export
 * @class IbzDailyBaseService
 * @extends {EntityBaseService}
 */
export class IbzDailyBaseService extends EntityBaseService<IIbzDaily> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzDaily';
    protected APPDENAMEPLURAL = 'IbzDailies';
    protected APPDEKEY = 'ibzdailyid';
    protected APPDETEXT = 'ibzdailyname';
    protected quickSearchFields = ['ibzdailyname',];
    protected selectContextParam = {
    };

    async addLocal(context: IContext, entity: IIbzDaily): Promise<IIbzDaily | null> {
        return this.cache.add(context, new IbzDaily(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzDaily): Promise<IIbzDaily | null> {
        return super.createLocal(context, new IbzDaily(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzDaily> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzDaily): Promise<IIbzDaily> {
        return super.updateLocal(context, new IbzDaily(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzDaily = {}): Promise<IIbzDaily> {
        return new IbzDaily(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
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

    protected getMyAllDailyCond() {
        return this.condCache.get('myAllDaily');
    }

    protected getMyDailyCond() {
        return this.condCache.get('myDaily');
    }

    protected getMyNotSubmitCond() {
        return this.condCache.get('myNotSubmit');
    }

    protected getMySubmitDailyCond() {
        return this.condCache.get('mySubmitDaily');
    }

    protected getProductDailyCond() {
        return this.condCache.get('productDaily');
    }

    protected getProjectDailyCond() {
        if (!this.condCache.has('projectDaily')) {
            const strCond: any[] = ['AND', ['EQ', 'ISSUBMIT','1']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectDaily', cond);
            }
        }
        return this.condCache.get('projectDaily');
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
     * @memberof IbzDailyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/${_context.ibzdaily}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzdailies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzdailies/${_context.ibzdaily}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzdailies/${_context.ibzdaily}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzdailies/${_context.ibzdaily}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzdailies/getdraft`, _data);
        return res;
    }
    /**
     * CreateUserDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async CreateUserDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzdailies/${_context.ibzdaily}/createuserdaily`, _data);
    }
    /**
     * GetYeaterdayDailyPlansTaskEdit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async GetYeaterdayDailyPlansTaskEdit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzdailies/${_context.ibzdaily}/getyeaterdaydailyplanstaskedit`, _data);
    }
    /**
     * GetYesterdayDailyPlansTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async GetYesterdayDailyPlansTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzdailies/${_context.ibzdaily}/getyesterdaydailyplanstask`, _data);
    }
    /**
     * HaveRead
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async HaveRead(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzdailies/${_context.ibzdaily}/haveread`, _data);
    }
    /**
     * LinkCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async LinkCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/ibzdailies/${_context.ibzdaily}/linkcompletetask`, _data);
    }
    /**
     * PushUserDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async PushUserDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzdailies/${_context.ibzdaily}/pushuserdaily`, _data);
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/ibzdailies/${_context.ibzdaily}/submit`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchdefault`, _data);
    }
    /**
     * FetchMyAllDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchMyAllDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchmyalldaily`, _data);
    }
    /**
     * FetchMyDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchMyDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchmydaily`, _data);
    }
    /**
     * FetchMyNotSubmit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchMyNotSubmit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchmynotsubmit`, _data);
    }
    /**
     * FetchMySubmitDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchMySubmitDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchmysubmitdaily`, _data);
    }
    /**
     * FetchProductDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchProductDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchproductdaily`, _data);
    }
    /**
     * FetchProjectDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzDailyService
     */
    async FetchProjectDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdailies/fetchprojectdaily`, _data);
    }
}
