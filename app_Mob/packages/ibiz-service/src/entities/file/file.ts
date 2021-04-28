import { FileBase } from './file-base';

/**
 * 附件
 *
 * @export
 * @class File
 * @extends {FileBase}
 * @implements {IFile}
 */
export class File extends FileBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof File
     */
    clone(): File {
        return new File(this);
    }
}
export default File;
