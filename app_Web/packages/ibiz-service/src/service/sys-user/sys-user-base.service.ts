import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysUser, SysUser } from '../../entities';
import keys from '../../entities/sys-user/sys-user-keys';

/**
 * 系统用户服务对象基类
 *
 * @export
 * @class SysUserBaseService
 * @extends {EntityBaseService}
 */
export class SysUserBaseService extends EntityBaseService<ISysUser> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysUser';
    protected APPDENAMEPLURAL = 'SysUsers';
    protected APPDEKEY = 'userid';
    protected APPDETEXT = 'personname';
    protected quickSearchFields = ['personname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysUser): SysUser {
        return new SysUser(data);
    }

    async addLocal(context: IContext, entity: ISysUser): Promise<ISysUser | null> {
        return this.cache.add(context, new SysUser(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysUser): Promise<ISysUser | null> {
        return super.createLocal(context, new SysUser(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysUser> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysUser): Promise<ISysUser> {
        return super.updateLocal(context, new SysUser(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysUser = {}): Promise<ISysUser> {
        return new SysUser(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
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
     * @memberof SysUserService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysusers/${_context.sysuser}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysusers`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysusers/${_context.sysuser}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysusers/${_context.sysuser}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysusers/${_context.sysuser}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysusers/getdraft`, _data);
        return res;
    }
    /**
     * ChangePwd
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async ChangePwd(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/sysusers/${_context.sysuser}/changepwd`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUserService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysusers/fetchdefault`, _data);
    }
}