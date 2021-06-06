import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IEmployee, Employee } from '../../entities';
import keys from '../../entities/employee/employee-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 人员服务对象基类
 *
 * @export
 * @class EmployeeBaseService
 * @extends {EntityBaseService}
 */
export class EmployeeBaseService extends EntityBaseService<IEmployee> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Employee';
    protected APPDENAMEPLURAL = 'Employees';
    protected APPDEKEY = 'userid';
    protected APPDETEXT = 'personname';
    protected quickSearchFields = ['personname',];
    protected selectContextParam = {
    };

    newEntity(data: IEmployee): Employee {
        return new Employee(data);
    }

    async addLocal(context: IContext, entity: IEmployee): Promise<IEmployee | null> {
        return this.cache.add(context, new Employee(entity) as any);
    }

    async createLocal(context: IContext, entity: IEmployee): Promise<IEmployee | null> {
        return super.createLocal(context, new Employee(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IEmployee> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IEmployee): Promise<IEmployee> {
        return super.updateLocal(context, new Employee(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IEmployee = {}): Promise<IEmployee> {
        return new Employee(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
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
     * FetchContact
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchContact(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchcontact`, _data);
    }
    /**
     * FetchTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchtask`, _data);
    }
    /**
     * FetchProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchproject`, _data);
    }
    /**
     * FetchTaskMulti
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchTaskMulti(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchtaskmulti`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchdefault`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/employees/${_context.employee}`);
        return res;
    }
    /**
     * FetchBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchbug`, _data);
    }
    /**
     * FetchProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmployeeService
     */
    async FetchProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/employees/fetchproduct`, _data);
    }
}
