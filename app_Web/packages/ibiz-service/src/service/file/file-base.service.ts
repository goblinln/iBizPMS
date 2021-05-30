import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IFile, File } from '../../entities';
import keys from '../../entities/file/file-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 附件服务对象基类
 *
 * @export
 * @class FileBaseService
 * @extends {EntityBaseService}
 */
export class FileBaseService extends EntityBaseService<IFile> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'File';
    protected APPDENAMEPLURAL = 'Files';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IFile): File {
        return new File(data);
    }

    async addLocal(context: IContext, entity: IFile): Promise<IFile | null> {
        return this.cache.add(context, new File(entity) as any);
    }

    async createLocal(context: IContext, entity: IFile): Promise<IFile | null> {
        return super.createLocal(context, new File(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IFile> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IFile): Promise<IFile> {
        return super.updateLocal(context, new File(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IFile = {}): Promise<IFile> {
        return new File(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
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
        return this.condCache.get('default');
    }

    protected getDocLibFileCond() {
        return this.condCache.get('docLibFile');
    }

    protected getMySubmitFileCond() {
        if (!this.condCache.has('mySubmitFile')) {
            const strCond: any[] = ['AND', ['EQ', 'ADDEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('mySubmitFile', cond);
            }
        }
        return this.condCache.get('mySubmitFile');
    }

    protected getProductDocLibFileCond() {
        return this.condCache.get('productDocLibFile');
    }

    protected getTypeCond() {
        if (!this.condCache.has('type')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}], ['NOTEQ', 'EXTRA','editor']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('type', cond);
            }
        }
        return this.condCache.get('type');
    }

    protected getTypeNotBySrfparentkeyCond() {
        return this.condCache.get('typeNotBySrfparentkey');
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
     * @memberof FileService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/files/${_context.file}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/files`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/files/${_context.file}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/files/${_context.file}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/files/${_context.file}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/files/getdraft`, _data);
        return res;
    }
    /**
     * UpdateObjectID
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async UpdateObjectID(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/files/${_context.file}/updateobjectid`, _data);
    }
    /**
     * UpdateObjectIDForPmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async UpdateObjectIDForPmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/files/${_context.file}/updateobjectidforpmsee`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/files/fetchdefault`, _data);
    }
    /**
     * FetchDocLibFile
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchDocLibFile(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/files/fetchdoclibfile`, _data);
    }
    /**
     * FetchProductDocLibFile
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchProductDocLibFile(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/files/fetchproductdoclibfile`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/files/fetchtype`, _data);
    }
    /**
     * FetchTypeNotBySrfparentkey
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchTypeNotBySrfparentkey(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/files/fetchtypenotbysrfparentkey`, _data);
    }

    /**
     * UpdateObjectIDBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof FileServiceBase
     */
    public async UpdateObjectIDBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/files/updateobjectidbatch`,_data);
    }

    /**
     * UpdateObjectIDForPmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof FileServiceBase
     */
    public async UpdateObjectIDForPmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/files/updateobjectidforpmseebatch`,_data);
    }
}
