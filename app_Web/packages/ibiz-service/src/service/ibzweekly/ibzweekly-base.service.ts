import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZWEEKLY, IBZWEEKLY } from '../../entities';
import keys from '../../entities/ibzweekly/ibzweekly-keys';

/**
 * 周报服务对象基类
 *
 * @export
 * @class IBZWEEKLYBaseService
 * @extends {EntityBaseService}
 */
export class IBZWEEKLYBaseService extends EntityBaseService<IIBZWEEKLY> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZWEEKLY';
    protected APPDENAMEPLURAL = 'IBZWEEKLies';
    protected APPDEKEY = 'ibzweeklyid';
    protected APPDETEXT = 'ibzweeklyname';
    protected quickSearchFields = ['ibzweeklyname',];
    protected selectContextParam = {
    };

    async addLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY | null> {
        return this.cache.add(context, new IBZWEEKLY(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY | null> {
        return super.createLocal(context, new IBZWEEKLY(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZWEEKLY> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY> {
        return super.updateLocal(context, new IBZWEEKLY(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZWEEKLY = {}): Promise<IIBZWEEKLY> {
        return new IBZWEEKLY(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
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
     * @memberof IBZWEEKLYService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/${_context.ibzweekly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzweeklies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzweeklies/${_context.ibzweekly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzweeklies/${_context.ibzweekly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzweeklies/${_context.ibzweekly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzweeklies/getdraft`, _data);
        return res;
    }
    /**
     * CreateEveryWeekReport
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async CreateEveryWeekReport(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/createeveryweekreport`, _data);
    }
    /**
     * CreateGetLastWeekPlanAndWork
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async CreateGetLastWeekPlanAndWork(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/creategetlastweekplanandwork`, _data);
    }
    /**
     * EditGetLastWeekTaskAndComTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async EditGetLastWeekTaskAndComTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/editgetlastweektaskandcomtask`, _data);
    }
    /**
     * HaveRead
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async HaveRead(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/haveread`, _data);
    }
    /**
     * JugThisWeekCreateWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async JugThisWeekCreateWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/jugthisweekcreateweekly`, _data);
    }
    /**
     * PushUserWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async PushUserWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/pushuserweekly`, _data);
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/submit`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/fetchdefault`, _data);
    }
    /**
     * FetchMyNotSubmit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchMyNotSubmit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/fetchmynotsubmit`, _data);
    }
    /**
     * FetchMyWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchMyWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/fetchmyweekly`, _data);
    }
    /**
     * FetchProductTeamMemberWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchProductTeamMemberWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/fetchproductteammemberweekly`, _data);
    }
    /**
     * FetchProjectWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchProjectWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzweeklies/fetchprojectweekly`, _data);
    }
}
