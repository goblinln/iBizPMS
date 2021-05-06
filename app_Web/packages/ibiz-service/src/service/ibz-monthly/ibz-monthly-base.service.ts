import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzMonthly, IbzMonthly } from '../../entities';
import keys from '../../entities/ibz-monthly/ibz-monthly-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 月报服务对象基类
 *
 * @export
 * @class IbzMonthlyBaseService
 * @extends {EntityBaseService}
 */
export class IbzMonthlyBaseService extends EntityBaseService<IIbzMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzMonthly';
    protected APPDENAMEPLURAL = 'IbzMonthlies';
    protected APPDEKEY = 'ibzmonthlyid';
    protected APPDETEXT = 'ibzmonthlyname';
    protected quickSearchFields = ['ibzmonthlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzMonthly): IbzMonthly {
        return new IbzMonthly(data);
    }

    async addLocal(context: IContext, entity: IIbzMonthly): Promise<IIbzMonthly | null> {
        return this.cache.add(context, new IbzMonthly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzMonthly): Promise<IIbzMonthly | null> {
        return super.createLocal(context, new IbzMonthly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzMonthly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzMonthly): Promise<IIbzMonthly> {
        return super.updateLocal(context, new IbzMonthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzMonthly = {}): Promise<IIbzMonthly> {
        return new IbzMonthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzmonthlies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzmonthlies/${_context.ibzmonthly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzmonthlies/${_context.ibzmonthly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzmonthlies/${_context.ibzmonthly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzmonthlies/getdraft`, _data);
        return res;
    }
    /**
     * CreateGetInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async CreateGetInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/creategetinfo`, _data);
    }
    /**
     * CreateUserMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async CreateUserMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/createusermonthly`, _data);
    }
    /**
     * EditGetCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async EditGetCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/editgetcompletetask`, _data);
    }
    /**
     * HaveRead
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async HaveRead(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/haveread`, _data);
    }
    /**
     * PushUserMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async PushUserMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/pushusermonthly`, _data);
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/ibzmonthlies/${_context.ibzmonthly}/submit`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchdefault`, _data);
    }
    /**
     * FetchMyMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchMyMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchmymonthly`, _data);
    }
    /**
     * FetchMyMonthlyMob
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchMyMonthlyMob(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchmymonthlymob`, _data);
    }
    /**
     * FetchMyReceivedMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchMyReceivedMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchmyreceivedmonthly`, _data);
    }
    /**
     * FetchMySubmitMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchMySubmitMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchmysubmitmonthly`, _data);
    }
    /**
     * FetchProductMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchProductMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchproductmonthly`, _data);
    }
    /**
     * FetchProjectMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async FetchProjectMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmonthlies/fetchprojectmonthly`, _data);
    }

    /**
     * CreateGetInfoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async CreateGetInfoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/creategetinfobatch`,_data);
    }

    /**
     * CreateUserMonthlyBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async CreateUserMonthlyBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/createusermonthlybatch`,_data);
    }

    /**
     * EditGetCompleteTaskBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async EditGetCompleteTaskBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/editgetcompletetaskbatch`,_data);
    }

    /**
     * HaveReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async HaveReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/havereadbatch`,_data);
    }

    /**
     * PushUserMonthlyBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async PushUserMonthlyBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/pushusermonthlybatch`,_data);
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzMonthlyServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzmonthlies/submitbatch`,_data);
    }
}
