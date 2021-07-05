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
    protected APPNAME = 'Web';
    protected APPDENAME = 'TaskTeam';
    protected APPDENAMEPLURAL = 'TaskTeams';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TaskTeam.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        task: 'root',
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
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam> {
        return super.updateLocal(context, new TaskTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskTeam = {}): Promise<ITaskTeam> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.root = data.id;
            }
        }
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
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.project && _context.task && true) {
            return this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
    this.log.warn([`[TaskTeam]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
