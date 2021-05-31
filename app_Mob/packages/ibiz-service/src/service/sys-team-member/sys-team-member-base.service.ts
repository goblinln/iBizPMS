import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysTeamMember, SysTeamMember } from '../../entities';
import keys from '../../entities/sys-team-member/sys-team-member-keys';

/**
 * 组成员服务对象基类
 *
 * @export
 * @class SysTeamMemberBaseService
 * @extends {EntityBaseService}
 */
export class SysTeamMemberBaseService extends EntityBaseService<ISysTeamMember> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'SysTeamMember';
    protected APPDENAMEPLURAL = 'SysTeamMembers';
    protected APPDEKEY = 'teammemberid';
    protected APPDETEXT = 'teamid';
    protected quickSearchFields = ['teamid',];
    protected selectContextParam = {
    };

    newEntity(data: ISysTeamMember): SysTeamMember {
        return new SysTeamMember(data);
    }

    async addLocal(context: IContext, entity: ISysTeamMember): Promise<ISysTeamMember | null> {
        return this.cache.add(context, new SysTeamMember(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysTeamMember): Promise<ISysTeamMember | null> {
        return super.createLocal(context, new SysTeamMember(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysTeamMember> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysTeamMember): Promise<ISysTeamMember> {
        return super.updateLocal(context, new SysTeamMember(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysTeamMember = {}): Promise<ISysTeamMember> {
        return new SysTeamMember(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
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
     * @memberof SysTeamMemberService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/systeammembers/${_context.systeammember}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/systeammembers`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/systeammembers/${_context.systeammember}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/systeammembers/${_context.systeammember}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/systeammembers/${_context.systeammember}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/systeammembers/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysTeamMemberService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/systeammembers/fetchdefault`, _data);
    }
}
