import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IStorySpec, StorySpec } from '../../entities';
import keys from '../../entities/story-spec/story-spec-keys';

/**
 * 需求描述服务对象基类
 *
 * @export
 * @class StorySpecBaseService
 * @extends {EntityBaseService}
 */
export class StorySpecBaseService extends EntityBaseService<IStorySpec> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'StorySpec';
    protected APPDENAMEPLURAL = 'StorySpecs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        story: 'story',
    };

    newEntity(data: IStorySpec): StorySpec {
        return new StorySpec(data);
    }

    async addLocal(context: IContext, entity: IStorySpec): Promise<IStorySpec | null> {
        return this.cache.add(context, new StorySpec(entity) as any);
    }

    async createLocal(context: IContext, entity: IStorySpec): Promise<IStorySpec | null> {
        return super.createLocal(context, new StorySpec(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IStorySpec> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.story && entity.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.story);
            if (data) {
                entity.title = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IStorySpec): Promise<IStorySpec> {
        return super.updateLocal(context, new StorySpec(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IStorySpec = {}): Promise<IStorySpec> {
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.title = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
        return new StorySpec(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
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
     * @memberof StorySpecService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.storyspec) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/storyspecs/${_context.storyspec}/select`);
        }
        if (_context.story && _context.storyspec) {
            return this.http.get(`/stories/${_context.story}/storyspecs/${_context.storyspec}/select`);
        }
        return this.http.get(`/storyspecs/${_context.storyspec}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/storyspecs`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/storyspecs`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/storyspecs`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.storyspec) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/storyspecs/${_context.storyspec}`, _data);
        }
        if (_context.story && _context.storyspec) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/storyspecs/${_context.storyspec}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/storyspecs/${_context.storyspec}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.storyspec) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/storyspecs/${_context.storyspec}`);
        }
        if (_context.story && _context.storyspec) {
            return this.http.delete(`/stories/${_context.story}/storyspecs/${_context.storyspec}`);
        }
        return this.http.delete(`/storyspecs/${_context.storyspec}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.storyspec) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/storyspecs/${_context.storyspec}`);
            return res;
        }
        if (_context.story && _context.storyspec) {
            const res = await this.http.get(`/stories/${_context.story}/storyspecs/${_context.storyspec}`);
            return res;
        }
        const res = await this.http.get(`/storyspecs/${_context.storyspec}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/storyspecs/getdraft`, _data);
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/storyspecs/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/storyspecs/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/storyspecs/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/storyspecs/fetchdefault`, _data);
        }
        return this.http.post(`/storyspecs/fetchdefault`, _data);
    }
    /**
     * FetchVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async FetchVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/storyspecs/fetchversion`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/storyspecs/fetchversion`, _data);
        }
        return this.http.post(`/storyspecs/fetchversion`, _data);
    }
}