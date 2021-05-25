import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZTaskAction, IBZTaskAction } from '../../entities';
import keys from '../../entities/ibztask-action/ibztask-action-keys';

/**
 * 任务日志服务对象基类
 *
 * @export
 * @class IBZTaskActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZTaskActionBaseService extends EntityBaseService<IIBZTaskAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZTaskAction';
    protected APPDENAMEPLURAL = 'IBZTaskActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        task: 'objectid',
    };

    newEntity(data: IIBZTaskAction): IBZTaskAction {
        return new IBZTaskAction(data);
    }

    async addLocal(context: IContext, entity: IIBZTaskAction): Promise<IIBZTaskAction | null> {
        return this.cache.add(context, new IBZTaskAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZTaskAction): Promise<IIBZTaskAction | null> {
        return super.createLocal(context, new IBZTaskAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZTaskAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZTaskAction): Promise<IIBZTaskAction> {
        return super.updateLocal(context, new IBZTaskAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZTaskAction = {}): Promise<IIBZTaskAction> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZTaskAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
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
     * @memberof IBZTaskActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/select`);
        }
        if (_context.task && _context.ibztaskaction) {
            return this.http.get(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/select`);
        }
        return this.http.get(`/ibztaskactions/${_context.ibztaskaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions`, _data);
        }
        if (_context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tasks/${_context.task}/ibztaskactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibztaskactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibztaskactions/${_context.ibztaskaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`);
        }
        if (_context.task && _context.ibztaskaction) {
            return this.http.delete(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`);
        }
        return this.http.delete(`/ibztaskactions/${_context.ibztaskaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`);
            return res;
        }
        if (_context.task && _context.ibztaskaction) {
            const res = await this.http.get(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}`);
            return res;
        }
        const res = await this.http.get(`/ibztaskactions/${_context.ibztaskaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/getdraft`, _data);
            return res;
        }
        if (_context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tasks/${_context.task}/ibztaskactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibztaskactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/comment`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/comment`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/createhis`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/createhis`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/editcomment`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/editcomment`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/managepmsee`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendmarkdone`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendtodo`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendtoread`, _data);
        }
        if (_context.task && _context.ibztaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/${_context.ibztaskaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibztaskactions/${_context.ibztaskaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/fetchdefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibztaskactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/fetchtype`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/fetchtype`, _data);
        }
        return this.http.post(`/ibztaskactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/createhisbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/editcommentbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/managepmseebatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/sendmarkdonebatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/sendtodobatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/ibztaskactions/sendtoreadbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/ibztaskactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztaskactions/sendtoreadbatch`,_data);
    }
}
