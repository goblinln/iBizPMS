import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzWeekly, IbzWeekly } from '../../entities';
import keys from '../../entities/ibz-weekly/ibz-weekly-keys';

/**
 * 周报服务对象基类
 *
 * @export
 * @class IbzWeeklyBaseService
 * @extends {EntityBaseService}
 */
export class IbzWeeklyBaseService extends EntityBaseService<IIbzWeekly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'IbzWeekly';
    protected APPDENAMEPLURAL = 'IbzWeeklies';
    protected APPDEKEY = 'ibzweeklyid';
    protected APPDETEXT = 'ibzweeklyname';
    protected quickSearchFields = ['ibzweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzWeekly): IbzWeekly {
        return new IbzWeekly(data);
    }

    async addLocal(context: IContext, entity: IIbzWeekly): Promise<IIbzWeekly | null> {
        return this.cache.add(context, new IbzWeekly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzWeekly): Promise<IIbzWeekly | null> {
        return super.createLocal(context, new IbzWeekly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzWeekly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzWeekly): Promise<IIbzWeekly> {
        return super.updateLocal(context, new IbzWeekly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzWeekly = {}): Promise<IIbzWeekly> {
        return new IbzWeekly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
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
     * @memberof IbzWeeklyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchdefault`, _data);
    }
    /**
     * FetchMyNotSubmit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzWeeklyService
     */
    async FetchMyNotSubmit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchmynotsubmit`, _data);
    }
    /**
     * FetchMyWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzWeeklyService
     */
    async FetchMyWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchmyweekly`, _data);
    }
    /**
     * FetchProductTeamMemberWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzWeeklyService
     */
    async FetchProductTeamMemberWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchproductteammemberweekly`, _data);
    }
    /**
     * FetchProjectWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzWeeklyService
     */
    async FetchProjectWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchprojectweekly`, _data);
    }
}
