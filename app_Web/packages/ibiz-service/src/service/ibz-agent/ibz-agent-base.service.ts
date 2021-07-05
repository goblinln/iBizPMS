import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzAgent, IbzAgent } from '../../entities';
import keys from '../../entities/ibz-agent/ibz-agent-keys';

/**
 * 代理服务对象基类
 *
 * @export
 * @class IbzAgentBaseService
 * @extends {EntityBaseService}
 */
export class IbzAgentBaseService extends EntityBaseService<IIbzAgent> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzAgent';
    protected APPDENAMEPLURAL = 'IbzAgents';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzAgent.json';
    protected APPDEKEY = 'ibzagentid';
    protected APPDETEXT = 'ibzagentname';
    protected quickSearchFields = ['ibzagentname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzAgent): IbzAgent {
        return new IbzAgent(data);
    }

    async addLocal(context: IContext, entity: IIbzAgent): Promise<IIbzAgent | null> {
        return this.cache.add(context, new IbzAgent(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzAgent): Promise<IIbzAgent | null> {
        return super.createLocal(context, new IbzAgent(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzAgent> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzAgent): Promise<IIbzAgent> {
        return super.updateLocal(context, new IbzAgent(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzAgent = {}): Promise<IIbzAgent> {
        return new IbzAgent(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzagents`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzagents/${_context.ibzagent}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzagents/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzagents/${_context.ibzagent}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzagents/${_context.ibzagent}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzagents/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzAgentService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzagents/${_context.ibzagent}/select`);
    }
}
