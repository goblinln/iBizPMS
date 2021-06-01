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
        return entity!;
    }

    async updateLocal(context: IContext, entity: IStorySpec): Promise<IStorySpec> {
        return super.updateLocal(context, new StorySpec(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IStorySpec = {}): Promise<IStorySpec> {
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
}
