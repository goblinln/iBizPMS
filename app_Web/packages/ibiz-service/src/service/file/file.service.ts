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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {FileService}
     * @memberof FileService
     */
    static getInstance(context?: any): FileService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}FileService` : `FileService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new FileService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default FileService;
