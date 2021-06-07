import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IMonthly, Monthly } from '../../entities';
import keys from '../../entities/monthly/monthly-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 月报服务对象基类
 *
 * @export
 * @class MonthlyBaseService
 * @extends {EntityBaseService}
 */
export class MonthlyBaseService extends EntityBaseService<IMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Monthly';
    protected APPDENAMEPLURAL = 'Monthlies';
    protected APPDEKEY = 'ibzmonthlyid';
    protected APPDETEXT = 'ibzmonthlyname';
    protected quickSearchFields = ['ibzmonthlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IMonthly): Monthly {
        return new Monthly(data);
    }

    async addLocal(context: IContext, entity: IMonthly): Promise<IMonthly | null> {
        return this.cache.add(context, new Monthly(entity) as any);
    }

    async createLocal(context: IContext, entity: IMonthly): Promise<IMonthly | null> {
        return super.createLocal(context, new Monthly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IMonthly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IMonthly): Promise<IMonthly> {
        return super.updateLocal(context, new Monthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IMonthly = {}): Promise<IMonthly> {
        return new Monthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
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

    protected getMyMonthlyCond() {
        return this.condCache.get('myMonthly');
    }

    protected getMyMonthlyMobCond() {
        return this.condCache.get('myMonthlyMob');
    }

    protected getMyReceivedMonthlyCond() {
        return this.condCache.get('myReceivedMonthly');
    }

    protected getMySubmitMonthlyCond() {
        return this.condCache.get('mySubmitMonthly');
    }

    protected getProductMonthlyCond() {
        return this.condCache.get('productMonthly');
    }

    protected getProjectMonthlyCond() {
        if (!this.condCache.has('projectMonthly')) {
            const strCond: any[] = ['AND', ['EQ', 'ISSUBMIT','1']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectMonthly', cond);
            }
        }
        return this.condCache.get('projectMonthly');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/monthlies/getdraft`, _data);
        return res;
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/monthlies`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/monthlies/${_context.monthly}`);
        return res;
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/monthlies/${_context.monthly}/submit`, _data);
    }
    /**
     * Read
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Read(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/monthlies/${_context.monthly}/read`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/monthlies/fetchdefault`, _data);
    }
    /**
     * AutoCreate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async AutoCreate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/monthlies/${_context.monthly}/autocreate`, _data);
    }
    /**
     * Notice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Notice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/monthlies/${_context.monthly}/notice`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof MonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/monthlies/${_context.monthly}`, _data);
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof MonthlyServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/monthlies/submitbatch`,_data);
    }

    /**
     * ReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof MonthlyServiceBase
     */
    public async ReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/monthlies/readbatch`,_data);
    }

    /**
     * AutoCreateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof MonthlyServiceBase
     */
    public async AutoCreateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/monthlies/autocreatebatch`,_data);
    }

    /**
     * NoticeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof MonthlyServiceBase
     */
    public async NoticeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/monthlies/noticebatch`,_data);
    }
}
