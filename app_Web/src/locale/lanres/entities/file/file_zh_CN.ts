import File_zh_CN_Base from './file_zh_CN_base';

function getLocaleResource(){
    const File_zh_CN_OwnData = {};
    const targetData = Object.assign(File_zh_CN_Base(), File_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;