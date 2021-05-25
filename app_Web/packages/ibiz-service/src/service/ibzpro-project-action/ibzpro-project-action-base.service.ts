import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProProjectAction, IBZProProjectAction } from '../../entities';
import keys from '../../entities/ibzpro-project-action/ibzpro-project-action-keys';

/**
 * 项目日志服务对象基类
 *
 * @export
 * @class IBZProProjectActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZProProjectActionBaseService extends EntityBaseService<IIBZProProjectAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProProjectAction';
    protected APPDENAMEPLURAL = 'IBZProProjectActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        project: 'objectid',
    };

    newEntity(data: IIBZProProjectAction): IBZProProjectAction {
        return new IBZProProjectAction(data);
    }

    async addLocal(context: IContext, entity: IIBZProProjectAction): Promise<IIBZProProjectAction | null> {
        return this.cache.add(context, new IBZProProjectAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProProjectAction): Promise<IIBZProProjectAction | null> {
        return super.createLocal(context, new IBZProProjectAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProProjectAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProProjectAction): Promise<IIBZProProjectAction> {
        return super.updateLocal(context, new IBZProProjectAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProProjectAction = {}): Promise<IIBZProProjectAction> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZProProjectAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
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
     * @memberof IBZProProjectActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
            return this.http.get(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/select`);
        }
        return this.http.get(`/ibzproprojectactions/${_context.ibzproprojectaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproprojectactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproprojectactions/${_context.ibzproprojectaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
            return this.http.delete(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}`);
        }
        return this.http.delete(`/ibzproprojectactions/${_context.ibzproprojectaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
            const res = await this.http.get(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzproprojectactions/${_context.ibzproprojectaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/ibzproprojectactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproprojectactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/comment`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/createhis`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.ibzproprojectaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/${_context.ibzproprojectaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzproprojectactions/${_context.ibzproprojectaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzproprojectactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProjectActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzproprojectactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProjectActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/ibzproprojectactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproprojectactions/sendtoreadbatch`,_data);
    }
}
