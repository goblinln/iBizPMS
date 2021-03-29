import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysEmployee, SysEmployee } from '../../entities';
import keys from '../../entities/sys-employee/sys-employee-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 人员服务对象基类
 *
 * @export
 * @class SysEmployeeBaseService
 * @extends {EntityBaseService}
 */
export class SysEmployeeBaseService extends EntityBaseService<ISysEmployee> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysEmployee';
    protected APPDENAMEPLURAL = 'SysEmployees';
    protected APPDEKEY = 'userid';
    protected APPDETEXT = 'personname';
    protected quickSearchFields = ['personname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysEmployee): SysEmployee {
        return new SysEmployee(data);
    }

    async addLocal(context: IContext, entity: ISysEmployee): Promise<ISysEmployee | null> {
        return this.cache.add(context, new SysEmployee(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysEmployee): Promise<ISysEmployee | null> {
        return super.createLocal(context, new SysEmployee(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysEmployee> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysEmployee): Promise<ISysEmployee> {
        return super.updateLocal(context, new SysEmployee(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysEmployee = {}): Promise<ISysEmployee> {
        return new SysEmployee(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugUserCond() {
        return this.condCache.get('bugUser');
    }

    protected getContActListCond() {
        return this.condCache.get('contActList');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getProductTeamMCond() {
        if (!this.condCache.has('productTeamM')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('productTeamM', cond);
            }
        }
        return this.condCache.get('productTeamM');
    }

    protected getProjectTeamMCond() {
        if (!this.condCache.has('projectTeamM')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectTeamM', cond);
            }
        }
        return this.condCache.get('projectTeamM');
    }

    protected getProjectTeamMProductCond() {
        if (!this.condCache.has('projectTeamMProduct')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectTeamMProduct', cond);
            }
        }
        return this.condCache.get('projectTeamMProduct');
    }

    protected getProjectTeamTaskUserTempCond() {
        return this.condCache.get('projectTeamTaskUserTemp');
    }

    protected getProjectTeamUserCond() {
        return this.condCache.get('projectTeamUser');
    }

    protected getProjectTeamUserTaskCond() {
        return this.condCache.get('projectTeamUserTask');
    }

    protected getProjectteamPkCond() {
        return this.condCache.get('projectteamPk');
    }

    protected getStoryProductTeamPKCond() {
        return this.condCache.get('storyProductTeamPK');
    }

    protected getTaskMTeamCond() {
        return this.condCache.get('taskMTeam');
    }

    protected getTaskTeamCond() {
        return this.condCache.get('taskTeam');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/${_context.sysemployee}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysemployees`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysemployees/${_context.sysemployee}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysemployees/${_context.sysemployee}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysemployees/${_context.sysemployee}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysemployees/getdraft`, _data);
        return res;
    }
    /**
     * FetchBugUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchBugUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchbuguser`, _data);
    }
    /**
     * FetchContActList
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchContActList(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchcontactlist`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchdefault`, _data);
    }
    /**
     * FetchProductTeamM
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProductTeamM(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchproductteamm`, _data);
    }
    /**
     * FetchProjectTeamM
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectTeamM(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteamm`, _data);
    }
    /**
     * FetchProjectTeamMProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectTeamMProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteammproduct`, _data);
    }
    /**
     * FetchProjectTeamTaskUserTemp
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectTeamTaskUserTemp(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteamtaskusertemp`, _data);
    }
    /**
     * FetchProjectTeamUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectTeamUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteamuser`, _data);
    }
    /**
     * FetchProjectTeamUserTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectTeamUserTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteamusertask`, _data);
    }
    /**
     * FetchProjectteamPk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProjectteamPk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchprojectteampk`, _data);
    }
    /**
     * FetchStoryProductTeamPK
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchStoryProductTeamPK(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchstoryproductteampk`, _data);
    }
    /**
     * FetchTaskMTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchTaskMTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchtaskmteam`, _data);
    }
    /**
     * FetchTaskTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchTaskTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysemployees/fetchtaskteam`, _data);
    }
}
