import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysRole, SysRole } from '../../entities';
import keys from '../../entities/sys-role/sys-role-keys';

/**
 * 系统角色服务对象基类
 *
 * @export
 * @class SysRoleBaseService
 * @extends {EntityBaseService}
 */
export class SysRoleBaseService extends EntityBaseService<ISysRole> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysRole';
    protected APPDENAMEPLURAL = 'SysRoles';
    protected APPDEKEY = 'roleid';
    protected APPDETEXT = 'rolename';
    protected quickSearchFields = ['rolename',];
    protected selectContextParam = {
    };

    newEntity(data: ISysRole): SysRole {
        return new SysRole(data);
    }

    async addLocal(context: IContext, entity: ISysRole): Promise<ISysRole | null> {
        return this.cache.add(context, new SysRole(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysRole): Promise<ISysRole | null> {
        return super.createLocal(context, new SysRole(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysRole> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysRole): Promise<ISysRole> {
        return super.updateLocal(context, new SysRole(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysRole = {}): Promise<ISysRole> {
        return new SysRole(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
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
     * @memberof SysRoleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysroles/${_context.sysrole}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysroles`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysroles/${_context.sysrole}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysroles/${_context.sysrole}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysroles/${_context.sysrole}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysroles/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysRoleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/sysroles/fetchdefault`, _data);
    }
}