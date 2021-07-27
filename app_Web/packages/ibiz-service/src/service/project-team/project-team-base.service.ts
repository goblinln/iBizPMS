import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTeam, ProjectTeam } from '../../entities';
import keys from '../../entities/project-team/project-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目团队服务对象基类
 *
 * @export
 * @class ProjectTeamBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTeamBaseService extends EntityBaseService<IProjectTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTeam';
    protected APPDENAMEPLURAL = 'ProjectTeams';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectTeam.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        project: 'root',
    };

    constructor(opts?: any) {
        super(opts, 'ProjectTeam');
    }

    newEntity(data: IProjectTeam): ProjectTeam {
        return new ProjectTeam(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTeam> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTeam): Promise<IProjectTeam> {
        return super.updateLocal(context, new ProjectTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTeam = {}): Promise<IProjectTeam> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return new ProjectTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProjectTeamPmCond() {
        return this.condCache.get('projectTeamPm');
    }

    protected getRowEditDefaultCond() {
        return this.condCache.get('rowEditDefault');
    }

    protected getSpecifyTeamCond() {
        if (!this.condCache.has('specifyTeam')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('specifyTeam', cond);
            }
        }
        return this.condCache.get('specifyTeam');
    }

    protected getTaskCntEstimateConsumedLeftCond() {
        return this.condCache.get('taskCntEstimateConsumedLeft');
    }

    protected getViewCond() {
        if (!this.condCache.has('view')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('view', cond);
            }
        }
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/projectteams`, _data);
            return res;
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${_context.project}/projectteams`, _data);
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.projectteam) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/projectteams/${_context.projectteam}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.projectteam) {
            const res = await this.http.get(`/projects/${_context.project}/projectteams/${_context.projectteam}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/projectteams/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectteams/getdraft`, _data);
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.projectteam) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/projectteams/${_context.projectteam}`);
            return res;
        }
        if (_context.project && _context.projectteam) {
            const res = await this.http.delete(`/projects/${_context.project}/projectteams/${_context.projectteam}`);
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.projectteam) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/projectteams/${_context.projectteam}`, _data);
            return res;
        }
        if (_context.project && _context.projectteam) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${_context.project}/projectteams/${_context.projectteam}`, _data);
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchCntEst
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchCntEst(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/projectteams/fetchcntest`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCntEst');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/projectteams/fetchcntest`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCntEst');
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[FetchCntEst函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchSpecifyTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchSpecifyTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/projectteams/fetchspecifyteam`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchSpecifyTeam');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/projectteams/fetchspecifyteam`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchSpecifyTeam');
            return res;
        }
    this.log.warn([`[ProjectTeam]>>>[FetchSpecifyTeam函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
