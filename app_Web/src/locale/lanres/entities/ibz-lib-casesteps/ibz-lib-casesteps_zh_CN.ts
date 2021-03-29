import IbzLibCasesteps_zh_CN_Base from './ibz-lib-casesteps_zh_CN_base';

function getLocaleResource(){
    const IbzLibCasesteps_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzLibCasesteps_zh_CN_Base(), IbzLibCasesteps_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;