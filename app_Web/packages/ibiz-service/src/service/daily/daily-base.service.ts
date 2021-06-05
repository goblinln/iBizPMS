import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDaily, Daily } from '../../entities';
import keys from '../../entities/daily/daily-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 日报服务对象基类
 *
 * @export
 * @class DailyBaseService
 * @extends {EntityBaseService}
 */
export class DailyBaseService extends EntityBaseService<IDaily> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Daily';
    protected APPDENAMEPLURAL = 'Dailies';
    protected APPDEKEY = 'ibzdailyid';
    protected APPDETEXT = 'ibzdailyname';
    protected quickSearchFields = ['ibzdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IDaily): Daily {
        return new Daily(data);
    }

    async addLocal(context: IContext, entity: IDaily): Promise<IDaily | null> {
        return this.cache.add(context, new Daily(entity) as any);
    }

    async createLocal(context: IContext, entity: IDaily): Promise<IDaily | null> {
        return super.createLocal(context, new Daily(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDaily> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDaily): Promise<IDaily> {
        return super.updateLocal(context, new Daily(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDaily = {}): Promise<IDaily> {
        return new Daily(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
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
     * Notice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Notice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/dailies/${_context.daily}/notice`, _data);
    }
    /**
     * Read
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Read(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/dailies/${_context.daily}/read`, _data);
    }
    /**
     * AutoCreate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async AutoCreate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/dailies/${_context.daily}/autocreate`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/dailies/${_context.daily}`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/dailies/${_context.daily}`);
        return res;
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/dailies/${_context.daily}/submit`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/dailies/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/dailies/fetchdefault`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DailyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/dailies`, _data);
    }

    /**
     * NoticeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DailyServiceBase
     */
    public async NoticeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/dailies/noticebatch`,_data);
    }

    /**
     * ReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DailyServiceBase
     */
    public async ReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/dailies/readbatch`,_data);
    }

    /**
     * AutoCreateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DailyServiceBase
     */
    public async AutoCreateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/dailies/autocreatebatch`,_data);
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DailyServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/dailies/submitbatch`,_data);
    }
}
