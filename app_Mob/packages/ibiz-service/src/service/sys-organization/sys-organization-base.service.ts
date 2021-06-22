import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysOrganization, SysOrganization } from '../../entities';
import keys from '../../entities/sys-organization/sys-organization-keys';

/**
 * 单位服务对象基类
 *
 * @export
 * @class SysOrganizationBaseService
 * @extends {EntityBaseService}
 */
export class SysOrganizationBaseService extends EntityBaseService<ISysOrganization> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'SysOrganization';
    protected APPDENAMEPLURAL = 'SysOrganizations';
    protected APPDEKEY = 'orgid';
    protected APPDETEXT = 'orgname';
    protected quickSearchFields = ['orgname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysOrganization): SysOrganization {
        return new SysOrganization(data);
    }

    async addLocal(context: IContext, entity: ISysOrganization): Promise<ISysOrganization | null> {
        return this.cache.add(context, new SysOrganization(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysOrganization): Promise<ISysOrganization | null> {
        return super.createLocal(context, new SysOrganization(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysOrganization> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysOrganization): Promise<ISysOrganization> {
        return super.updateLocal(context, new SysOrganization(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysOrganization = {}): Promise<ISysOrganization> {
        return new SysOrganization(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
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
     * @memberof SysOrganizationService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysorganizations`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysorganizations/${_context.sysorganization}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysorganizations/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysorganizations/${_context.sysorganization}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysorganizations/${_context.sysorganization}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysorganizations/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysOrganizationService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysorganizations/${_context.sysorganization}/select`);
    }
}
