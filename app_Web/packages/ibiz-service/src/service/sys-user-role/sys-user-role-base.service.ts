import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysUserRole, SysUserRole } from '../../entities';
import keys from '../../entities/sys-user-role/sys-user-role-keys';

/**
 * 用户角色关系服务对象基类
 *
 * @export
 * @class SysUserRoleBaseService
 * @extends {EntityBaseService}
 */
export class SysUserRoleBaseService extends EntityBaseService<ISysUserRole> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysUserRole';
    protected APPDENAMEPLURAL = 'SysUserRoles';
    protected APPDEKEY = 'userroleid';
    protected APPDETEXT = 'userid';
    protected quickSearchFields = ['userid',];
    protected selectContextParam = {
    };

    newEntity(data: ISysUserRole): SysUserRole {
        return new SysUserRole(data);
    }

    async addLocal(context: IContext, entity: ISysUserRole): Promise<ISysUserRole | null> {
        return this.cache.add(context, new SysUserRole(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysUserRole): Promise<ISysUserRole | null> {
        return super.createLocal(context, new SysUserRole(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysUserRole> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysUserRole): Promise<ISysUserRole> {
        return super.updateLocal(context, new SysUserRole(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysUserRole = {}): Promise<ISysUserRole> {
        return new SysUserRole(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
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
     * @memberof SysUserRoleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysuserroles/${_context.sysuserrole}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysuserroles`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysuserroles/${_context.sysuserrole}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysuserroles/${_context.sysuserrole}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysuserroles/${_context.sysuserrole}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysuserroles/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserRoleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysuserroles/fetchdefault`, _data);
    }
}
