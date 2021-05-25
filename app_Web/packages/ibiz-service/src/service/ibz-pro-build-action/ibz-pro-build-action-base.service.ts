import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzProBuildAction, IbzProBuildAction } from '../../entities';
import keys from '../../entities/ibz-pro-build-action/ibz-pro-build-action-keys';

/**
 * 版本日志服务对象基类
 *
 * @export
 * @class IbzProBuildActionBaseService
 * @extends {EntityBaseService}
 */
export class IbzProBuildActionBaseService extends EntityBaseService<IIbzProBuildAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzProBuildAction';
    protected APPDENAMEPLURAL = 'IbzProBuildActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        build: 'objectid',
    };

    newEntity(data: IIbzProBuildAction): IbzProBuildAction {
        return new IbzProBuildAction(data);
    }

    async addLocal(context: IContext, entity: IIbzProBuildAction): Promise<IIbzProBuildAction | null> {
        return this.cache.add(context, new IbzProBuildAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzProBuildAction): Promise<IIbzProBuildAction | null> {
        return super.createLocal(context, new IbzProBuildAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzProBuildAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getBuildService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzProBuildAction): Promise<IIbzProBuildAction> {
        return super.updateLocal(context, new IbzProBuildAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzProBuildAction = {}): Promise<IIbzProBuildAction> {
        if (_context.build && _context.build !== '') {
            const s = await ___ibz___.gs.getBuildService();
            const data = await s.getLocal2(_context, _context.build);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IbzProBuildAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
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
     * @memberof IbzProBuildActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
            return this.http.get(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/select`);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
            return this.http.get(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/select`);
        }
        if (_context.build && _context.ibzprobuildaction) {
            return this.http.get(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/select`);
        }
        return this.http.get(`/ibzprobuildactions/${_context.ibzprobuildaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions`, _data);
        }
        if (_context.product && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions`, _data);
        }
        if (_context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzprobuildactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzprobuildactions/${_context.ibzprobuildaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
            return this.http.delete(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
            return this.http.delete(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
        }
        if (_context.build && _context.ibzprobuildaction) {
            return this.http.delete(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
        }
        return this.http.delete(`/ibzprobuildactions/${_context.ibzprobuildaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
            const res = await this.http.get(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
            return res;
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
            const res = await this.http.get(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
            return res;
        }
        if (_context.build && _context.ibzprobuildaction) {
            const res = await this.http.get(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzprobuildactions/${_context.ibzprobuildaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/getdraft`, _data);
            return res;
        }
        if (_context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/builds/${_context.build}/ibzprobuildactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprobuildactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/comment`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/comment`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/comment`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/createhis`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/createhis`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/createhis`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/editcomment`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/editcomment`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/managepmsee`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/managepmsee`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendmarkdone`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendmarkdone`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtodo`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtodo`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtoread`, _data);
        }
        if (_context.product && _context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtoread`, _data);
        }
        if (_context.build && _context.ibzprobuildaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/${_context.ibzprobuildaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzprobuildactions/${_context.ibzprobuildaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/fetchdefault`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/fetchdefault`, _data);
        }
        if (_context.build && true) {
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzprobuildactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBuildActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/fetchtype`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/fetchtype`, _data);
        }
        if (_context.build && true) {
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzprobuildactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/createhisbatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/createhisbatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/editcommentbatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/editcommentbatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/managepmseebatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/managepmseebatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/sendmarkdonebatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/sendtodobatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/sendtodobatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBuildActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/ibzprobuildactions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/ibzprobuildactions/sendtoreadbatch`,_data);
        }
        if(_context.build && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/builds/${_context.build}/ibzprobuildactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobuildactions/sendtoreadbatch`,_data);
    }
}
