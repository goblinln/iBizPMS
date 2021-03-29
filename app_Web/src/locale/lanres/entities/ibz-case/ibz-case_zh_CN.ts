import IbzCase_zh_CN_Base from './ibz-case_zh_CN_base';

function getLocaleResource(){
    const IbzCase_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzCase_zh_CN_Base(), IbzCase_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;