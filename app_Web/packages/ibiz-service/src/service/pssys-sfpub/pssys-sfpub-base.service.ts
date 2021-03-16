import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPSSysSFPub, PSSysSFPub } from '../../entities';
import keys from '../../entities/pssys-sfpub/pssys-sfpub-keys';

/**
 * 后台服务架构服务对象基类
 *
 * @export
 * @class PSSysSFPubBaseService
 * @extends {EntityBaseService}
 */
export class PSSysSFPubBaseService extends EntityBaseService<IPSSysSFPub> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PSSysSFPub';
    protected APPDENAMEPLURAL = 'PSSysSFPubs';
    protected APPDEKEY = 'pssyssfpubid';
    protected APPDETEXT = 'pssyssfpubname';
    protected quickSearchFields = ['pssyssfpubname',];
    protected selectContextParam = {
    };

    async addLocal(context: IContext, entity: IPSSysSFPub): Promise<IPSSysSFPub | null> {
        return this.cache.add(context, new PSSysSFPub(entity) as any);
    }

    async createLocal(context: IContext, entity: IPSSysSFPub): Promise<IPSSysSFPub | null> {
        return super.createLocal(context, new PSSysSFPub(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPSSysSFPub> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPSSysSFPub): Promise<IPSSysSFPub> {
        return super.updateLocal(context, new PSSysSFPub(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPSSysSFPub = {}): Promise<IPSSysSFPub> {
        return new PSSysSFPub(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
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
     * @memberof PSSysSFPubService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssyssfpubs/${_context.pssyssfpub}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/pssyssfpubs`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/pssyssfpubs/${_context.pssyssfpub}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/pssyssfpubs/${_context.pssyssfpub}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/pssyssfpubs/${_context.pssyssfpub}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/pssyssfpubs/getdraft`, _data);
        return res;
    }
    /**
     * FetchBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async FetchBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssyssfpubs/fetchbuild`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysSFPubService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssyssfpubs/fetchdefault`, _data);
    }
}
