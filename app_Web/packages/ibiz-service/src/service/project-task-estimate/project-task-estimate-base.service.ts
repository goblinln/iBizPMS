import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTaskEstimate, ProjectTaskEstimate } from '../../entities';
import keys from '../../entities/project-task-estimate/project-task-estimate-keys';

/**
 * 项目工时统计服务对象基类
 *
 * @export
 * @class ProjectTaskEstimateBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTaskEstimateBaseService extends EntityBaseService<IProjectTaskEstimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTaskEstimate';
    protected APPDENAMEPLURAL = 'ProjectTaskEstimates';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectTaskEstimate): ProjectTaskEstimate {
        return new ProjectTaskEstimate(data);
    }

    async addLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate | null> {
        return this.cache.add(context, new ProjectTaskEstimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate | null> {
        return super.createLocal(context, new ProjectTaskEstimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTaskEstimate> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate> {
        return super.updateLocal(context, new ProjectTaskEstimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTaskEstimate = {}): Promise<IProjectTaskEstimate> {
        return new ProjectTaskEstimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
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
