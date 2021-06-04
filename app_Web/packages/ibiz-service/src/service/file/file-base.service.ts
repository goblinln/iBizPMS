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
     * FetchProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchproject`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchproject`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchproject`, _data);
        }
        return this.http.post(`/files/fetchproject`, _data);
    }
    /**
     * FetchProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchproduct`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchproduct`, _data);
        }
        return this.http.post(`/files/fetchproduct`, _data);
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
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchtype`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchtype`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchtype`, _data);
        }
        return this.http.post(`/files/fetchtype`, _data);
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
        if (_context.project && _context.story && _context.file) {
            return this.http.delete(`/projects/${_context.project}/stories/${_context.story}/files/${_context.file}`);
        }
        if (_context.product && _context.story && _context.file) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/files/${_context.file}`);
        }
        if (_context.product && _context.file) {
            return this.http.delete(`/products/${_context.product}/files/${_context.file}`);
        }
        return this.http.delete(`/files/${_context.file}`);
    }
}
