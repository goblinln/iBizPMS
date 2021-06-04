import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskTeamNested, TaskTeamNested } from '../../entities';
import keys from '../../entities/task-team-nested/task-team-nested-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { SearchFilter } from 'ibiz-core';

/**
 * 任务团队服务对象基类
 *
 * @export
 * @class TaskTeamNestedBaseService
 * @extends {EntityBaseService}
 */
export class TaskTeamNestedBaseService extends EntityBaseService<ITaskTeamNested> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TaskTeamNested';
    protected APPDENAMEPLURAL = 'TaskTeamNesteds';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
    };

    newEntity(data: ITaskTeamNested): TaskTeamNested {
        return new TaskTeamNested(data);
    }

    async addLocal(context: IContext, entity: ITaskTeamNested): Promise<ITaskTeamNested | null> {
        return this.cache.add(context, new TaskTeamNested(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskTeamNested): Promise<ITaskTeamNested | null> {
        return super.createLocal(context, new TaskTeamNested(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskTeamNested> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskTeamNested): Promise<ITaskTeamNested> {
        return super.updateLocal(context, new TaskTeamNested(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskTeamNested = {}): Promise<ITaskTeamNested> {
        return new TaskTeamNested(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamNestedService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','task']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamNestedService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
}
