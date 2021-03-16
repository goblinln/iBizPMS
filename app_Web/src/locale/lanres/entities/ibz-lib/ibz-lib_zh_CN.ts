import IbzLib_zh_CN_Base from './ibz-lib_zh_CN_base';

function getLocaleResource(){
    const IbzLib_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzLib_zh_CN_Base(), IbzLib_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;