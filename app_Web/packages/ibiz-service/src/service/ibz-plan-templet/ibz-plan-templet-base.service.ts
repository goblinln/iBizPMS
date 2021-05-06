import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzPlanTemplet, IbzPlanTemplet } from '../../entities';
import keys from '../../entities/ibz-plan-templet/ibz-plan-templet-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 计划模板服务对象基类
 *
 * @export
 * @class IbzPlanTempletBaseService
 * @extends {EntityBaseService}
 */
export class IbzPlanTempletBaseService extends EntityBaseService<IIbzPlanTemplet> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzPlanTemplet';
    protected APPDENAMEPLURAL = 'IbzPlanTemplets';
    protected APPDEKEY = 'ibzplantempletid';
    protected APPDETEXT = 'ibzplantempletname';
    protected quickSearchFields = ['ibzplantempletname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzPlanTemplet): IbzPlanTemplet {
        return new IbzPlanTemplet(data);
    }

    async addLocal(context: IContext, entity: IIbzPlanTemplet): Promise<IIbzPlanTemplet | null> {
        return this.cache.add(context, new IbzPlanTemplet(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzPlanTemplet): Promise<IIbzPlanTemplet | null> {
        return super.createLocal(context, new IbzPlanTemplet(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzPlanTemplet> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzPlanTemplet): Promise<IIbzPlanTemplet> {
        return super.updateLocal(context, new IbzPlanTemplet(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzPlanTemplet = {}): Promise<IIbzPlanTemplet> {
        return new IbzPlanTemplet(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getCurUserTempletCond() {
        if (!this.condCache.has('curUserTemplet')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ACL','open'], ['EQ', 'CREATEMANNAME',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curUserTemplet', cond);
            }
        }
        return this.condCache.get('curUserTemplet');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
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
     * @memberof IbzPlanTempletService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzplantemplets/${_context.ibzplantemplet}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzplantemplets`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzplantemplets/${_context.ibzplantemplet}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzplantemplets/${_context.ibzplantemplet}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzplantemplets/${_context.ibzplantemplet}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzplantemplets/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * GetPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async GetPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzplantemplets/${_context.ibzplantemplet}/getplan`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * FetchCurUserTemplet
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async FetchCurUserTemplet(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzplantemplets/fetchcurusertemplet`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzplantemplets/fetchdefault`, _data);
    }

    /**
     * GetPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async GetPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzplantemplets/getplanbatch`,tempData,isloading);
    }
}
