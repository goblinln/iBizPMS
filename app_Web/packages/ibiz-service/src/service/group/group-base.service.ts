import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IGroup, Group } from '../../entities';
import keys from '../../entities/group/group-keys';

/**
 * 群组服务对象基类
 *
 * @export
 * @class GroupBaseService
 * @extends {EntityBaseService}
 */
export class GroupBaseService extends EntityBaseService<IGroup> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Group';
    protected APPDENAMEPLURAL = 'Groups';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IGroup): Group {
        return new Group(data);
    }

    async addLocal(context: IContext, entity: IGroup): Promise<IGroup | null> {
        return this.cache.add(context, new Group(entity) as any);
    }

    async createLocal(context: IContext, entity: IGroup): Promise<IGroup | null> {
        return super.createLocal(context, new Group(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IGroup> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IGroup): Promise<IGroup> {
        return super.updateLocal(context, new Group(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IGroup = {}): Promise<IGroup> {
        return new Group(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
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
     * @memberof GroupService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/groups/${_context.group}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/groups`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/groups/${_context.group}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/groups/${_context.group}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/groups/${_context.group}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/groups/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof GroupService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/groups/fetchdefault`, _data);
    }
}
