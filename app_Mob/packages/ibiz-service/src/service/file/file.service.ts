import { FileBaseService } from './file-base.service';

/**
 * 附件服务
 *
 * @export
 * @class FileService
 * @extends {FileBaseService}
 */
export class FileService extends FileBaseService {
    /**
     * Creates an instance of FileService.
     * @memberof FileService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('FileService')) {
            return ___ibz___.sc.get('FileService');
        }
        ___ibz___.sc.set('FileService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {FileService}
     * @memberof FileService
     */
    static getInstance(): FileService {
        if (!___ibz___.sc.has('FileService')) {
            new FileService();
        }
        return ___ibz___.sc.get('FileService');
    }
}
export default FileService;
