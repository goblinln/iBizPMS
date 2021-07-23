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
    protected APPNAME = 'Mob';
    protected APPDENAME = 'SysEmployee';
    protected APPDENAMEPLURAL = 'SysEmployees';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee.json';
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
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/sysemployees/${_context.sysemployee}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchbug`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchBug');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchContact
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchContact(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchcontact`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchContact');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/sysemployees/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchtask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchTask');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchTaskMulti
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysEmployeeService
     */
    async FetchTaskMulti(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysemployees/fetchtaskmulti`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchTaskMulti');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
