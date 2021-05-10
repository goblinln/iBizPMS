import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDocContent, DocContent } from '../../entities';
import keys from '../../entities/doc-content/doc-content-keys';

/**
 * 文档内容服务对象基类
 *
 * @export
 * @class DocContentBaseService
 * @extends {EntityBaseService}
 */
export class DocContentBaseService extends EntityBaseService<IDocContent> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'DocContent';
    protected APPDENAMEPLURAL = 'DocContents';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        doc: 'doc',
    };

    newEntity(data: IDocContent): DocContent {
        return new DocContent(data);
    }

    async addLocal(context: IContext, entity: IDocContent): Promise<IDocContent | null> {
        return this.cache.add(context, new DocContent(entity) as any);
    }

    async createLocal(context: IContext, entity: IDocContent): Promise<IDocContent | null> {
        return super.createLocal(context, new DocContent(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDocContent> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.doc && entity.doc !== '') {
            const s = await ___ibz___.gs.getDocService();
            const data = await s.getLocal2(context, entity.doc);
            if (data) {
                entity.doc = data.docsn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDocContent): Promise<IDocContent> {
        return super.updateLocal(context, new DocContent(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDocContent = {}): Promise<IDocContent> {
        if (_context.doc && _context.doc !== '') {
            const s = await ___ibz___.gs.getDocService();
            const data = await s.getLocal2(_context, _context.doc);
            if (data) {
                entity.doc = data.docsn;
            }
        }
        return new DocContent(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
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
     * @memberof DocContentService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && _context.doccontent) {
            return this.http.get(`/docs/${_context.doc}/doccontents/${_context.doccontent}/select`);
        }
        return this.http.get(`/doccontents/${_context.doccontent}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/docs/${_context.doc}/doccontents`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/doccontents`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && _context.doccontent) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/docs/${_context.doc}/doccontents/${_context.doccontent}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/doccontents/${_context.doccontent}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && _context.doccontent) {
            return this.http.delete(`/docs/${_context.doc}/doccontents/${_context.doccontent}`);
        }
        return this.http.delete(`/doccontents/${_context.doccontent}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && _context.doccontent) {
            const res = await this.http.get(`/docs/${_context.doc}/doccontents/${_context.doccontent}`);
            return res;
        }
        const res = await this.http.get(`/doccontents/${_context.doccontent}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/docs/${_context.doc}/doccontents/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/doccontents/getdraft`, _data);
        return res;
    }
    /**
     * FetchCurVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async FetchCurVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/doccontents/fetchcurversion`, _data);
        }
        return this.http.post(`/doccontents/fetchcurversion`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DocContentService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/doccontents/fetchdefault`, _data);
        }
        return this.http.post(`/doccontents/fetchdefault`, _data);
    }
}
