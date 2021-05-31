import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskTeam, TaskTeam } from '../../entities';
import keys from '../../entities/task-team/task-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务团队服务对象基类
 *
 * @export
 * @class TaskTeamBaseService
 * @extends {EntityBaseService}
 */
export class TaskTeamBaseService extends EntityBaseService<ITaskTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'TaskTeam';
    protected APPDENAMEPLURAL = 'TaskTeams';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
    };

    newEntity(data: ITaskTeam): TaskTeam {
        return new TaskTeam(data);
    }

    async addLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam | null> {
        return this.cache.add(context, new TaskTeam(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam | null> {
        return super.createLocal(context, new TaskTeam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskTeam> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam> {
        return super.updateLocal(context, new TaskTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskTeam = {}): Promise<ITaskTeam> {
        return new TaskTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
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
}
